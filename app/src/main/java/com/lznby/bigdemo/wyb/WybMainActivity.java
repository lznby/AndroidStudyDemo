package com.lznby.bigdemo.wyb;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.databinding.ActivityWybMainBinding;
import com.lznby.bigdemo.tools.ARouterTools;

@Route(path = ARouterTools.WybMainActivity)
public class WybMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWybMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_wyb_main);

        binding.setVm(this);
    }

    public void onNetworkClick(View view) {
        ARouter.getInstance().build(ARouterTools.NetworkActivity).navigation();
    }
}
