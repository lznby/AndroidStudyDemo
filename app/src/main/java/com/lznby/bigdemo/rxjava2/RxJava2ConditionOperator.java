package com.lznby.bigdemo.rxjava2;

import com.lznby.bigdemo.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * @author Lznby
 * @time 2018/8/14 15:36
 * Class Note: 条件操作符
 * <p>
 * 条件操作符，用于控制被观察者开始、停止、跳过的各种条件操作符。
 */
public class RxJava2ConditionOperator {
    /**
     * all操作符
     * <p>
     * 作用：
     * 1.要判断所有元素是否满足某个条件，可以使用all操作符，它接受一个Predicate，其中的test方法用于判断某个元素是否满足条件，最终输出是否所有元素都满足条件，比如下面例子：
     */
    public static void allConditionOperator() {
        //要判断所有元素是否满足某个条件，可以使用all操作符，它接受一个Predicate，其中的test方法用于判断某个元素是否满足条件，最终输出是否所有元素都满足条件，比如下面例子：
        Flowable.just(1, 2, 3)
                .all(integer -> integer >= 0)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子是判断是否所有元素都大于等于0，all操作符会把被观察者转换成Single<Boolean>类型的被观察者，最终输出结果为true。
    }

    /**
     * ambArray操作符
     * <p>
     * 作用：
     * 1.ambArray操作符可以从多个被观察者中选择第一个发射元素的被观察者进行处理，其他被观察者则抛弃，比如：
     */
    public static void ambArrayConditionOperator() {
        Flowable.ambArray(
                Flowable.timer(1, TimeUnit.SECONDS),
                //仅处理第一个发射元素的被观察者
                Flowable.just(3, 4, 5)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码只会处理第二个被观察者,输出结果为3,4,5。
    }

    /**
     * contains操作符
     * <p>
     * 作用：
     * 1.如果要判断被观察者是否包含某一个元素，可以使用contains操作符，例子如下：
     */
    public static void containsConditionOperator() {
        //如果要判断被观察者是否包含某一个元素，可以使用contains操作符，例子如下：
        Flowable.just(3, 4, 5)
                .contains(3)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码判断被观察者发射元素中是否包含“3”，contains操作符会把被观察者转换成Single<Boolean>类型的被观察者,输出结果为true。
    }

    /**
     * any操作符
     * <p>
     * 作用：
     * 1.我们观察contains的源码会发现，实际上它调用的是any操作符，any操作符可以判断是否存在某一个元素满足一定的条件，用法和all操作符类似，也是接受一个Predicate，例子如下：
     */
    public static void anyConditionOperator() {
        //我们观察contains的源码会发现，实际上它调用的是any操作符，any操作符可以判断是否存在某一个元素满足一定的条件，用法和all操作符类似，也是接受一个Predicate，例子如下：
        Flowable.just(3, 4, 5)
                .any(integer -> integer == 3)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子即寻找是否有元素"3"，等价于contains(3),any操作符也是把被观察者转换成Single<Boolean>类型的被观察者,输出结果为true。
    }

    /**
     * isEmpty操作符
     * <p>
     * 作用：
     * 1. 要判断一个被观察者是否发射过元素，可以使用isEmpty操作符，其例子如下：
     */
    public static void isEmptyConditionOperator() {
        //要判断一个被观察者是否发射过元素，可以使用isEmpty操作符，其例子如下：
        Flowable.just(3, 4, 5)
                .isEmpty()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码输出false，可以源码发现isEmpty实际上是调用了all操作符。
    }

    /**
     * defaultIfEmpty操作符
     * <p>
     * 作用：
     * 1. 如果你需要在被观察者不发送数据的时候需要发送一个默认的元素，可以使用defaultIfEmpty操作符，其例子如下：
     */
    public static void defaultIfEmptyConditionOperator() {
        //如果你需要在被观察者不发送数据的时候需要发送一个默认的元素，可以使用defaultIfEmpty操作符，其例子如下：
        Flowable.empty()
                .defaultIfEmpty(1)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //  如果被观察者不发送任何数据，则会发送默认数据，上述代码中发送的是“1”，故输出结果为“1”。
    }

    /**
     * switchIfEmpty操作符
     * <p>
     * 作用：
     * 1.defaultIfEmpty操作符只能在被观察者不发送数据时发送一个默认的数据，如果需要发送更多数据，则可以使用switchIfEmpty操作符，发送自定义的被观察者作为替代，比如：
     */
    public static void switchIfEmptyConditionOperator() {
        //defaultIfEmpty操作符只能在被观察者不发送数据时发送一个默认的数据，如果需要发送更多数据，则可以使用switchIfEmpty操作符，发送自定义的被观察者作为替代，比如：
        Flowable.empty()
                .switchIfEmpty(Flowable.just(3, 4, 5))
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码会输出3,4,5。
    }

    /**
     * sequenceEqual 操作符
     * <p>
     * 作用：
     * 1.如果要对比两个被观察者发射的元素队列，可以使用sequenceEqual操作符，它只关心两个发射队列的元素、元素发射顺序、和最终状态，而不关心他的时间，下面是一个例子：
     */
    public static void sequenceEqualConditionOperator() {
        //如果要对比两个被观察者发射的元素队列，可以使用sequenceEqual操作符，它只关心两个发射队列的元素、元素发射顺序、和最终状态，而不关心他的时间，下面是一个例子：
        Flowable.sequenceEqual(
                Flowable.just(0L, 1L, 2L),
                Flowable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子中输出是true，sequenceEqual操作符对于元素的类型也是十分敏感的，假设要自定义Integer和Long类型值相等则认为两个元素相等，可以添加额外的参数isEqual，由于sequenceEqual操作符只能对比两个被观察者，所以使用BiPredicate作为判断类型，下面是例子:
        Flowable.sequenceEqual(
                Flowable.just(0, 1, 2),
                Flowable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS),
                (num1, num2) -> num1.longValue() == num2.longValue()
        ).subscribe();
        //上述代码中两个类型均为Number类型，但如果不使用自定义的isEqual参数，则会返回false，这里使用了判断两个数的Long值相等则相等，所以最后输出结果为true。
    }

    /**
     * takeUntil 操作符
     * <p>
     * 作用：
     * 1.如果想执行到某个条件就停止事件，可以使用takeUntil操作符，它接受一个Predicate来定义停止条件，其用法如下：
     */
    public static void takeUntilConditionOperator() {
        //如果想执行到某个条件就停止事件，可以使用takeUntil操作符，它接受一个Predicate来定义停止条件，其用法如下：
        Flowable.just(1, 2, 3)
                .takeUntil(integer -> integer == 2)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码表明当元素==2时则停止，所以输出结果为1,2。这里值得注意的是，输出结果是包含该元素的。除此之外，takeUntil也可以接受另外一个被观察者，当这个被观察者结束之后则停止第一个被观察者,例子如下：
        Flowable.interval(100, TimeUnit.MILLISECONDS)
                .takeUntil(Flowable.timer(1, TimeUnit.SECONDS))
                .subscribe();
        //上述代码将会在1秒后停止interval生成的被观察者，所以输出结果只有0,1,2,3,4,5,6,7,8。
    }

    /**
     * takeWhile 操作符
     * <p>
     * 作用：
     * 1.takeWhile操作符和takeUntil操作符类似，但是takeWhile只接受Predicate，而且Predicate中的test方法返回true才执行被观察者的事件，其例子如下：
     */
    public static void takeWhileConditionOperator() {
        //takeWhile操作符和takeUntil操作符类似，但是takeWhile只接受Predicate，而且Predicate中的test方法返回true才执行被观察者的事件，其例子如下：
        Flowable.interval(100, TimeUnit.MILLISECONDS)
                .takeWhile(longItem -> longItem != 5L)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码输出的结果为0,1,2,3,4，注意是不包含5这个元素的。
    }

    /**
     * skipUntil 操作符
     * <p>
     * 作用：
     * 1.skipUntil操作符接收一个被观察者，直到该被观察者发送事件之前，第一个被观察者所有发送的元素将被抛弃，例如：
     */
    public static void skipUnitlConditionOperator() {
        //skipUntil操作符接收一个被观察者，直到该被观察者发送事件之前，第一个被观察者所有发送的元素将被抛弃，例如：
        Flowable.intervalRange(0, 10, 0, 1, TimeUnit.SECONDS)
                .skipUntil(Flowable.timer(3, TimeUnit.SECONDS))
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码中,表示intervalRange生成的被观察者前3秒发送的元素都会被抛弃,所以最终输出的结果为3,4,5,6,7,8,9。
    }


    /**
     * skipWhile 操作符
     * <p>
     * 作用：
     * 1.skipWhile操作符可以接受一个Predicate用于控制跳过开始一段数据，比如：
     */
    public static void skipWhileConditionOperator() {
        //skipWhile操作符可以接受一个Predicate用于控制跳过开始一段数据，比如：
        Flowable.intervalRange(0, 5, 0, 100, TimeUnit.MILLISECONDS)
                .skipWhile(longItem -> longItem < 2)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子中，少于2的元素将会被跳过，即输出2,3,4，要注意的是，如果.skipWhile(longItem -> longItem > 2)是不会跳过任何发射元素的，因为skipWhile操作符只会过滤一开始的数据，不能跳过中间或者以后的数据。
    }
}
