package com.lznby.bigdemo.lznby.fragment.control.dialog;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.lznby.fragment.base.SingleBaseActivity;
import com.lznby.bigdemo.tools.ARouterTools;

/**
 * @author Lznby
 * Note：
 * 1.Dialog与Fragment的结合使用.
 */
@Route(path = ARouterTools.CrimeDialogFragmentActivity)
public class CrimeDialogFragmentActivity extends SingleBaseActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeDialogFragment();
    }
}
