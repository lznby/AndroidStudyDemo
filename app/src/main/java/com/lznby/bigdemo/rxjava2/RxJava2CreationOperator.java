package com.lznby.bigdemo.rxjava2;

import android.util.Log;

import com.lznby.bigdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * @author Lznby
 * @time 2018/8/13 16:38
 * Class Note: RxJava2 创建操作符
 */
public class RxJava2CreationOperator {
    /**
     * just操作符
     * <p>
     * 使用范围：
     * 1.可以使用Flowable、Observable、Single或者Maybe来调用这个操作符。
     * 2.对于Completable则不能使用（因为没有onNext事件），
     * 3.对于Flowable和Observable最多能接收10个参数，也就是发送10个数据，而Single和Maybe只能接收1个参数（只能发送一次onNext事件）。
     * <p>
     * 作用：
     */

    public static void justCreationOperator() {
        //以下代码相当于代码相当于顺序调用onNext("test")和onNext("test2")，然后调用onComplete方法。
        Flowable.just("test1", "test2")
                .subscribe(str -> LogUtil.e("onNext:" + str));
    }

    /**
     * fromArray操作符
     * <p>
     * 使用范围：
     * 1.同just操作符
     * <p>
     * 作用：
     * 1.之前说到的just操作符，最多只能接收10个参数。我们通过观察just操作符的源码发现，其实超过2个参数时，它会帮我们调用fromArray操作符，采用fromArray来接收任意长度的数据数组。
     * 2.作用基本与just相同
     * 3.fromArray可以直接传入一个数组，例如fromArray(new int[]{1, 2, 3})，但是不要直接传递一个list进去，这样它会直接把list当做一个数据元素发送。
     */
    public static void fromArrayCreationOperator() {
        //以下代码相当于代码相当于顺序调用onNext(1)和onNext(2)...，然后调用onComplete方法。
        Flowable.fromArray(1, 2, 3, 4, 5)
                .subscribe(integer -> Log.i("tag", String.valueOf(integer)));
        //等价于
        Flowable.fromArray(new int[]{1, 2, 3, 4, 5})
                .subscribe(integer -> Log.i("tag", String.valueOf(integer)));
    }

    /**
     * empty操作符
     * <p>
     * 使用范围：
     * 1.
     * <p>
     * 作用：
     * 1.上面说到，fromArray可以接受任意长度的数据数组，假设数组元素数量为0会怎么样呢？我们查看fromArray源代码，可以发现，当数据长度为0时，会调用empty操作符。
     * 2.empty操作符不会发送任何数据，而是直接发送onComplete事件，我们写一个例子：
     */
    public static void emptyCreationOperator() {
        Flowable.empty().subscribe(
                obj -> LogUtil.e("tag->next:" + obj.toString()),
                e -> LogUtil.e("tag->error:"),
                () -> LogUtil.e("tag->complete")
                //会发现上面的例子只会打印complete，其他回调并不会触发。
        );
    }

    /**
     * error操作符
     * <p>
     * 使用范围
     * 1.
     * <p>
     * 作用：
     * 1.除了直接发送onComplete，当然就有直接发送onError，error操作符就是调用时候直接发送onError事件给观察者：
     */
    public static void errorCreatetionOperator() {
        Flowable.error(new RuntimeException("test")).subscribe(
                obj -> LogUtil.e("tag->next" + obj.toString()),
                e -> LogUtil.e("tag->error"),
                () -> LogUtil.e("tag->complete")
        );
        //上面的例子将只会打印error，其他回调并不会触发。
    }

    /**
     * never操作符
     * <p>
     * 使用范围：
     * 1.
     * <p>
     * 特点：
     * 下面来介绍另外一个什么都不会发送的操作符never，也不会触发观察者任何的回调：
     * 1.
     * <p>
     * 作用：
     * 1.上面的例子将不会输出任何东西，这个操作符通常用于“测试”用途。
     */
    public static void neverCreationOperator() {
        Flowable.never().subscribe(
                obj -> LogUtil.e("tag->next" + obj.toString()),
                e -> LogUtil.e("tag->error"),
                () -> LogUtil.e("tag->complete")
        );
    }

    /**
     * fromIterable操作符
     * <p>
     * 使用范围：
     * 1.
     * <p>
     * 作用：
     * 1.上文说到的fromArray并不能遍历list等集合，采用fromIterable可以遍历可迭代数据集合：
     */
    public static void fromIterableCreationOperator() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Flowable.fromIterable(list).subscribe(
                s -> LogUtil.e("tag:" + s)
        );
        //可以看到输出结果是顺序输出列表中的元素，fromIterable和fromArray除了输入数据不同，其他基本是相似的。
    }

    /**
     * timer操作符
     * <p>
     * 作用：
     * 1.接下来我们说一下时间间隔操作符timer，可以指定一段时间发送数据（固定值0L）：
     */
    public static void timerCreationOperator() {
        Flowable.timer(1, TimeUnit.SECONDS)
                //这里的x是long类型
                .subscribe(x -> LogUtil.e("tag:" + String.valueOf(x)));
        //上面例子为延迟1秒后调用onNext(0)，然后调用onComplete()事件。其中timer操作符还有重载方法可以接受多一个参数Scheduler，这个后面会介绍到。
    }

    /**
     * interval操作符
     *
     * 作用：
     * 1.上面的timer操作符只能发送一次数据，对于要不断地发送数据，可以采用interval操作符
     * 2.这里interval接受的第一个参数是第一次的延迟，如果忽略（重载方法）则和设定的间隔一样，也可以单独设置Scheduler。
     */
    public static void intervalCreationOperator() {
        Flowable.interval(2,1,TimeUnit.SECONDS)
                //这里的x是0,1,2,3...
                .subscribe(x -> LogUtil.e("tag:"+String.valueOf(x)));
        //如果你运行了上面的代码，会发现interval操作符会无限执行，永不停止，那么应该怎么停止呢？这里就需要过滤操作符take了，为避免打乱文章顺序，这个也是后面再详细介绍。
    }

    /**
     * intervalRange操作符
     *
     * 作用：
     * 1.上面说到interval操作符是从0开始发送数据，如果我们需要指定发送范围，那么可以使用intervalRange操作符：
     */
    public static void intervalRangeCreationOperator() {
        Flowable.intervalRange(1,10,2,1,TimeUnit.SECONDS)
                //x从1-10,初始间隔为2秒,之后间隔1秒发送一次
                .subscribe(x->LogUtil.e("tag:"+String.valueOf(x)));
        //如同注释中的说明，当x从1开始发送到10后(注意参数10是发送10个数量的意思,类似于request(10)的操作)调用onComplete方法，值得注意的是从最后一个元素发出到onComplete之间并不会有period长度的延迟。
    }

    /**
     * range / rangeLong操作符
     *
     * 作用：
     * 1.如果你不需要延迟发送数据，但是需要确定一个数据的范围可以采用range或者是rangeLong，后者的数据类型是long，可以使用的范围更加广，其他完全是一样的，其用法如下：
     */
    public static void rangeAndRangeLongCreationOperator() {
        //int类型范围
        Flowable.range(0,5)
                .subscribe(x -> LogUtil.e("tag:"+String.valueOf(x)));

        //long类型范围
        Flowable.rangeLong(Integer.MAX_VALUE, 5L)
                .subscribe(x->LogUtil.e("tag:"+String.valueOf(x)));

        //上述输出都是从第一个数开始直接输出到最后一个数（第二个参数也是数量，而不是尾数）然后调用onComplete事件，中间没有间隔延迟。
    }

    /**
     * defer操作符
     *
     * 作用：
     * 1. 之前说过一个被观察者可以订阅多个观察者，如果需要每个观察者被订阅的时候都重新创建被观察者（一对一），则可以使用defer操作符：
     */
    public static void deferCreationOperator() {
        Flowable<String> flowable = Flowable.defer(() -> Flowable.just("1","2"));

        //订阅第一个观察者
        flowable.subscribe(str -> LogUtil.e("tag:"+str));

        //订阅第二个观察者
        flowable.subscribe(str -> LogUtil.e("tag:"+str));

        //上面会输出两次（1,2）而且是顺序输出，只有当第一个观察者执行完后才回去创建第二个被观察者然后订阅观察者，然后才开始（第二个被观察者）发送事件消息。
    }
}
