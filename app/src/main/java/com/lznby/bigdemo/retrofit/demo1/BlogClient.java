package com.lznby.bigdemo.retrofit.demo1;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.retrofit.utils.ResponseBodyPrinter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author Lznby
 * @time 2018/7/7 10:52
 * Class Note: Retrofit2接口调用
 */
public class BlogClient {

    public static void invokeInterface() {
        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .build();

        //2.使用Retrofit创建一个BlogService的代理对象
        BlogService service = retrofit.create(BlogService.class);

        //3.接口调用
        Call<ResponseBody> call = service.getBlog(2);

        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        ResponseBodyPrinter.printResponseBody(call);
    }
}
