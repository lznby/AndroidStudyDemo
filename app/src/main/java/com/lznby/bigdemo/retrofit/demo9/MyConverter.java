package com.lznby.bigdemo.retrofit.demo9;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Lznby
 * @time 2018/7/9 14:16
 * Class Note: [Retrofit 自定义Converter]
 */
public class MyConverter {

    /**
     * 自定义Converter实现RequestBody到String的转换
     */
    public static class StringConverter implements Converter<ResponseBody, String> {

        public static final StringConverter INSTACE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    /**
     * 用于向Retrofit提供StringConverter
     */
    public static class StringConverterFactory extends Converter.Factory {

        public static final StringConverterFactory INSTANCE = new StringConverterFactory();

        public static StringConverterFactory create() {
            return INSTANCE;
        }

        //我们只关注实现从ResponseBody 到 String 的转换，所以其他方法可以不覆盖
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == String.class) {
                return StringConverter.INSTACE;
            }
            //其他类型我们不处理，返回null就行
            return null;
        }
    }
}
