package com.lznby.bigdemo.retrofit.demo1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note:Retrofit2接口定义
 */
public interface BlogService {
    @GET("blog/{id}")
    Call<ResponseBody> getBlog(@Path("id") int id);
}
