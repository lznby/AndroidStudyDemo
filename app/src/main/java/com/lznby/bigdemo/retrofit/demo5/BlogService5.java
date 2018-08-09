package com.lznby.bigdemo.retrofit.demo5;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author Lznby
 * @time 2018/7/9 9:55
 * Class Note: [Retrofit注解详解 之 FormUrlEncoded/Field/FieldMap注解]源码
 */
public interface BlogService5 {
    /**
     * 当GET、POST...HTTP等方法中没有设置Url时，则必须使用{@link Url}提供
     * 对于Query和QueryMap，如果不是String（或Map的第二个泛型参数不是String）时
     * 会被默认会调用toString转换为String类型
     * Url支持的类型有 okhttp3.HttpUrl, String, java.net.URL, android.net.Url
     * {@link retrofit2.http.QueryMap} 用法和 {@link retrofit2.http.FieldMap} 用法一样，不在说明
     * @param url
     * @param showAll
     * @return
     */
    @GET //当这里有URL注解时，这里的URL就省略了
    Call<ResponseBody> testUrlAndQuery(@Url String url, @Query("showAll") boolean showAll);
}
