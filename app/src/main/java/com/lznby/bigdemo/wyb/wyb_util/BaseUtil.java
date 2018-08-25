package com.lznby.bigdemo.wyb.wyb_util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/8/19
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class BaseUtil {
    /**
     * 判断网络连接状态
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
}
