package com.lznby.bigdemo.rxjava2;

import com.lznby.bigdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * @author Lznby
 * @time 2018/8/14 13:32
 * Class Note: 合并聚合操作符
 * <p>
 * 这期我们来说一下RxJava中合并操作符和聚合操作符，主要用于合并多个被观察者或者把一个观察者的多个元素聚合成一个元素。
 * 文章先从合并操作符开始说明，当切换到聚合操作符时，文章会提到。
 */
public class RxJava2MergeOperator {
    /**
     * startWith/starWithArray操作符
     * <p>
     * 作用：
     * 1.如果你需要在被观察发送元素之前追加数据或者追加新的被观察者，这时候可以使用startWith操作符
     * 2.可以使用另外一个操作符startWithArray，一次追加多个元素。
     */
    public static void startWithAndstarWithArrayMergeOperator() {
        //如果你需要在被观察发送元素之前追加数据或者追加新的被观察者，这时候可以使用startWith操作符，例子如下：
        Flowable.just(4, 5, 6)
                .startWith(Flowable.just(1, 2, 3))
                .startWith(0)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));

        // 上述代码输出结果为“0,1,2,3,4,5,6”,如果你需要追加多个元素，则需要使用另外一个操作符startWithArray，一次追加多个元素。
        Flowable.just(4, 5, 6)
                .startWithArray(1, 2, 3)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        // 上述代码输出结果为“1,2,3,4,5,6”，实际上上述两个操作符从源码中观察，都可以发现它实际上使用的concat和concatArray操作符去实现的。下面我们来说明下这两个操作符。
    }

    /**
     * concat/concatArray操作符
     * <p>
     * 作用：
     * 1.concat操作符可以连接最多4个的被观察者，他们的顺序是串行执行的：
     * 2.如果需要多于4个被观察合并在一起，可以使用concatArray操作符：
     */
    public static void concatAndconcatArrayMergeOperator() {
        //concat操作符可以连接最多4个的被观察者，他们的顺序是串行执行的：
        Flowable.concat(
                Flowable.just(1, 2, 3),
                Flowable.just(4, 5, 6)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));

        //上述代码输出结果为“1,2,3,4,5,6”，如果需要多于4个被观察合并在一起，可以使用concatArray操作符：

        Flowable.concatArray(Flowable.just(1), Flowable.just(2))
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码输出结果为1、2
    }

    /**
     * merge/mergeArray操作符
     * <p>
     * 作用：
     * 1.上面说到的concat操作符，对于每一个被观察者都是按照顺序串行执行的，接下来介绍的merge操作符也是合并多个被观察者，但他们合并后是按照时间线并行执行
     * 2.和concat操作符一样，merge也是最多只能合并4个被观察者，可以使用mergeArray来合并多个被观察者，这里就不举例子了。
     */
    public static void mergeAndMergeArrayMergeOperator() {
        //上面说到的concat操作符，对于每一个被观察者都是按照顺序串行执行的，接下来介绍的merge操作符也是合并多个被观察者，但他们合并后是按照时间线并行执行，举个例子：
        Flowable.merge(
                Flowable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                Flowable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子中，两个被观察将并行执行，输出结果为"0,3->1,4->2,5"，可以直观的地观察到和concat操作符的区别。
    }

    /**
     * concatDelayError/mergeDelayError
     * <p>
     * 作用：
     * 1.使用concat和merge操作符时，如果遇到其中一个被观察者发出onError事件则会马上终止其他被观察者的事件，
     * 如果希望onError事件推迟到其他被观察者都结束后才触发，可以使用对应的concatDelayError或者mergeDelayError操作符。
     * 2. concatArray和mergeArray也可以使用对应的操作符：concatArrayDelayError和mergeArrayDelayError
     */
    public static void concatDelayErrorAndmergeDelayErrorMergeOperator() {
        //使用concat和merge操作符时，如果遇到其中一个被观察者发出onError事件则会马上终止其他被观察者的事件，如果希望onError事件推迟到其他被观察者都结束后才触发，可以使用对应的concatDelayError或者mergeDelayError操作符，比如：
        Flowable.mergeDelayError(
                Flowable.create(s -> s.onError(new NullPointerException()), BackpressureStrategy.ERROR),
                Flowable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
    }

    /**
     * zip操作符
     * <p>
     * 作用：
     * 1. 多个被观察者压缩成单个的操作可以使用zip操作符，如果多个被观察者数量不同，则以少的为基准，可以使用Funtions来自定义zip操作（zipper）：
     * 2.对于delayError的操作是通过参数传递进去的，在zipper参数之后。zip操作符最多接受9个被观察者，这里的zipper使用到的Functions按照被观察个数分别对应Function、BiFunction和Function3 - Function9，其中的apply方法用于对应的操作。
     */
    public static void zipMergeOperator() {
        //多个被观察者压缩成单个的操作可以使用zip操作符，如果多个被观察者数量不同，则以少的为基准，可以使用Funtions来自定义zip操作（zipper）：
        Flowable.zip(
                Flowable.just(1, 2, 3),
                Flowable.just(4, 5),
                (int1, int2) -> int1 + int2
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码中输出的结果为5、7
    }

    /**
     * combineLatest操作符
     * <p>
     * 作用：
     * 1.combineLatest类似zip操作符，但它合并时机和zip不一样，zip是一对一合并压缩，combineLatest则是在同一个时间线上，合并最后的元素
     */
    public static void combineLatestMergeOperator() {
        //combineLatest类似zip操作符，但它合并时机和zip不一样，zip是一对一合并压缩，combineLatest则是在同一个时间线上，合并最后的元素，举个例子：
        Flowable.combineLatest(
                Flowable.just(1L, 2L, 3L),
                Flowable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                (long1, long2) -> long1 + long2
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子只会合并3+0,3+1,3+2,即输出3,4,5这几个元素。对于第一个和观察者中元素1、2元素被忽略了
    }

    /**
     * combineLatestDelayError操作符
     * <p>
     * 作用：
     * 1.combineLatest要处理delayError问题, 需要使用combineLatestDelayError操作符,这个操作符把combiner的Funtions用Object[]数组的Funtion来代替，
     * 而且是作为第一个参数，上述的combineLatest例子使用combineLatestDelayError可以改写成：
     */
    public static void combineLatestDelayErrorMergeOperator() {
        // combineLatest要处理delayError问题，需要使用combineLatestDelayError操作符，这个操作符把combiner的Funtions用Object[]数组的Funtion来代替，而且是作为第一个参数，上述的combineLatest例子使用combineLatestDelayError可以改写成：
        Flowable.combineLatestDelayError(
                objects -> (Long) objects[0] + (Long) objects[1],
                Flowable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS)
        ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
    }

    /**
     * reduce操作符
     * <p>
     * 作用：
     * 1. 下面我们来介绍聚合操作符，要把一个被观察者的所有元素都聚合成单一的元素，可以使用reduce操作符，比如把所有元素都加起来，代码如下：
     */
    public static void reduceMergeOperator() {
        // 下面我们来介绍聚合操作符，要把一个被观察者的所有元素都聚合成单一的元素，可以使用reduce操作符，比如把所有元素都加起来，代码如下：
        Flowable.just(1, 2, 3)
                .reduce((last, item) -> {
                    LogUtil.e("tag:" + last + "," + item);
                    return last + item;
                }).subscribe(ele -> LogUtil.e("tag：" + String.valueOf(ele)));
        //可以看到一开始，先执行1+2，然后用1+2的结果和3相加，最后输出6，相当于把三个元素聚合在一起。
    }


    /**
     * count操作符
     * <p>
     * 作用：
     * 1.如果要统计一个被观察者发送多少个元素可以使用count方法,其代码如下：
     */
    public static void countMergeOperator() {
        //如果要统计一个被观察者发送多少个元素可以使用count方法,其代码如下：
        Flowable.just(1, 2, 3)
                .count()
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //count操作符会把观察者转换成Single<Long>类型的被观察者，上述代码会输出3（即原被观察者发送的元素数量）。
    }

    /**
     * collect操作符
     * <p>
     * 作用：
     * 1.collect和reduce操作相似，不过它是需要自己定义收集的容器和收集逻辑，下面举个例子，利用ArrayList收集发射的元素：
     */
    public static void collectMergeOperator() {
        //collect和reduce操作相似，不过它是需要自己定义收集的容器和收集逻辑，下面举个例子，利用ArrayList收集发射的元素：
        Flowable.just(1, 2, 3)
                .collect(new Callable<ArrayList<Integer>>() {
                             @Override
                             public ArrayList<Integer> call() throws Exception {
                                 return new ArrayList<>();
                             }
                         }, new io.reactivex.functions.BiConsumer<ArrayList<Integer>, Integer>() {
                             @Override
                             public void accept(ArrayList<Integer> list, Integer integer) throws Exception {
                                 list.add(integer);
                             }
                         }
                ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));

        //lambda写法
        Flowable.just(1, 2, 3)
                .collect(ArrayList::new, ArrayList::add)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));

        // 上述操作最终会输出[1,2,3]这个ArrayList元素,相当于收集了所用元素的结果
    }
}
