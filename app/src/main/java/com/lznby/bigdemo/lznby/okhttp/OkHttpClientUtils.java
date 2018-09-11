package com.lznby.bigdemo.lznby.okhttp;

/**
 * @author Lznby
 * @time 2018/9/11 19:51
 * Class Note:
 */

import android.support.annotation.Nullable;

import com.lznby.bigdemo.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.TlsVersion;

/**
 * @author Lznby
 * @time 2018/9/9 17:25
 * Class Note:
 * 1.OkHttpsClient 拥有的功能汇总。
 */
public enum OkHttpClientUtils {
    /**
     * 枚举
     */
    INSTANCE;

    /**
     * 设置缓存大小
     */
    int cacheSize =10*1024*1024;

    /**
     * 设置缓存文件及大小
     */
    Cache cache = new Cache(new File("filepath"),cacheSize);

    /**
     * 设置连接方式:
     * 1.使用条件服务器支持Https、且Android在5.0+
     */
    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            ).build();

    /**
     * 设置连接方式
     * 1.最普通的连接方式
     */
    List<ConnectionSpec> listSpec = Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS);


    /**
     * 认证身份信息
     */
    Authenticator authenticator = new Authenticator() {
        @Nullable
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            if (response.request().header("Authorization")!=null) {
                //Give up, we've already attempted to authenticate.
                return null;
            }
            LogUtil.e("Authenticating for response:" + response);
            LogUtil.e("Challenges:" + response.challenges());
            String credential = Credentials.basic("jesse", "password1");
            return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
        }
    };



    /**
     * OkHttpClient
     * @return
     */
    public OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                //设置超时
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                //设置拦截器
                .addInterceptor(new LoggerInterceptor())
                //                .addNetworkInterceptor(new RequestsInterceptor())
                .addNetworkInterceptor(RewritingResponsesTest.REWRITE_CACHE_CONTROL_INTERCEPTOR)
                //设置缓存
                //                .cache(cache)
                //设置时间监听
                //                .eventListener(new PrintEventListenerFactory(0001,System.nanoTime()))
                //设置连接方式与Https有关
                //                .connectionSpecs(Collections.singletonList(spec))
                //                .connectionSpecs(listSpec)
                //绑定证书
                //                .certificatePinner(new CertificatePinner.Builder()
                //                        .add("publicobject.com","sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                //                        .build()
                //                )
                //设置处理身份认证信息
                //                .authenticator(authenticator)
                //自动保存cookies,下次请求自动加上
                //                .cookieJar(new CookieJar() {
                //                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
                //
                //                    @Override
                //                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                //                        cookieStore.put(url.host(), cookies);
                //                    }
                //                    @Override
                //                    public List<Cookie> loadForRequest(HttpUrl url) {
                //                        List<Cookie> cookies = cookieStore.get(url.host());
                //                        return cookies != null ? cookies : new ArrayList<Cookie>();
                //                    }
                //                })
                ;

        return httpClientBuilder.build();

    }
}
