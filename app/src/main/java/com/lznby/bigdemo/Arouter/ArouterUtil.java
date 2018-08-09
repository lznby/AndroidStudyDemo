package com.lznby.bigdemo.Arouter;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by arvin on 2017/12/6.
 */

public class ArouterUtil {

    public static void navigation(String url, Bundle bundle) {
        build(url, bundle).navigation();
    }

    public static void navigation(Uri url) {
        ARouter.getInstance().build(url).navigation();
    }


    private static Postcard build(String url, Bundle bundle) {
        return ARouter.getInstance()
                .build(url)
                //.withTransition(R.anim.push_right_in, R.anim.push_right_out) 动画
                .with(bundle);
    }

}
