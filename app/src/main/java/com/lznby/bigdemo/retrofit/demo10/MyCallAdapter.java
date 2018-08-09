package com.lznby.bigdemo.retrofit.demo10;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * [Retrofit 自定义 CallAdapter]源码
 */
public class MyCallAdapter {

    public static class CustomCall<R> {

        public final Call<R> call;

        public CustomCall(Call<R> call) {
            this.call = call;
        }
    }

    public static class CustomCallAdapter implements CallAdapter<CustomCall<?>,CustomCall<?>> {

        private final Type responseType;

        //下面的responseType 方法需要数据的类型
        CustomCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public  CustomCall<?> adapt(Call<CustomCall<?>> call) {
            return new CustomCall<>(call);
        }
    }

    public static class CustomCallAdapterFactory extends CallAdapter.Factory {
        public static final CustomCallAdapterFactory INSTANCE = new CustomCallAdapterFactory();

        @Override
        public CallAdapter<?,?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            // 获取原始类型
            Class<?> rawType = getRawType(returnType);
            // 返回值必须是CustomCall并且带有泛型
            if (rawType == CustomCall.class && returnType instanceof ParameterizedType) {
                Type callReturnType = getParameterUpperBound(0, (ParameterizedType) returnType);
                return new CustomCallAdapter(callReturnType);
            }
            return null;
        }
    }

}
