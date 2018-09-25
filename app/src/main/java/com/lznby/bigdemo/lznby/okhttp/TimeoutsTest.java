package com.lznby.bigdemo.lznby.okhttp;

import com.lznby.bigdemo.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/7 19:42
 * Class Note:
 * 1. Timeouts (设置超时)
 */
public class TimeoutsTest {

    private final OkHttpClient client;

    private String responseString = new String();

    public TimeoutsTest() throws Exception {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Timeouts
     *
     * @return
     */
    public String timeouts() {
        Request request = new Request.Builder()
                // This Url is served with a 2 second delay.
                .url("http://httpbin.org/delay/2")
                .build();
        try (Response response = client.newCall(request).execute()) {
            LogUtil.e("Response completed:" + response);
            responseString = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
