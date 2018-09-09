package com.lznby.bigdemo.lznby.okhttp;

import com.lznby.bigdemo.utils.LogUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/7 17:46
 * Class Note:
 * 1.OkHttp Cache Response 缓存响应结果
 */
public class OkHttpCacheTest {

    private final OkHttpClient client;

    public OkHttpCacheTest(File cacheDirectory) throws Exception{
        /**
         * 10MiB
         */
        int cacheSize =10*1024*1024;

        Cache cache = new Cache(cacheDirectory,cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    /**
     * 请求缓存
     * @return
     */
    public String responseCaching() {

        String responseString = new String();

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        String response1Body = new String();
        try (Response response1 = client.newCall(request).execute()){
            if (!response1.isSuccessful()) {
                throw new IOException("Unexpected Code" + response1);
            }
            response1Body = response1.body().string();
            LogUtil.e("Response 1 response:         " + response1);
            LogUtil.e("Response 1 cache response:   " + response1.cacheResponse());
            LogUtil.e("Response 1 network response: " + response1.networkResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

        String response2Body = new String();
        try (Response response2 = client.newCall(request).execute()){
            if (!response2.isSuccessful()) {
                throw new IOException("Unexpected Code" + response2);
            }
            response2Body = response2.body().string();
            LogUtil.e("Response 1 response:         " + response2);
            LogUtil.e("Response 1 cache response:   " + response2.cacheResponse());
            LogUtil.e("Response 1 network response: " + response2.networkResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseString = "Response 2 equals Response 1?" + response1Body.equals(response2Body);

        LogUtil.e(responseString);

        return responseString;

    }


}
