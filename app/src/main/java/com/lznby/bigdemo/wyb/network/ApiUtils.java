package com.lznby.bigdemo.wyb.network;

import android.content.Context;

import com.lznby.bigdemo.BuildConfig;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 利用枚举类型来实现单例设计模式 详情参见
 * https://blog.csdn.net/normallife/article/details/51152246
 * https://www.cnblogs.com/cielosun/p/6582333.html
 *
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/8/23
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public enum ApiUtils {

    INSTANCE;

    public Api getApi(Context context) {
        UserInterceptor userInterceptor = new UserInterceptor(context);
        OkHttpClient client = provideOkHttpClient().newBuilder()
                .addInterceptor(userInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Api.HOST)
                //使用Gson作为数据转换器
                .addConverterFactory(GsonConverterFactory.create())
                //使用RxJava2作为回调适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                //添加网络访问工具OKHttpClient
                .callFactory(client)
                .build()
                .create(Api.class);
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                //超时时间
                .readTimeout(30, TimeUnit.SECONDS)
                // HTTP过滤器 方便日志打印查看
                .addInterceptor(new HttpLoggingInterceptor())
                //                .cache(new Cache(new File(""),1024*1024*20))
                .build().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder.build();
    }
}
