package com.lznby.bigdemo.rxjava2;


import com.lznby.bigdemo.utils.LogUtil;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author Lznby
 * @time 2018/7/16 19:20
 * Class Note: RxJava2——几种被观察者及观察者
 *   被观察者                观察者
 * 1.Flowable               FlowableSubscriber
 * 2.Observable             Observer
 * 3.Single                 SingleObserver
 * 4.Completable            CompletableObserver
 * 5.Maybe                  MaybeObserver
 *
 * 5种订阅方式的使用条件：
 * 1.FlowableSubscript
 * 2.Observable
 * 3.Single
 * 4.Completable
 * 5.Maybe
 *
 * 订阅模式：
 * 1.subscript
 * 2.actions
 *
 * FlowableSubscriber 和 Observable 区别：
 * 1.FlowableSubscriber支持背压,Observable不支持背压;
 *
 * 背压(BackPressure):
 * 1.背压就是生产者（被观察者）的生产速度大于消费者（观察者）消费速度从而导致的问题。
 * 2.如果被观察者快速发送消息，但是观察者处理消息的很缓慢，如果没有特定的流（Flow）控制，就会导致大量消息积压占用系统资源，最终导致十分缓慢。
 * 3.Flowable创建的时候已经设置了BackpressureStrategy，而且Subscriber使用了request来控制最大的流量。
 *
 * RxJava组成：
 * RxJava基于观察者模式，主要有四个部分：观察者、被观察者、订阅、事件。
 *
 * RxJava定义：
 * Rx是ReactiveX的缩写，而ReactiveX是Reactive Extensions的缩写。RxJava顾名思义即使Java上的异步和基于事件响应式编程库。
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
        //1、创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //4.订阅观察者时的操作(订阅的事件)
                emitter.onNext("test1");
                emitter.onNext("test2");
                emitter.onComplete();
            }
        });//没有被压（消费速度小于生产速度）设置

        //2、创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //订阅时的操作，无需request
                LogUtil.e("onSubscript:");
            }

            @Override
            public void onNext(String s) {
                //onNext事件处理
                LogUtil.e("onNext:" + s);
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

        //3.观察者订阅被观察者
        observable.subscribe(observer);
    }

    /**
     * Observable
     * 使用Consumer来作为观察者——我们只关心onNext方法,可以少重载subscribe中的方法
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

        observable.subscribe(//观察者
                new Consumer<String>() {
                    //相当于onNext，处理业务
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtil.e("onNext:" + s);
                    }
                }, new Consumer<Throwable>() {
                    //相当于onError
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("onError:");
                    }
                }, new Action() {
                    // 相当于是onComplete,注意这里是Action
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    // 订阅时的操作???
                    public void accept(Disposable disposable) throws Exception {

                    }
                }
        );
    }

    /**
     * Single和SingleObserver
     * <p>
     * 如果你使用一个单一连续事件流，即只有一个onNext事件，接着就触发onComplete或者onError，这样你可以使用Single。
     * <p>
     * Single特点：
     * 1.Single只包含两个事件，一个是正常处理成功的onSuccess，另一个是处理失败的onError，它只发送一次消息
     * （当然就不存在背压问题），其中Single类似于Observable。
     * 2.  这里就不在展开了。另外Single也可以直接转换成Flowable或者Observable:
     * single.toFlowable();
     * single.toObservable();
     * 3.当然也有单一Actions的模式：
     * single.subscribe(onSuccess)
     * single.subscribe(onSuccess,onError)
     */
    public static void singleSubscript() {
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {//被观察者
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("test1");
                //下一行为错误写法，重复调用不会执行
                //emitter.onSuccess("test2");
            }
        });
        //订阅观察者SingleObserver
        single.subscribe(new SingleObserver<String>() {//观察者
            @Override
            public void onSubscribe(Disposable d) {
                //订阅
            }

            @Override
            public void onSuccess(String s) {
                //相当于onNext和onComplete
            }

            @Override
            public void onError(Throwable e) {
                //错误处理
            }
        });
    }

    /**
     * Single Actions模式
     * <p>
     * 当然也有单一Actions的模式：
     * single.subscribe(onSuccess)
     * single.subscribe(onSuccess,onError)
     */
    public static void singleActions() {
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {//被观察者
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("test1");
                //下一行为错误写法，重复调用不会执行
                //emitter.onSuccess("test2");
            }
        });

        single.subscribe(//观察者
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //相当于onNext
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //相当于onError
                    }
                }
        );
    }

    /**
     * Completable和CompletableObserver
     * <p>
     * 如果你的观察者连onNext事件都不关心，你可以使用Completable，他只有onComplete和onError两个事件：
     * <p>
     * 同样也可以使用Actions来简化Observer：
     * completable.subscribe(onComplete)
     * completable.subscribe(onComplete,onError)
     * <p>
     * 要转换成其他类型的被观察者，也是可以使用toFlowable()、toObservable()等方法去转换。
     */
    public static void completableSubscript() {
        Completable.create(new CompletableOnSubscribe() {//被观察者
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();//单一onComplete或onError
            }
        }).subscribe(new CompletableObserver() {//观察者
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * Completable Actions模式
     * <p>
     * 同样也可以使用Actions来简化Observer：
     * completable.subscribe(onComplete)
     * completable.subscribe(onComplete,onError)
     * <p>
     * 要转换成其他类型的被观察者，也是可以使用toFlowable()、toObservable()等方法去转换。
     */
    public static void completableActions() {
        Completable.create(new CompletableOnSubscribe() {//被观察者
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onComplete();//单一onComplete或onError
            }
        }).subscribe(//观察者
                new Action() {
                    @Override
                    public void run() throws Exception {
                        //相当于onComplete
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //相当于onError
                    }
                }
        );
    }

    /**
     * Maybe和MaybeObserver
     * <p>
     * 如果你有一个需求是可能发送一个数据或者不会发送任何数据，这时候你就需要Maybe，它类似于Single和Completable的混合体。
     * <p>
     * Maybe可能会调用以下其中一种情况（也就是所谓的Maybe）：
     * onSuccess或者onError
     * onComplete或者onError
     * 可以看到onSuccess和onComplete是互斥的存在
     * <p>
     * 要转换成其他类型的被观察者，也是可以使用toFlowable()、toObservable()等方法去转换。
     */
    public static void maybeSubscript() {
        //1.被观察者
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                //发送一个数据的情况，或者onError，不需要再调用onComplete(调用了也不会触发onComplete回调方法)
                emitter.onSuccess("test1");
                //emitter.onComplete();//不需要数据的情况，或者onError
            }
        });

        //2.订阅观察者
        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                //发送一个数据时，相当于onNext和onComplete，但不会触发另一个方法的onComplete
                LogUtil.e("onSuccess:" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //无数据发送时候的onComplete
                LogUtil.e("onComplete");
            }
        });
    }

    /**
     * Maybe Actions模式
     * <p>
     * 同样可以是用Actions来简化Observer:
     * maybe.subscribe(onSuccess);
     * maybe.subscribe(onSuccess,onError);
     * maybe.subscribe(onSuccess,onError,onComplete);
     * <p>
     * 要转换成其他类型的被观察者，也是可以使用toFlowable()、toObservable()等方法去转换。
     */
    public static void maybeActions() {
        Maybe<String> maybe = Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                emitter.onSuccess("test");
            }
        });
        maybe.subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //发送一个数据时，相当于onNext和onComplete，但不会触发另一个方法的onComplete
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //相当于onError
                    }
                }
        );
    }
}
