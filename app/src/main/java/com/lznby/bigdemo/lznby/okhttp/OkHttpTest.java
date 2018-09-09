package com.lznby.bigdemo.lznby.okhttp;

import com.lznby.bigdemo.utils.LogUtil;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

/**
 * @author Lznby
 * @time 2018/9/6 14:04
 * Class Note:
 * 参考自：https://github.com/square/okhttp/wiki
 * 1.okhttp3 基本使用
 */
public class OkHttpTest {

    private final OkHttpClient client = new OkHttpClient();

    /**
     * 在Canceling a Call的使用
     */
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    /**
     * Synchronous Get(同步请求)
     *
     * @return
     * @throws Exception
     */
    public String synchronousGet() throws Exception {
        String responseString;
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Headers responseHeaders = response.headers();

            for (int i = 0; i < responseHeaders.size(); i++) {
                LogUtil.e("我是响应头：" + responseHeaders.name(i) + ":" + responseHeaders.value(i));
            }
            responseString = response.body().string();
            LogUtil.e("我是响应内容：" + responseString);
        }
        return responseString;
    }

    /**
     * Asynchronous Get(异步请求)
     *
     * @return
     * @throws Exception
     */
    public String asynchronousGet() throws Exception {
        final StringBuffer responseString = new StringBuffer();
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected Code " + response);
                    }

                    Headers responseHeaders = response.headers();

                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        LogUtil.e("我是响应头：" + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    responseString.append(responseBody.string());
                    LogUtil.e("我是响应内容：" + responseString.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return "有没有结果：" + responseString.toString();
    }

    /**
     * Accessing Headers  (添加请求头)
     */
    public String accessingHeaders() throws Exception {

        String responseString = new String();

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = client.newCall(request).execute()) {

            responseString = response.body().string();

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }
            LogUtil.e("Service:" + response.header("Service"));
            LogUtil.e("Date:" + response.header("Data"));
            LogUtil.e("Vary:" + response.header("Vary"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Posting a String
     *
     * @return
     */
    public String postString() throws Exception {

        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

        String responseString = new String();

        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }
            responseString = response.body().string();
            LogUtil.e("请求结果：" + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Post Streaming
     *
     * @return
     */
    public String postStreaming() throws Exception {

        String responseString = new String();

        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n)
                        return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code " + response);
            }

            responseString = response.body().string();
            LogUtil.e("请求结果：" + responseString);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }

    /**
     * Posting a File (找不到文件？？)
     *
     * @return
     */
    public String postFile() throws Exception {
        String responseString = new String();

        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

        File file = new File("README.md");

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code " + response);
            }

            responseString = response.body().string();

            LogUtil.e("请求结果" + responseString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Post from parameters
     *
     * @return
     */
    public String postFromParameters() throws Exception {
        String responseString = new String();

        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code " + response);
            }
            responseString = response.body().string();
            LogUtil.e(responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Posting a multipart request
     */
    public String postMultipartRequest() throws Exception {

        final String IMAGUR_CLIENT_ID = "...";

        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        String responseString = new String();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png", RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID" + IMAGUR_CLIENT_ID)
                .url("https://api.imgur.com/3/image")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }
            responseString = response.body().string();
            LogUtil.e("请求结果：" + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Parse a Json Response With Moshi (使用Moshi解析返回的结果)
     *
     * @return
     */
    public String parseJsonResponseWithMoshi() {

        String responseString;

        final Moshi moshi = new Moshi.Builder().build();

        final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }

            Gist gist = gistJsonAdapter.fromJson(response.body().source());

            for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
                LogUtil.e("key::" + entry.getKey());
                LogUtil.e("Value::" + entry.getValue().content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "请求成功";
    }

    /**
     * Response Caching 参见 OkHttpCacheTest
     */

    /**
     * Canceling a Call (取消) (好像有点问题)
     *
     * @return
     */
    public String cancelingCall() {

        String responseString = new String();

        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/2")
                .build();

        final long startNanos = System.nanoTime();
        final Call call = client.newCall(request);

        // Schedule a job to cancel the call in 1 second
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                call.cancel();
                System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
            }
        }, 1, TimeUnit.SECONDS);

        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);

        try (Response response = call.execute()) {
            System.out.printf("%.2f Call was expected to fail, but completed %s%n", (System.nanoTime() - startNanos) / 1e9f, response);
            responseString = response.body().string();
        } catch (Exception e) {
            System.out.printf("%.2f Call failed as expected %s%n", (System.nanoTime() - startNanos) / 1e9f, e);
            e.printStackTrace();
        }
        return responseString;
    }

    /**
     * Timeouts 参见 TimeoutsTest
     */

    /**
     * Per-call Configuration 每次请求时进行配置(代理、超时和缓存)
     *
     * @return
     */
    public String perCallConfiguration() {
        String responseString = new String();

        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/1")//This Url is served with a 1 second delay.
                .build();

        //Copy to customize OkHttp for this request.
        OkHttpClient client1 = client.newBuilder()
                .readTimeout(500, TimeUnit.MILLISECONDS)
                .build();
        try (Response response = client1.newCall(request).execute()) {
            LogUtil.e("Response 1 succeeded:" + response);
            responseString = "第一次请求：" + response.body().string() + "\n";
        } catch (Exception e) {
            LogUtil.e("Response 1 failed:" + e);
        }

        //Copy to customize OkHttp for this request.
        OkHttpClient client2 = client.newBuilder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .build();

        try (Response response = client2.newCall(request).execute()) {
            LogUtil.e("Response 2 succeeded:" + response);
            responseString = responseString + "第二次请求:" + response.body().string() + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }

    /**
     * EventListener
     * 为请求设置监听(这里主要是用于监听请求各个阶段的耗时)
     */
    public String eventListener() throws Exception{
        String responseString = new String();

        //1.设置监听
        OkHttpClient client = new OkHttpClient.Builder()
                .eventListener(new PrintingEventListener())
                .build();

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        LogUtil.e("Request 1 (new Connection)");

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            responseString = response.body().string();
            LogUtil.e("我是响应内容：" + responseString);
        } catch (Exception e){
            e.printStackTrace();
        }

        return responseString;
    }


    /**
     * EventListener.Factory
     * 1.This sample factory creates a unique ID for each call and uses that ID to differentiate calls in log messages.
     * 2.同时有多个请求，为每个请求设置 callId 和 各自的初始时间 callStartNanos.
     */
    public String eventListenerFactory() throws Exception{
        String responseString;

        long callStartNanos = System.nanoTime();

        long callId = 0001;

        //1.设置监听
        OkHttpClient client = new OkHttpClient.Builder()
                .eventListener(new PrintEventListenerFactory(callId,callStartNanos))
                .build();

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            responseString = response.body().string();
            LogUtil.e("我是响应内容：" + responseString);
        }
        return responseString;
    }


    /*******************************以下是与请求本身关,但是需要用到的东西***********************************/


    /**
     * Parse a Json Response With Moshi 解析使用的类
     */
    static class Gist {
        Map<String, GistFile> files;
    }

    /**
     * Parse a Json Response With Moshi 解析使用的类
     */
    static class GistFile {
        String content;
    }
}
