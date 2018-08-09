package com.lznby.bigdemo.retrofit.demo4;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.retrofit.utils.ResponseBodyPrinter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author Lznby
 * @time 2018/7/9 8:47
 * Class Note: [Retrofit注解详解 之 FormUrlEncoded/Field/FieldMap注解]源码
 */
public class BlogClient4 {
    public static void invokeInterface() {
        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .build();

        //2.使用Retrofit创建一个BlogService2的代理对象
        BlogService4 service4 = retrofit.create(BlogService4.class);

        //3.接口调用,样式 @Headers 和 @Header
        Call<ResponseBody> call = service4.testHeader("ikidou");

        //4.打印请求结果
        ResponseBodyPrinter.printResponseBody(call);
    }
}
