package com.lznby.bigdemo.lznby.okhttp.jsonUtils;

import com.google.gson.Gson;
import com.lznby.bigdemo.lznby.okhttp.LoggerInterceptor;
import com.lznby.bigdemo.utils.LogUtil;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/9 21:38
 * Class Note:
 * 1.网络请求工具类
 * 2.BaseInfoEntity为基础类
 * 3.其实主要是为了使用泛型解析json(参考：https://www.jianshu.com/p/d62c2be60617)
 */
public class OkHttpUtils<T> {

    OkHttpClient client = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            }).addNetworkInterceptor(new LoggerInterceptor())
            .build();

    /**
     * data 为 object 的情况:
     * {"code":"0","message":"success","data":{}}
     *
     * @param requestBody 为get请求时传入null即可
     * @param isPost      true:post false:get
     * @param url         请求地址
     * @param clazz       即T的类型 如T为 RegisterResponseInfo 时 clazz 为 RegisterResponseInfo.class
     * @param <T>
     * @return
     */
    public <T> BaseInfoEntity<T> getResponseBodyObject(RequestBody requestBody, boolean isPost, String url, Class<T> clazz) {
        String responseString;
        BaseInfoEntity<T> infoEntity = new BaseInfoEntity<>();

        //这里是解析Gson的关键
        Type type = new ParameterizedTypeImpl(BaseInfoEntity.class, new Class[]{clazz});

        Request request;
        if (isPost && requestBody != null) {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                LogUtil.e("请求响应失败！");
            }

            responseString = response.body().string();

            LogUtil.e("请求结果是：" + responseString);

            Gson gson = new Gson();

            infoEntity = gson.fromJson(responseString, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoEntity;
    }

    /**
     * data 为 array 的情况:
     * {"code":"0","message":"success","data":[]}
     *
     * @param requestBody 为get请求时传入null即可
     * @param isPost      true:post false:get
     * @param url         请求地址
     * @param clazz       即T的类型 如T为 RegisterResponseInfo 时 clazz 为 RegisterResponseInfo.class
     * @param <T>
     * @return
     */
    public <T> BaseInfoEntity<List<T>> getResponseBodyArray(RequestBody requestBody, boolean isPost, String url, Class<T> clazz) {
        String responseString;
        BaseInfoEntity<List<T>> infoEntity = new BaseInfoEntity<>();

        //以下两行为解析Gson的关键
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, new Class[]{clazz});
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(BaseInfoEntity.class, new Type[]{listType});

        Request request;
        if (isPost && requestBody != null) {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                LogUtil.e("请求响应失败！");
            }

            responseString = response.body().string();

            LogUtil.e("请求结果是：" + responseString);

            Gson gson = new Gson();

            infoEntity = gson.fromJson(responseString, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return infoEntity;
    }

}
