package com.lznby.bigdemo.retrofit.demo6;

import com.lznby.bigdemo.retrofit.entity.Blog;
import com.lznby.bigdemo.retrofit.entity.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lznby
 * @time 2018/7/9 10:19
 * Class Note: [Retrofit Converter 反序列]源码
 */
public interface BlogService6 {
    @GET("blog/{id}")
    Call<Result<Blog>> getBlog(@Path("id") int id );
}
