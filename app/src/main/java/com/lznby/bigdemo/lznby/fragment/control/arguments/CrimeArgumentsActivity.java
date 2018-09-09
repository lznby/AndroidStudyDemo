package com.lznby.bigdemo.lznby.fragment.control.arguments;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.lznby.fragment.base.SingleBaseActivity;
import com.lznby.bigdemo.tools.ARouterTools;

/**
 * @author Lznby
 * Note:
 * 1.使用Arguments在Fragment与Activity之间进行传值。
 */
@Route(path = ARouterTools.CrimeArgumentsActivity)
public class CrimeArgumentsActivity extends SingleBaseActivity {

    @Override
    protected Fragment createFragment() {
        return CrimeArgumentsFragment.newInstance(null);
    }
}
