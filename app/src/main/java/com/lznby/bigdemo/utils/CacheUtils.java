package com.lznby.bigdemo.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import okhttp3.Cache;

/**
 * @author Lznby
 * @time 2018/9/12 14:41
 * Class Note:
 * 1.缓存工具类
 * 2.关于Android缓存路径可以参考(文中有一点小问题,留意一下)：https://blog.csdn.net/u011494050/article/details/39671159
 * 3.如果手机上有SD卡优先将缓存存放到SD卡上,否者使用手机内部存储
 */
public class CacheUtils {

    /**
     * 获取当前应用缓存文件路径
     * @param context
     * @return
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 传入缓存大小MIB大小,自动转化为Byte,并返回。
     * @param cacheMib
     * @return
     */
    public static int getCacheSize(int cacheMib) {
        return cacheMib * 1024 * 1024;
    }

    /**
     * 获取Cache
     * @param context
     * @param cacheMib
     * @return
     */
    public static Cache getCache(Context context, int cacheMib) {
        return new Cache(new File(getDiskCacheDir(context)),getCacheSize(cacheMib));
    }
}
