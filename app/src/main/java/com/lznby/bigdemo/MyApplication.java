package com.lznby.bigdemo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Lznby
 * @time 2018/7/11 12:25
 * Class Note:
 */
public class MyApplication extends Application {

    /**
     * 标识是否为调试
     */
    private boolean isDebug = true;

    /**
     * Context
     */
    private static Context context;

    /**
     * 设置是否为调试阶段
     * @param debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 初始化Application
         */
        if (isDebug) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init( this );

        /**
         * 获取Application的Context
         */
        context = getApplicationContext();
    }

    /**
     * 全局获取Context
     * @return
     */
    public static Context getContext() {
        return context;
    }
}