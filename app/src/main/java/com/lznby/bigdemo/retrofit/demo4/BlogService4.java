package com.lznby.bigdemo.retrofit.demo4;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * @author Lznby
 * @time 2018/7/9 8:48
 * Class Note: [Retrofit注解详解 之 FormUrlEncoded/Field/FieldMap注解]源码
 */
public interface BlogService4 {
    @GET("/headers?showAll=true")
    @Headers({"CustomHeader1: customHeaderValuue1", "CustomHeader2: customHeaderValue2"})
    Call<ResponseBody> testHeader(@Header("CustomHeader3") String customHeaderValue3);
}
