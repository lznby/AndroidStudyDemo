package com.lznby.bigdemo.retrofit.demo2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * @author Lznby
 * @time 2018/7/7 12:14
 * Class Note: Retrofit2接口定义
 */
public interface BlogService2 {
    /**
     * method 表示请求的方法，区分大小写，retrofit 不会做处理
     * path表示路径
     * hasBody表示是否有请求体
     */
    @HTTP(method = "GET", path = "blog/{id}", hasBody = false)
    Call<ResponseBody> getBlog(@Path("id") int id);
}
