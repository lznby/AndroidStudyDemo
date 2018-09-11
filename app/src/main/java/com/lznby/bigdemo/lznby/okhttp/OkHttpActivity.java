package com.lznby.bigdemo.lznby.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.tools.ARouterTools;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Lznby
 * Note:
 * 1.okhttp的基本使用
 * 2.参见:https://github.com/square/okhttp/wiki
 */
@Route(path = ARouterTools.OkHttpActivity)
public class OkHttpActivity extends AppCompatActivity {

    @BindView(R.id.tv_show_response)
    TextView mTvShowResponse;

    /**
     * 用于存放请求结果
     */
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_synchronous_get, R.id.bt_asynchronous_get, R.id.bt_accessing_headers,
            R.id.bt_post_string, R.id.bt_post_streaming, R.id.bt_post_file, R.id.bt_post_parameters,
            R.id.bt_post_multipart, R.id.bt_post_parse_json, R.id.bt_response_caching,
            R.id.bt_canceling_call, R.id.bt_timeouts, R.id.bt_per_call_configuration,
            R.id.bt_handling_authentication, R.id.bt_application_interceptors,R.id.bt_network_interceptors,
            R.id.bt_rewriting_requests,R.id.bt_rewriting_responses,R.id.bt_https_request_1,
            R.id.bt_https_request_2,R.id.bt_certificate_pinning,R.id.bt_event_listener,
            R.id.bt_event_listener_factory,
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_synchronous_get:
                //同步请求
                runSynchronousGet();
                break;
            case R.id.bt_asynchronous_get:
                //异步请求
                runAsynchronousGet();
                break;
            case R.id.bt_accessing_headers:
                //添加请求头
                runAccessingHeaders();
                break;
            case R.id.bt_post_string:
                //Post String
                runPostString();
                break;
            case R.id.bt_post_streaming:
                //Post Streaming
                runPostStreaming();
                break;
            case R.id.bt_post_file:
                //Post File
                runPostFile();
            case R.id.bt_post_parameters:
                //
                runPostFromParameters();
                break;
            case R.id.bt_post_multipart:
                //
                runMultipartRequest();
                break;
            case R.id.bt_post_parse_json:
                //
                runParseJsonResponseWithMoshi();
                break;
            case R.id.bt_response_caching:
                //
                runResponseCaching();
                break;
            case R.id.bt_canceling_call:
                //
                runCancelingCall();
                break;
            case R.id.bt_timeouts:
                //
                runTimeOuts();
                break;
            case R.id.bt_per_call_configuration:
                //
                runPerCallConfiguration();
                break;
            case R.id.bt_handling_authentication:
                //
                runHandlingAuthentication();
                break;
            case R.id.bt_application_interceptors:
                //
                runApplicationInterceptors();
                break;
            case R.id.bt_network_interceptors:
                //
                runNetworkInterceptors();
                break;
            case R.id.bt_rewriting_requests:
                //
                runRewritingRequest();
                break;
            case R.id.bt_rewriting_responses:
                //
                runRewritingResponse();
                break;
            case R.id.bt_https_request_1:
                //
                runHttpsRequest1();
                break;
            case R.id.bt_https_request_2:
                //
                runHttpsRequeset2();
                break;
            case R.id.bt_certificate_pinning:
                //
                runCertificatePinning();
                break;
            case R.id.bt_event_listener:
                //
                runEventListener();
                break;
            case R.id.bt_event_listener_factory:
                //
                runEventListenerFactory();
                break;
            default:
                break;
        }
    }

    /**
     * Synchronous Get (同步请求)
     * 1.请求结果可以显示在TextView
     */
    private void runSynchronousGet() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.synchronousGet();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Asynchronous Get (异步请求)
     * 结果：
     * 1.请求结果不可以显示在TextView
     * 原因：
     * 1.由于是异步的请求所以还未请求到结果已经return,所以返回return的结果中没有包含网络请求结果。
     */
    private void runAsynchronousGet() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.asynchronousGet();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Accessing Headers (添加请求头)
     */
    private void runAccessingHeaders() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.accessingHeaders();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Posting a String
     */
    private void runPostString() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.postString();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Post Streaming
     */
    private void runPostStreaming() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.postStreaming();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Posting a File
     */
    private void runPostFile() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.postFile();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Posting form parameters
     */
    private void runPostFromParameters() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.postFromParameters();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Posting a multipart request
     */
    private void runMultipartRequest() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.postMultipartRequest();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Parse a JSON Response With Moshi.
     */
    private void runParseJsonResponseWithMoshi() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.parseJsonResponseWithMoshi();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Response Caching
     */
    private void runResponseCaching() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpCacheTest okHttpCacheTest = new OkHttpCacheTest(new File("cache.txt"));
                            try {
                                response = okHttpCacheTest.responseCaching();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Canceling a Call
     */
    private void runCancelingCall() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.cancelingCall();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Timeouts
     */
    private void runTimeOuts() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            TimeoutsTest timeoutsTest = new TimeoutsTest();
                            try {
                                response = timeoutsTest.timeouots();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Per-call Configuration
     */
    private void runPerCallConfiguration() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.perCallConfiguration();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Handling authentication
     */
    private void runHandlingAuthentication() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o -> {
                            HandlingAuthenticationTest okHttpTest = new HandlingAuthenticationTest();
                            try {
                                response = okHttpTest.handlingAuthentication();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Application Interceptors
     */
    private void runApplicationInterceptors() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o-> {
                            LoginInterceptorTest logginInterceptorTest = new LoginInterceptorTest();
                            try {
                                response = logginInterceptorTest.applicationInterceptors();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Network Interceptors
     */
    private void runNetworkInterceptors() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            LoginInterceptorTest logginInterceptorTest = new LoginInterceptorTest();
                            try {
                                response = logginInterceptorTest.networkInterceptors();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Rewriting Request
     */
    private void runRewritingRequest() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o-> {
                            LoginInterceptorTest loginInterceptorTest = new LoginInterceptorTest();
                            try {
                                response = loginInterceptorTest.rewritingRequest();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Rewriting Responses
     */
    private void runRewritingResponse() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            LoginInterceptorTest logginInterceptorTest = new LoginInterceptorTest();
                            try {
                                response = logginInterceptorTest.rewritingResponse();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Https Request 1
     */
    private void runHttpsRequest1() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            HttpsTest httpsTest = new HttpsTest();
                            try {
                                response = httpsTest.httpsRequest1();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Https Request 2
     */
    private void runHttpsRequeset2() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            HttpsTest httpsTest = new HttpsTest();
                            try {
                                response = httpsTest.httpsRequest2();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * Certificate Pinning
     */
    private void runCertificatePinning() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            HttpsTest.CertificatePinning certificatePinning = new HttpsTest.CertificatePinning();
                            try {
                                response = certificatePinning.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * EventListener(设置监听)
     */
    private void runEventListener() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.eventListener();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

    /**
     * EventListener.Factory
     */
    private void runEventListenerFactory() {
        Observable.just("")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(
                        o->{
                            OkHttpTest okHttpTest = new OkHttpTest();
                            try {
                                response = okHttpTest.eventListenerFactory();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        o->mTvShowResponse.setText(response)
                )
                .subscribe();
    }

}
