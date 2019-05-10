package com.lznby.bigdemo.lznby.retrofit2;

/**
 * @author Lznby
 * @time 2018/9/26 15:43
 * Class Note:
 * 1.Retrofit说明文档
 */

/**
 * 注解种类：
 * 1.网络请求方法
 * 注解                 作用域                   功能
 * @GET                 网络请求接口的方法        GET
 * @POST                网络请求接口的方法        POST
 * @PUT                 网络请求接口的方法        PUT
 * @DELETE              网络请求接口的方法        DELETE
 * @PATH                网络请求接口的方法        PATH
 * @HEAD                网络请求接口的方法        HEAD
 * @OPTIONS             网络请求接口的方法        OPTIONS
 * @HTTP                网络请求接口的方法        HTTP用于替换以上7个注解的作用及更多功能拓展
 * 2.标记类
 * @FormUrlEncoded      网络请求接口的方法        表示请求体是一个表单
 * @Multipart           网络请求接口的方法        表示请体是一个支持文件上传的Form表单
 * @Streaming           网络请求接口的方法        表示返回的数据以流的形式返回;适用于返回数据较大的场景;(如果没有使用该注解,默认把数据全部载入内存;之后获取数据也是从内存中读取)
 * 3.网络请求参数
 * @Headers             网络请求接口的方法        添加不固定的Header
 * @Header              网络请求接口的方法的参数   添加请求头
 * @Body                网络请求接口的方法的参数   用于非表单请求体
 * @Field               网络请求接口的方法的参数   向Post表单传入键值对
 * @FieldMap            网络请求接口的方法的参数   向Post表单传入键值对
 * @Part                网络请求接口的方法的参数   用于表单字段;适用于有文件上传的情况
 * @PartMap             网络请求接口的方法的参数   用于表单字段;适用于有文件上传的情况
 * @Query               网络请求接口的方法的参数   用于表单字段;功能与@Field与@FieldMap;(区别在于@QueryMap的数据体现在URL上,@FieldMap的数据体现在请求体上;但生成的数据是一致的。)
 * @QueryMap            网络请求接口的方法的参数   用于表单字段;功能与@Field与@FieldMap;(区别在于@QueryMap的数据体现在URL上,@FieldMap的数据体现在请求体上;但生成的数据是一致的。)
 * @Path                网络请求接口的方法的参数   URL缺省值
 * @URL                 网络请求接口的方法的参数   URL设置
 */

/**
 * Retrofit说明文档
 * Retrofit支持多种数据解析方式
 *
 * 使用时需要在Gradle添加依赖
 *
 * 数据解析器        Gradle依赖
 * Gson             com.squareup.retrofit2:converter-gson:2.0.2
 * Jackson          com.squareup.retrofit2:converter-jackson:2.0.2
 * Simple XML       com.squareup.retrofit2:converter-simplexml:2.0.2
 * Protobuf         com.squareup.retrofit2:converter-protobuf:2.0.2
 * Moshi            com.squareup.retrofit2:converter-moshi:2.0.2
 * Wire             com.squareup.retrofit2:converter-wire:2.0.2
 * Scalars          com.squareup.retrofit2:converter-scalars:2.0.2
 */

/**
 * 关于网络请求适配器(CallAdapter)
 * Retrofit支持多种网络请求适配器方式:guava、Java8和rxJava
 *
 * 使用时如使用的是 Android 默认的 CallAdapter，则不需要添加网络请求适配器的依赖，
 * 否则则需要按照需求进行添加Retrofit 提供的 CallAdapter。
 *
 * 使用时需要添加的依赖
 *
 * 网络请求适配器                  Gradle依赖
 * guava                         com.squareup.retrofit2:adapter-guava:2.0.2
 * Java8                         com.squareup.retrofit2:adapter-java8:2.0.2
 * rxjava                        com.squareup.retrofit2:adapter-rxjava:2.0.2
 */
public interface RetrofitDocument {
}
