package com.lznby.bigdemo.retrofit.demo9;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lznby
 * @time 2018/7/9 14:02
 * Class Note: [Retrofit 自定义Converter]
 */
public class BlogClient9 {

    public static void invokeInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                //如果有Gson这类的Converter 一定放两个在其前面
                .addConverterFactory(MyConverter.StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BlogService9 service9 = retrofit.create(BlogService9.class);
        Call<String> call = service9.getBlog(2);
        call.enqueue(new Callback<String>() {
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
