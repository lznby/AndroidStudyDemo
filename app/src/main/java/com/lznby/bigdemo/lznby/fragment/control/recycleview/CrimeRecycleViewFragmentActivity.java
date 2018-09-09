package com.lznby.bigdemo.lznby.fragment.control.recycleview;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.lznby.fragment.base.SingleBaseActivity;
import com.lznby.bigdemo.tools.ARouterTools;

/**
 * @author Lznby
 * @time 2018/9/5 21:26
 * Class Note:
 * 1.RecycleView结合Fragment使用.
 */
@Route(path = ARouterTools.CrimeRecycleViewFragmentActivity)
public class CrimeRecycleViewFragmentActivity extends SingleBaseActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
