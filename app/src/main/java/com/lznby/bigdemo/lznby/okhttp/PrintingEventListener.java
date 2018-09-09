package com.lznby.bigdemo.lznby.okhttp;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/8 21:55
 * Class Note:
 * 1.Events
 * 2.对请求的各个环节进行监听,打印使用时间和所占内存资源等,便于改进网络请求。
 * 3.以下重写方法都是按HTTP请求的顺序来的。
 * 4.以下重写方法只是用于打印请求各阶段的耗时情况。
 */
public class PrintingEventListener extends EventListener {
    private long callStartNanos;

    private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if (name.equals("callStart")) {
            callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d,name);
    }

    @Override
    public void callStart(Call call) {
        printEvent("callStart");
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        printEvent("dnsStart");
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        printEvent("dnsEnd");
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        printEvent("connectStart");
    }

    @Override
    public void secureConnectStart(Call call) {
        printEvent("secure connect start");
    }

    @Override
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        printEvent("secure connect end");
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        printEvent("connect failed");
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        printEvent("connect end");
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        printEvent("connection acquired");
    }

    @Override
    public void requestHeadersStart(Call call) {
        printEvent("request headers start");
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        printEvent("request headers end");
    }

    @Override
    public void requestBodyStart(Call call) {
        printEvent("request Body start");
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        printEvent("request Body end");
    }

    @Override
    public void responseHeadersStart(Call call) {
        printEvent("response headers start");
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        printEvent("response headers end");
    }

    @Override
    public void responseBodyStart(Call call) {
        printEvent("response body start");
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        printEvent("response body end");
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        printEvent("connection released");
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        printEvent("call Failed");
    }

    @Override
    public void callEnd(Call call) {
        printEvent("call end");
    }

}
