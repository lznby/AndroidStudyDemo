package com.lznby.bigdemo.lznby.okhttp.jsonUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Lznby
 * @time 2018/9/9 21:23
 * Class Note:
 * 1.Gson泛型封装
 * 2.参考地址：https://www.jianshu.com/p/d62c2be60617
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private final Class raw;

    private final Type[] args;

    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
