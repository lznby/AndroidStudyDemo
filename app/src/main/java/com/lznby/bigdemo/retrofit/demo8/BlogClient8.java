package com.lznby.bigdemo.retrofit.demo8;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lznby.bigdemo.retrofit.entity.Blog;
import com.lznby.bigdemo.retrofit.entity.Result;
import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.utils.LogUtil;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * @author Lznby
 * @time 2018/7/9 11:27
 * Class Note: [Retrofit CallAdapter rxJava]源码
 */
public class BlogClient8 {

    public static void invokeInterface() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        BlogService8 service8 = retrofit.create(BlogService8.class);

        service8.getBlogs(1)
                .observeOn(Schedulers.io())
                //android.os.NetworkOnMainThreadException异常出现时，.subscribeOn(Schedulers.newThread())
                //与 .subscribeOn(Schedulers.io()) 总有个能解决问题。
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Result<List<Blog>>>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("onError"+e);
                    }

                    @Override
                    public void onNext(Result<List<Blog>> listResult) {
                        LogUtil.e(""+listResult);
                    }
                });
    }
}
