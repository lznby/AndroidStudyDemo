package com.lznby.bigdemo.retrofit.demo7;

import com.lznby.bigdemo.retrofit.entity.Blog;
import com.lznby.bigdemo.retrofit.entity.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Lznby
 * @time 2018/7/9 11:03
 * Class Note: [Retrofit Converter 序列化]源码
 */
public interface BlogService7 {
    @POST("blog")
    Call<Result<Blog>> createBlog(@Body Blog blog);
}
