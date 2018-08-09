package com.lznby.bigdemo.retrofit.demo8;

import com.lznby.bigdemo.retrofit.entity.Blog;
import com.lznby.bigdemo.retrofit.entity.Result;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Lznby
 * @time 2018/7/9 11:26
 * Class Note: [Retrofit CallAdapter rxJava]源码
 */
public interface BlogService8 {
    @GET("/blog")
    Observable<Result<List<Blog>>> getBlogs(@Query("page") int page);
        /*
          「20160608补充」如果需要Header的值，可以把返回值替换为
            Observable<Response<Result<List<Blog>>>>
            Observable<retrofit2.adapter.rxjava.Result<Result<List<Blog>>>> //都叫Result，真是失策
         */

}
