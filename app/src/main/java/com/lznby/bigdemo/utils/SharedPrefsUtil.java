package com.lznby.bigdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Lznby
 * @time 2018/9/17 14:12
 * Class Note:
 * 1.SharedPreferences存储数据方式工具类
 * 2.SharedPreferences参考文章：https://blog.csdn.net/zuolongsnail/article/details/6556703
 * 3.关于sp中的commit和apply方法：https://blog.csdn.net/u010198148/article/details/51706483
 */
public class SharedPrefsUtil {
    /**
     * 这个随便填什么都行
     */
    public final static String SETTING = "Setting";

    /**
     * SharedPreferences缓存int类型
     * @param context
     * @param key
     * @param value
     */
    public static void putValue(Context context, String key, int value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.apply();
    }

    /**
     * SharedPreferences缓存boolean类型
     * @param context
     * @param key
     * @param value
     */
    public static void putValue(Context context,String key, boolean value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    /**
     * SharedPreferences缓存String类型
     * @param context
     * @param key
     * @param value
     */
    public static void putValue(Context context,String key, String value) {
        SharedPreferences.Editor sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    /**
     * SharedPreferences获取缓存的int类型值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getValue(Context context,String key, int defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * SharedPreferences获取缓存的boolean类型值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getValue(Context context,String key, boolean defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * SharedPreferences获取缓存的String类型值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getValue(Context context,String key, String defValue) {
        SharedPreferences sp =  context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}