package com.lznby.bigdemo.lznby.okhttp;

/**
 * @author Lznby
 * @time 2018/9/8 19:12
 * Class Note:
 * 1.Rewriting Responses
 * 2.使用拦截器重写Response
 */

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Dangerous interceptor that rewrites the server's cache-control header.
 */
public class RewritingResponsesTest {
    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control","max-age=60")
                    .build();
        }
    };
}
