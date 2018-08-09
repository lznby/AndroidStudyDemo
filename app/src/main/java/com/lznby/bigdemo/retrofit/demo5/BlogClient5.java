package com.lznby.bigdemo.retrofit.demo5;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.retrofit.utils.ResponseBodyPrinter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author Lznby
 * @time 2018/7/9 9:55
 * Class Note: [Retrofit注解详解 之 FormUrlEncoded/Field/FieldMap注解]源码
 */
public class BlogClient5 {
    public static void invokeInterface() {
        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .build();

        //2.使用Retrofit创建一个BlogService2的代理对象
        BlogService5 service5 = retrofit.create(BlogService5.class);

        //3.接口调用,样式 @Headers 和 @Header
        Call<ResponseBody> call = service5.testUrlAndQuery("headers",false);

        //4.打印请求结果
        ResponseBodyPrinter.printResponseBody(call);
    }
}
