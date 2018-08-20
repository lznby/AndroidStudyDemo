package com.lznby.bigdemo.rxjava2;

import com.lznby.bigdemo.utils.LogUtil;

import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author Lznby
 * @time 2018/8/15 10:28
 * Class Note: 变换操作符
 */
public class RxJava2ConversionOperator {
    /**
     * map 操作符
     * <p>
     * 作用：
     * 1. 基本的转换操作符，可以把每一个元素转换成新的元素发射，接收一个Function<T,R>作为转换逻辑的操作，下面是例子：
     */
    public static void mapConversionOperator() {
        Flowable.just(1, 2, 3)
                .map(integer -> "int" + integer)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述例子中map操作符返回了Flowable<String>，最终输出的结果为：int1、int2、int3。
    }

    /**
     * flatMap 操作符
     * <p>
     * 作用：
     * 1.上面的Map操作符是把每一个元素转换成一个新的元素，但是flatMap操作符是把每一个元素转换成新的被观察者，每个被观察者发射的元素将会合并成新的被观察者，这些元素顺序输出，例如下面的：
     */
    public static void flatMapConversionOperator() {
        Flowable.just(1, 2, 3)
                .flatMap((Function<Integer, Publisher<?>>)
                        integer -> Flowable.just("a", integer)
                ).subscribe(ele -> LogUtil.e("tag:" + ele));
        //上述代码中把每一个just发射的元素转换成新的Flowable，而每一个新的Flowable额外添加一个“a”元素，所以上述的例子输出结果是：a、1、a、2、a、3。
    }

    /**
     * flatMapIterable 操作符
     * <p>
     * 作用：
     * 1. flatMapIterable与flatMap类似，但是flatMapIterable是把每一个元素转换成Iterable，例子如下：
     */
    public static void flatMapIterableConversionOperator() {
        Flowable.just(1, 2, 3)
                .flatMapIterable((Function<Integer, ? extends Iterable<?>>)
                        integer -> Arrays.asList("a", integer)
                ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码把每一个元素转换成一个List，每个list以元素“a”开头，所以上述的例子输出结果是：a、1、a、2、a、3。
    }

    /**
     * concatMap 操作符
     * <p>
     * 作用：
     * 1.concatMap操作符合flatMap操作符类似，接收的参数和转换都是类似的，例子如下
     */
    public static void concatMapConversionOperator() {
        Flowable.just(1, 2, 3)
                .concatMap(integer -> Flowable.just("a", integer))
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        // 上述的例子输出结果是：a、1、a、2、a、3。flatMap操作符内部是使用merge合并元素，concatMap操作符则是通过concat合并元素，前者可能会出现元素交错问题，后者严格按照顺序发射。另外concatMap也有类似的concatMapIterable操作符，这里就不一样介绍了。
    }

    /**
     * switchMap 操作符
     * <p>
     * 作用：
     * 1.switchMap用法与flatMap类似，但是转换出来的每一个新的数据（被观察者）发射会取代掉前一个被观察者，如下例子：
     */
    public static void switchMapConversionOperator() {
        //switchMap用法与flatMap类似，但是转换出来的每一个新的数据（被观察者）发射会取代掉前一个被观察者，如下例子：
        Flowable.just(1, 2, 3)
                .switchMap(integer ->
                                Flowable.timer(1, TimeUnit.SECONDS)
                                        .map(longValue -> integer)
                        //延迟1s发送元素
                ).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上面的例子最终只会输出3这个元素，其他元素被覆盖替代掉不再发送。
    }

    /**
     * cast 操作符
     * <p>
     * 作用：
     * 1.强制转换每一个元素的类型，内部调用map操作符来进行转换：
     */
    public static void castConversionOperator() {
        //强制转换每一个元素的类型，内部调用map操作符来进行转换：
        Flowable.just(1, 2, 3)
                .cast(Number.class)
                .subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码把每个元素都转换成Number类型，然后再发射。
    }

    /**
     * scan 操作符
     * <p>
     * 作用：
     * 1.扫描每一个元素，第一个元素将忽略，从第二个元素开始（可以获得上一个元素的值）进行变换后返回，例如：
     */
    public static void scanConversionOperator() {
        //扫描每一个元素，第一个元素将忽略，从第二个元素开始（可以获得上一个元素的值）进行变换后返回，例如：
        Flowable.just(1, 2, 3)
                .scan((last, item) -> {
                    LogUtil.e("tag:" + String.valueOf(last));
                    LogUtil.e("tag:" + String.valueOf(item));
                    return item + 1;
                }).subscribe(ele -> LogUtil.e("tag:" + String.valueOf(ele)));
        //上述代码输出结果为1、3、4
    }

    /**
     * buffer 操作符
     * <p>
     * 作用：
     * 1.buffer操作符是把多个元素打包成一个元素一次过发送数据，例如下面例子：
     */
    public static void bufferConversionOperator() {
        Flowable.just(1, 2, 3, 4, 5)
                //3个元素打包成一个元素
                .buffer(3)
                .subscribe(intList -> LogUtil.e("tag:" + intList.toString()));
        //上述代码把三个元素组合成一个List发送，输出结果为：[1,2,3]、[4,5]
    }

    /**
     * toList 操作符
     * <p>
     * 作用：
     * 1.把所有元素转换成一个List一次过发送出去，如下面例子：
     */
    public static void toListConversionOperator() {
        //把所有元素转换成一个List一次过发送出去，如下面例子：
        Flowable.just(1, 2, 3, 4, 5)
                .toList()
                .subscribe(intList -> LogUtil.e("tag:" + intList));
        //上述代码输出[1, 2, 3, 4, 5]。除了toList操作符以外，还有toSortedList操作符，而且toSortedList操作符也支持自定义排序方式，这里就不展开了。
    }

    /**
     * groupBy
     * <p>
     * 作用：
     * 1. groupBy操作符通过Function接收每个数据的分组key，然后返回GroupedFlowable，使用者可以再订阅这个被观察者进行数据输出，由于使用lambda表达式可能加大理解难度，这里给出普通写法的例子：
     */
    public static void groupByConversionOperator() {
        // groupBy操作符通过Function接收每个数据的分组key，然后返回GroupedFlowable，使用者可以再订阅这个被观察者进行数据输出，由于使用lambda表达式可能加大理解难度，这里给出普通写法的例子：
        Flowable.just(1, 2, 3, 4, 5)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        //这里的返回值是分组的key
                        return integer > 2 ? "A组" : "B组";
                    }
                })
                .subscribe(new Consumer<GroupedFlowable<String, Integer>>() {
                    @Override
                    public void accept(GroupedFlowable<String, Integer> stringIntegerGroupedFlowable) throws Exception {
                        stringIntegerGroupedFlowable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                String key = stringIntegerGroupedFlowable.getKey();
                                LogUtil.e("tag:" + key + ":" + String.valueOf(integer));
                            }
                        });
                    }
                });
        //上述代码将输出：“B组:1、B组:2、A组:3、A组:4、A组:5”，上述代码转换成lambda表达式为：

        Flowable.just(1, 2, 3, 4, 5)
                .groupBy(integer -> integer > 2 ? "A组" : "B组")
                .subscribe(groupedFlowable ->
                        groupedFlowable.subscribe(
                                integer -> LogUtil.e("tag:" + groupedFlowable.getKey() + ":" + String.valueOf(integer))
                        ));
    }

    /**
     * toMap 操作符
     * <p>
     * 作用：
     * 1. 熟悉了groupBy后，toMap也是类似的，可以通过自定义key、value转换成对应的map，如下例子：
     */
    public static void toMapConversionOperator() {
        Flowable.just(1, 2, 3, 4, 5)
                //第一个参数Function返回Map的key
                .toMap(integer -> "key" + integer)
                .subscribe(map -> LogUtil.e("tag:" + map.toString()));
        //  上述代码输出：“{key5=5, key2=2, key4=4, key1=1, key3=3}”，另外toMap还可以支持自定义每个item对应的value值（传入第二个Function处理），这里就不一一介绍了
    }
}
