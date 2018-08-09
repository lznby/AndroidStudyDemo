package com.lznby.bigdemo.retrofit.demo2;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.retrofit.utils.ResponseBodyPrinter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
/**
 * @author Lznby
 * @time 2018/7/7 12:17
 * Class Note: [Retrofit注解详解 之 HTTP注解]源码
 */
public class BlogClient2 {
    public static void invokeInterface(){
        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .build();

        //2.使用Retrofit创建一个BlogService2的代理对象
        BlogService2 service2 = retrofit.create(BlogService2.class);

        //3.接口调用
        Call<ResponseBody> call = service2.getBlog(2);

       //4.打印请求结果
        ResponseBodyPrinter.printResponseBody(call);
    }
}
