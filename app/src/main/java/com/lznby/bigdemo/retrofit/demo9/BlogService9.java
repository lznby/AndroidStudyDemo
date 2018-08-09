package com.lznby.bigdemo.retrofit.demo9;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lznby
 * @time 2018/7/9 14:02
 * Class Note: [Retrofit 自定义Converter]
 */
public interface BlogService9 {
    @GET("blog/{id}")
    Call<String> getBlog(@Path("id") int id);
}
