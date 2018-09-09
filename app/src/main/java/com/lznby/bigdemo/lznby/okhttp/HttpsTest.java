package com.lznby.bigdemo.lznby.okhttp;

/**
 * @author Lznby
 * @time 2018/9/8 20:36
 * Class Note:
 * 1.Https 与 Pinning(证书)
 */


import com.lznby.bigdemo.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.Collections;

import javax.net.ssl.SSLContext;

import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;

/**
 * Specific security vs. connectivity decisions are implemented by ConnectionSpec. OkHttp includes four built-in connection specs:
 * 1.RESTRICTED_TLS is a secure configuration, intended to meet stricter compliance requirements.
 * 2.MODERN_TLS is a secure configuration that connects to modern HTTPS servers.
 * 3.COMPATIBLE_TLS is a secure configuration that connects to secure–but not current–HTTPS servers.
 * 4.CLEARTEXT is an insecure configuration that is used for http:// URLs.
 */
public class HttpsTest {

    /**
     * By default, OkHttp will attempt a MODERN_TLS connection. However by configuring the client
     * connectionSpecs you can allow a fall back to COMPATIBLE_TLS connection if the modern
     * configuration fails.
     */
    /**
     * 此处使用static修饰是因为有静态类中要调用,实际使用是不需要的。
     */
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
            .build();

    /********************************************以上为普通写法****************************************/


    /**
     * 1.使用条件服务器支持Https、且Android在5.0+
     */
    ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            )
            .build();

    /**
     * newBuild其实可以用Build的,但是应为上面已经用了Build,所以此处用了newBuild
     */
    OkHttpClient client1 = new OkHttpClient().newBuilder()
            .connectionSpecs(Collections.singletonList(spec))
            .build();

    /*************************************以上为最常用的写法***************************************/


    /**
     * https request 1
     *
     * @return
     * @throws Exception
     */
    public String httpsRequest1() throws Exception {
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
     * https request 2
     *
     * @return
     * @throws Exception
     */
    public String httpsRequest2() throws Exception {
        String responseString;
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
        try (Response response = client1.newCall(request).execute()) {
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


    /*******************************************以下为证书相关***************************************/

    /**
     * 1.如果服务器不支持Https的就不要使用证书设置了
     */

    /**
     * Certificate Pinning (固定证书)
     */
    static class CertificatePinning {

        String responseString = new String();

        /**
         * 固定证书
         */
        public CertificatePinning() {

            client = new OkHttpClient.Builder()
                    .certificatePinner(new CertificatePinner.Builder()
                            .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                            .build())
                    .build();
        }

        /**
         * 固定证书使用
         *
         * @throws Exception
         */
        public String run() throws Exception {
            Request request = new Request.Builder()
                    .url("https://publicobject.com/robots.txt")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                responseString = response.body().string();

                for (Certificate certificate : response.handshake().peerCertificates()) {
                    LogUtil.e("证书：" + CertificatePinner.pin(certificate));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseString;
        }
    }


    /**
     * Customizing Trusted Certificates (自定义信任证书)
     */

    class CustomTrust {
        private final OkHttpClient client;

        public CustomTrust() {
            SSLContext sslContext = sslContextForTrustedCertificates(trustedCertificatesInputStream());
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .build();
        }

        public void run() throws Exception {
            Request request = new Request.Builder()
                    .url("https://publicobject.com/helloworld.txt")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }

        private InputStream trustedCertificatesInputStream() {
            //...
            // Full source omitted. See sample.
            //此处为return null 事实上已改return InputStream
            return null;
        }

        public SSLContext sslContextForTrustedCertificates(InputStream in) {
            //...
            // Full source omitted. See sample.
            //此处为return null 事实上已改return SSLContext
            return null;
        }
    }


}
