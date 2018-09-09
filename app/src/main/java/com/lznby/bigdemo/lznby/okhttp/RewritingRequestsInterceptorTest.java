package com.lznby.bigdemo.lznby.okhttp;

/**
 * @author Lznby
 * @time 2018/9/8 10:40
 * Class Note:
 * 1.用于重写请求的拦截器
 * 2.Rewriting Requests
 */

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * This interceptor compresses the Http request body. Many web servers can't handle this.
 */
public final class RewritingRequestsInterceptorTest implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") !=null ) {
            return chain.proceed(originalRequest);
        }

        Request compressedRequest = originalRequest.newBuilder()
                .header("Content-Encoding","gzip")
                .method(originalRequest.method(),gzip(originalRequest.body()))
                .build();

        return chain.proceed(compressedRequest);
    }

    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }

            @Override
            public long contentLength() throws IOException {
                return -1;// We don't know the compressed length in advance!
            }
        };
    }
}
