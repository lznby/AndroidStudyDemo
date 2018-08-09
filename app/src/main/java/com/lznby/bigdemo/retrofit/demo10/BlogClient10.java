package com.lznby.bigdemo.retrofit.demo10;

import com.lznby.bigdemo.retrofit.demo9.MyConverter;
import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lznby
 * @time 2018/7/9 15:41
 * Class Note: [Retrofit 自定义 CallAdapter]源码
 */
public class BlogClient10 {

    public static void invokeInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .addConverterFactory(MyConverter.StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(MyCallAdapter.CustomCallAdapterFactory.INSTANCE)
                .build();

        BlogService10 service10 = retrofit.create(BlogService10.class);
        MyCallAdapter.CustomCall<String> call = service10.getBlog(2);
        call.call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogUtil.e("返回结果:"+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
