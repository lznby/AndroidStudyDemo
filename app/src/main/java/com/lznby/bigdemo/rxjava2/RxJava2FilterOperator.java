package com.lznby.bigdemo.rxjava2;

import com.lznby.bigdemo.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;

/**
 * @author Lznby
 * @time 2018/8/14 10:43
 * Class Note: RxJava 过滤操作符
 *
 * 上篇说到RxJava创建操作符，这一节来说说过滤操作符，还记得上节中说到interval操作符如果没有其他限制的话就会
 * 无限发送onNext事件，永远也不会调用onComplete事件，如果需要限制的话，可以使用一些过滤操作符进行限制。
 */
public class RxJava2FilterOperator {
    /**
     * filter操作符
     * <p>
     * 作用：
     * 1.先说一个基本的过滤操作符filter，可以自己设定任意的规则来过滤数据，比如过滤出大于等于2的元素
     */
    public static void filterFilterOperator() {
        Flowable.just(1, 2, 3)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //过滤出>=2的数据
                        return integer >= 2;
                    }
                }).subscribe(integer -> LogUtil.e("tag:" + String.valueOf(integer)));
        //上面的结果只会输出2和3，因为boolean test(T t)方法返回true则表示元素数据有效，否则则为无效抛弃。
    }

    /**
     * take操作符
     * <p>
     * 作用:
     * 1.如果需要使用类似request(long)的方法来限制发射数据的数量，可以使用take操作符
     */
    public static void takeFilterOperator() {

        //如果需要使用类似request(long)的方法来限制发射数据的数量，可以使用take操作符：
        Flowable.interval(1, TimeUnit.SECONDS)
                //只发送5个元素
                .take(5)
                .subscribe(integer -> LogUtil.e("tag:" + String.valueOf(integer)));

        //take操作符可以采用时间过滤，例如过滤出5秒之内发送的数据：
        Flowable.interval(1, TimeUnit.SECONDS)
                //5秒之内的数据（这里输出0,1,2,3）
                .take(5, TimeUnit.SECONDS)
                .subscribe(integer -> LogUtil.e("tag:" + String.valueOf(integer)));
    }

    /**
     * takeLast操作符
     * <p>
     * 作用：
     * 1.如果要筛选出最后几个元素的话可以使用takeLast操作符，比如选取最后3个元素：
     * 2.另外使用takeLast操作符筛选时间，可以增加delayError参数(不传默认为false)takeLast(3, TimeUnit.SECONDS, true)来延迟筛选过程中接收到的error。
     * <p>
     * 特点使用范围：
     * 1.使用takeLast要特别注意，首先元素数量是可数的，由于takeLast使用的是buffer，所以过滤后的数据会一次性发送（而不是按照例如intervalRange设定的方式发送），当然这里可以指定takeLast使用的bufferSize。
     */
    public static void takLastFilterOperator() {
        //如果要筛选出最后几个元素的话可以使用takeLast操作符，比如选取最后3个元素：
        Flowable.just(1, 2, 3, 4, 500)
                //只发送最后3个元素
                .takeLast(3)
                .subscribe(integer -> LogUtil.e("tag:" + String.valueOf(integer)));


        //也可以通过时间来筛选，比如筛选出最后三秒的数据：
        Flowable.intervalRange(0, 10, 1, 1, TimeUnit.SECONDS)
                //只发送最后三秒的数据
                .takeLast(3, TimeUnit.SECONDS)
                .subscribe(integer -> LogUtil.e("tag:" + String.valueOf(integer)));
    }

    /**
     * firstElement/lastElement操作符
     * <p>
     * 作用：
     * 1. 如果需要选取第一个元素(允许为空)，可以使用firstElement操作符：
     * 2. 同理，如果要选取最后一个元素(允许为空)，可以使用lastElement操作符：
     */
    public static void firstElementAndLastElementFilterOperator() {

        //如果需要选取第一个元素(允许为空)，可以使用firstElement操作符：
        Flowable.just(1, 2, 3)
                .firstElement()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));

        //同理，如果要选取最后一个元素(允许为空)，可以使用lastElement操作符：
        Flowable.just(1, 2, 3)
                .lastElement()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
    }

    /**
     * first/last操作符
     * <p>
     * 作用：
     * 1. 如果要设置一个默认值（当被观察者不发射任何元素的时候）可以使用first操作符：
     * 2. 同理如果要设置lastElement为空时发送的元素默认值，可以使用last操作符：
     */
    public static void firstAndLastFilterOperator() {

        //如果要设置一个默认值（当被观察者不发射任何元素的时候）可以使用first操作符：
        Flowable.empty()
                //这里的2是默认元素,而非第二个元素
                .first(2)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述的代码将会输入“2”这个元素。

        //同理如果要设置lastElement为空时发送的元素默认值，可以使用last操作符：
        Flowable.empty()
                .last(3)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述的代码将会输入“3”这个元素。
    }

    /**
     * firstOrError/lastOrError操作符
     * <p>
     * 作用：
     * 1.上述说到的firstElement操作符，如果为空元素的时候不会发生任何异常，如果你需要在空的时候抛出异常，可以使用firstOrError操作符：
     * 2.上述说到的lastElement操作符也是遇到空元素也是不会发生任何的异常，如果你需要在空的时候抛出异常，可以使用lastOrError操作符：
     */
    public static void firstOrErrorAndlastOrErrorFilterOperator() {
        Flowable.empty()
                .firstOrError()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码将会抛出异常。


        Flowable.empty()
                .lastOrError()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码将会抛出异常。
    }

    /**
     * elementAt / elementAtOrError操作符
     * <p>
     * 作用：
     * 1.如果需要指定发射第几个元素（注意这里的参数为索引值），可以使用elementAt操作符（支持越界）
     * 2.如果希望越界后抛出异常，可以使用elementAtOrError操作符：
     */
    public static void elementAtAndelementAtOrErrorFilterOperator() {

        //如果需要指定发射第几个元素（注意这里的参数为索引值），可以使用elementAt操作符（支持越界）
        Flowable.just("a", "b", "c")
                //指定索引为2的元素，如果不存在则直接完成
                .elementAt(2)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));


        //如果需要设置越界后发送的默认元素，可以添加额外默认值参数：
        Flowable.just("a", "b", "c")
                //指定索引为4的元素，若果不存在则发送“d”
                .elementAt(4, "d")
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));


        //如果希望越界后抛出异常，可以使用elementAtOrError操作符：
        Flowable.just("a","b","c")
                //指定索引值为3的元素,如果不存在则抛出异常
                .elementAtOrError(3)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
    }

    /**
     * ofType操作符
     *
     * 作用：假设你需要筛选特定类型的数据，可以采用ofType操作符：
     */
    public static void ofTypeFilterOperator() {
        Flowable.just("a",1,2,"b")
                .ofType(Integer.class)
                .subscribe(integer -> LogUtil.e("tag:"+String.valueOf(integer)));
        //上述代码只会输出1、3,其他元素被抛弃
    }

    /**
     * skip/skipLast操作符
     *
     * 作用：
     * 1. 如果需要跳过若干个元素，或者跳过一段时间，可以使用skip或者skipLast操作符。下面是跳过若干个元素的例子：
     * 2.skip应该是跳过开头
     * 3.skipLast应该是跳过尾
     *
     */
    public static void skipAndskipLastFilterOperator() {

        //如果需要跳过若干个元素，或者跳过一段时间，可以使用skip或者skipLast操作符。下面是跳过若干个元素的例子：
        Flowable.just("a","b","c")
                .skip(1)
                .skipLast(1)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

        //假设需要跳过一段时间，可以使用重载方法：
        Flowable.intervalRange(0,10,1,1,TimeUnit.SECONDS)
                .skip(3,TimeUnit.SECONDS)
                .skipLast(3,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

    }


    /**
     * ignoreElements操作符
     *
     * 作用：
     * 1.如果你不关心发送的元素，只关心onComplete和onError事件，可以使用ignoreElements操作符，他会把当前被观察者转换成Completable类型的被观察者：
     */
    public static void ignoreElementFilterOperator() {
        //如果你不关心发送的元素，只关心onComplete和onError事件，可以使用ignoreElements操作符，他会把当前被观察者转换成Completable类型的被观察者：
        Flowable.just("a","b","c")
                .ignoreElements()
                .subscribe(()->LogUtil.e("tag:Complete!"));
    }

    /**
     * distinct/distinctUntilChanged操作符
     *
     * 作用：
     * 1.如果想过滤掉重复的元素，可以使用distinct操作符：
     * 2.如果只需要过滤连续重复的元素，则可以使用distinctUntilChanged操作符：
     */
    public static void distinctAnddistinctUntilChanged() {
        //如果想过滤掉重复的元素，可以使用distinct操作符：
        Flowable.just("a","b","c","b","b","c")
                .distinct()
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
        //上述代码只会输出a、b、c三个元素。

        //如果只需要过滤连续重复的元素，则可以使用distinctUntilChanged操作符：
        Flowable.just("a","b","c","b","b","c")
                .distinctUntilChanged()
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
        //上述代码会输出a、b、c、b、c这几个元素。

    }

    /**
     * timeout操作符
     *
     * 作用：
     * 1.如果需要过滤超时操作，可以使用timeout操作符：
     */
    public static void  timeoutFilterOperator() {
        //如果需要过滤超时操作，可以使用timeout操作符：
        Flowable.intervalRange(0,10,0,2,TimeUnit.SECONDS)
                .timeout(1,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
        //上述代码输出0后超时,抛出异常。

        Flowable.intervalRange(0,10,0,2,TimeUnit.SECONDS)
                .timeout(1,TimeUnit.SECONDS,Flowable.just(-1L))
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
        //上述代码先输出0，然后超时，使用自定义的Flowable输出-1。
    }

    /**
     * throttleFirst操作符
     *
     * 作用：
     * 1.如果你需要在一段时间内只响应第一次的操作，比如说一段时间内连续点击按钮只执行第一次的点击操作，throttleFirst操作符就可以满足这个需求
     *
     */
    public static void throttleFirstFilterOperator() {
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                //每一秒钟只处理第一个元素
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));
        //上述结果为0,2,4,6,8,其中1,3,5,7,9都被过滤掉了。
    }

    /**
     * throttleLast/sample操作符
     *
     * 作用：
     * 1. 除了throttleFirst，有对应的throttleLast操作符，它的功能和sample操作符相同，都是隔一段时间采集一个元素：但是取的值是最后一个。
     *
     */
    public static void throttleLastAndsampleFilterOpterator() {
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                //每2秒钟采集最后一个元素
                .throttleLast(2,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

        //等价于
        Flowable.intervalRange(0,10,0,1, TimeUnit.SECONDS)
                //每2秒采集最后一个元素
                .sample(2,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

        //以上代码只会输出1、3、5、7,之后就会直接触发onCompete事件。
    }

    /**
     * throttleWithTimeout/debounce操作符
     *
     * 作用：
     * 1.假设有一种即时显示搜索结果需求，要求一段时间用户不输入才响应请求搜索结果，这样的需求可以使用throttleWithTimeout操作符或者debounce操作符
     */
    public static void throttleWithTimeoutAnddebounceFilterOperator() {
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                //2秒内有新数据则抛弃旧数据
                .throttleWithTimeout(2,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

        // 等价于
        Flowable.intervalRange(0,10,0,1,TimeUnit.SECONDS)
                //2秒内有新数据则抛弃旧数据
                .debounce(2,TimeUnit.SECONDS)
                .subscribe(ele -> LogUtil.e("tag:"+String.valueOf(ele)));

        // 上述例子中只会输出9这个元素，因为没当接收到一个元素的时候，会等待2秒，如果有新的元素发送，则抛弃旧的元素，使用新的元素，直到2秒过去或者没有新的数据（比如onComplete）。
    }
}

