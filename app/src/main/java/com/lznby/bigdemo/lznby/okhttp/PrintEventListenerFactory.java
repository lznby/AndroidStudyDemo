package com.lznby.bigdemo.lznby.okhttp;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lznby
 * @time 2018/9/9 9:45
 * Class Note:
 * 1.EventListener Factory
 */
public class PrintEventListenerFactory extends EventListener {

    public static final Factory FACTORY = new Factory() {
        final AtomicLong nextCallId = new AtomicLong(1L);
        @Override
        public EventListener create(Call call) {
            long callId = nextCallId.getAndIncrement();
            System.out.printf("%04d %s%n", callId, call.request().url());
            return new PrintEventListenerFactory(callId, System.nanoTime());
        }
    };

    final long callId;

    final long callStartNanos;

    public PrintEventListenerFactory(long callId, long callStartNanos) {
        this.callId = callId;
        this.callStartNanos = callStartNanos;
    }

    private void printEvent(String name) {
        long elapsedNanos = System.nanoTime() - callStartNanos;
        System.out.printf("%04d %.3f %s%n", callId, elapsedNanos / 1000000000d, name);
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
