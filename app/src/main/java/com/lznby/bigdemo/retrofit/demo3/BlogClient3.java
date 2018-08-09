package com.lznby.bigdemo.retrofit.demo3;

import com.lznby.bigdemo.retrofit.utils.BaseApi;
import com.lznby.bigdemo.retrofit.utils.ResponseBodyPrinter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @author Lznby
 * @time 2018/7/8 11:08
 * Class Note: [Retrofit注解详解 之 FormUrlEncoded/Field/FieldMap/Multipart/Part/PartMap注解]源码
 */
public class BlogClient3 {
    public static void invokeInterface() {
        //1.创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .build();

        //2.使用Retrofit创建一个BlogService3的代理对象
        BlogService3 service3 = retrofit.create(BlogService3.class);

        //3.接口调用
        //4.打印请求结果

        //===================================================================================

        //演示 @FormUrlCoded 和 @Field
        Call<ResponseBody> call1 = service3.testFormUrlEncoded1("怪盗kidou", 24);
        ResponseBodyPrinter.printResponseBody(call1);

        //===================================================================================

        //演示 @FormUrlEncoded 和 @FieldMap
        //实现的效果与上面相同
        Map<String, Object> map = new HashMap<>();
        map.put("username", "怪盗kidou");
        map.put("age", 24);
        Call<ResponseBody> call2 = service3.testFormUrlEncoded2(map);
        ResponseBodyPrinter.printResponseBody(call2);

        //===================================================================================

        MediaType textType = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(textType, "怪盗kidou");
        RequestBody age = RequestBody.create(textType, "24");
        RequestBody file = RequestBody.create(MediaType.parse("application/octet-stream"), "这里是模拟文件的内容");

        //===================================================================================

        //演示 @Multipart 和 @Part
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        Call<ResponseBody> call3 = service3.testFileUpload1(name, age, filePart);
        ResponseBodyPrinter.printResponseBody(call3);

        //===================================================================================

        //演示 @Multipart 和 @PartMap
        //实现和上面相同的效果
        Map<String, RequestBody> fileUpload2Args = new HashMap<>();
        fileUpload2Args.put("name", name);
        fileUpload2Args.put("age", age);
        //这里并不会被当成文件，因为没有文件名(包含在Content-Disposition请求头上)，当上面的 filePart 有
//        fileUpload2Args.put("file",file);
        Call<ResponseBody> call4 = service3.testFileUpload2(fileUpload2Args,filePart);
        ResponseBodyPrinter.printResponseBody(call4);

        //===================================================================================

        // 还有一种比较hack的方式可以实现文件上传，
        // 上面说过被当成文件上传的必要条件就是 Content-Disposition 请求头中必须要有 filename="xxx" 才会被当成文件
        // 所有我们在写文件名的时候可以拼把 filename="XXX" 也拼接上去，
        // 即文件名变成  表单键名"; filename="文件名  （两端的引号会自动加，所以这里不加）也可以实现，但是不推荐方式

        Map<String, RequestBody> fileUpload3Args = new HashMap<>();
        fileUpload3Args.put("name",name);
        fileUpload3Args.put("age",age);
        fileUpload3Args.put("file\"; filename=\"test.txt",file);
        Call<ResponseBody> call5 = service3.testFileUpload3(fileUpload3Args);
        ResponseBodyPrinter.printResponseBody(call5);


        //===================================================================================

    }
}
