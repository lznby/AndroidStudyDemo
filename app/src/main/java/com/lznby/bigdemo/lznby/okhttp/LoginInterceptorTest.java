package com.lznby.bigdemo.lznby.okhttp;

import com.lznby.bigdemo.utils.LogUtil;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/8 9:50
 * Class Note:
 * 1.Interceptors(拦截器)
 */
public class LoginInterceptorTest {

    /**
     * 拦截器类(实现拦截器接口)
     * 1.这里最要是打印日志功能。
     */
    class LoggerInterceptor implements Interceptor {

        Logger logger = Logger.getAnonymousLogger();

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();

            logger.info(String.format("Sending Request %s on %s%n%s",request.url(),chain.connection(),request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();

            logger.info(String.format("Received response for %s in %.1fms%n%s",response.request().url(),(t2-t1) / 1e6d,response.headers()));

            return response;
        }
    }

    /**
     * Application Interceptors
     * @return
     * @throws IOException
     */
    public String applicationInterceptors() throws IOException {

        String responseString = new String();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent","OkHttp Example")
                .build();


        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                throw  new IOException("Unexpected Code" + response);
            }

            responseString = response.body().string();

            LogUtil.e("请求结果："+responseString);

            response.body().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;

    }

    /**
     * Network Interceptors
     * @return
     */
    public String networkInterceptors() throws Exception{

        String responseString = new String();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggerInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent","OkHttp Example")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }

            responseString = response.body().string();

            LogUtil.e("请求结果：" + responseString);

            response.body().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }

    /**
     * rewriting Request
     */
    public String rewritingRequest() throws Exception{

        String responseString = new String();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(RewritingResponsesTest.REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent","OkHttp Example")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }

            responseString = response.body().string();

            LogUtil.e("请求结果：" + responseString);

            response.body().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }

    /**
     * rewriting Response
     */
    public String rewritingResponse() {

        String responseString = new String();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new RewritingRequestsInterceptorTest())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent","OkHttp Example")
                .build();

        try (Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected Code" + response);
            }

            responseString = response.body().string();

            LogUtil.e("请求结果：" + responseString);

            response.body().close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseString;
    }


}
