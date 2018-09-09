package com.lznby.bigdemo.lznby.okhttp;

import com.lznby.bigdemo.utils.LogUtil;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * @author Lznby
 * @time 2018/9/7 20:15
 * Class Note:
 * 1.Handling Authentication (处理身份认证)
 */
public class HandlingAuthenticationTest {

    private final OkHttpClient client;

    public HandlingAuthenticationTest() {
        client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        if (response.request().header("Authorization")!=null) {
                            return null;//Give up, we've already attempted to authenticate.
                        }
                        LogUtil.e("Authenticating for response:" + response);
                        LogUtil.e("Challenges:" + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();
    }

    /**
     * Handling Authentication
     * @return
     */
    public String handlingAuthentication() {

        String responseString = new String();

        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }
            if (responseCount(response) >= 3) {
                return null;//Ifd we've failed 3 times, give up.
            }
            responseString = response.body().string();
            LogUtil.e("请求结果：" + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * 响应次数计数器
     * @param response
     * @return
     */
    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
