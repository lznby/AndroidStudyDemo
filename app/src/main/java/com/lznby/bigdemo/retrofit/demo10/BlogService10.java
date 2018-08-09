package com.lznby.bigdemo.retrofit.demo10;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lznby
 * @time 2018/7/9 15:41
 * Class Note: [Retrofit 自定义 CallAdapter]源码
 */
public interface BlogService10 {
        @GET("blog/{id}")
        MyCallAdapter.CustomCall<String> getBlog(@Path("id") int id);
}
