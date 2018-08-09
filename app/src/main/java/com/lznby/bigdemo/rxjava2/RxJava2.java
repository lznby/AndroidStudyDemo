package com.lznby.bigdemo.rxjava2;


import com.lznby.bigdemo.utils.LogUtil;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Lznby
 * @time 2018/7/16 19:20
 * Class Note: RxJava2——FlowableSubscribe
 */
public class RxJava2 {

    /**
     * FlowableSubscript:
     * onNext按顺序执行执行顺序如下：
     * onNext -> onNext ... -> onComplete
     * onNext -> onNext ... -> onError
     */
    public static void flowableSubscribe() {
        //1、创建观察者
        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                //订阅时候的操作

                //请求多少事情，这里表示不限制
                s.request(Long.MAX_VALUE);
                LogUtil.e("onSubscribe事件");
            }

            @Override
            public void onNext(String s) {
                //onNext事件处理,处理业务
                LogUtil.e("onNext事件:" + s);
            }

            @Override
            public void onError(Throwable t) {
                //onError
                LogUtil.e("onError事件");
            }

            @Override
            public void onComplete() {
                //onComplete
                LogUtil.e("onComplete事件");
            }
        };

        //2、被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onNext("test3");
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //3、被观察者订阅观察者
        flowable.subscribe(subscriber);
    }

    /**
     * FlowableSubscript:
     * 使用Consumer来作为观察者——我们只关心onNext方法,可以少重载subscribe中的方法
     */
    public static void flowableActions() {

        //1、被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onNext("test3");
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //2、订阅观察者
        flowable.subscribe(
                new Consumer<String>() {
                    //相当于onNext,处理业务
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.e("Consumer:" + s);
                    }
                }, new Consumer<Throwable>() {
                    //相当于onError，处理错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("Consumer<Throwable>:");
                    }
                }, new Action() {
                    //相当于onComplete，注意这里是Action
                    @Override
                    public void run() throws Exception {
                        LogUtil.e("Action:");
                    }
                }, new Consumer<Subscription>() {
                    //相当于onSubscribe
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        //请求多少事情，这里表示不限制
                        subscription.request(Long.MAX_VALUE);
                        LogUtil.e("Consumer<Subscription>:");

                    }
                });
    }

    /**
     * Observable和Observer
     */
    public static void observableSubscript() {
        //1、被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //订阅观察者时的操作
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onComplete();
            }
        });//没有被压（消费速度小于生产速度）设置

        //2、观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //订阅时的操作，无需request
                LogUtil.e("onSubscript:");
            }

            @Override
            public void onNext(String s) {
                //onNext事件处理
                LogUtil.e("onNext:"+s);
            }

            @Override
            public void onError(Throwable e) {
                //onError事件处理
                LogUtil.e("onError");
            }

            @Override
            public void onComplete() {
                //onComplete事件处理
                LogUtil.e("onComplete:");
            }
        };

        observable.subscribe(observer);
    }

    /**
     *
     */
    public static void observableActions() {
        //1、被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //订阅观察者时的操作
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onComplete();
            }
        });//没有被压（消费速度小于生产速度）设置

        observable.subscribe(
                new Consumer<String>() {
                    //相当于onNext，处理业务
                    @Override
                    public void accept(String s) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    //相当于onError
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }
        );
    }
}
