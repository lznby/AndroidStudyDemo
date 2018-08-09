package com.lznby.bigdemo.retrofit.demo7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lznby.bigdemo.retrofit.entity.Blog;
import com.lznby.bigdemo.retrofit.entity.Result;
import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lznby
 * @time 2018/7/9 11:03
 * Class Note: [Retrofit Converter 序列化]源码
 */
public class BlogClient7 {

    public static void invokeInterface() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                //可以传递接收的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //2.使用Retrofit创建一个BlogService2的代理对象
        BlogService7 service7 = retrofit.create(BlogService7.class);

        Blog blog = new Blog();
        blog.content = "新建的Blog";
        blog.title = "测试";
        blog.author = "怪盗kidou";

        //3.调用接口
        Call<Result<Blog>> call = service7.createBlog(blog);

        //4.重写回调方法
        call.enqueue(new Callback<Result<Blog>>() {
            @Override
            public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {
                //已转换为想要的类型了
                Result<Blog> result = response.body();
                LogUtil.e("返回结果:" + result);
            }

            @Override
            public void onFailure(Call<Result<Blog>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
