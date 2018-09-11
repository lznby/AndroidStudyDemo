package com.lznby.bigdemo.lznby.okhttp;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/10 19:36
 * Class Note:
 * 1.简单的拦截器
 */
public class LoggerInterceptor implements Interceptor {

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