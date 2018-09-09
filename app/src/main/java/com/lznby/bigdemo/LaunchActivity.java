package com.lznby.bigdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lznby
 * Note:启动界面
 */
@Route(path = ARouterTools.LaunchActivity)
public class LaunchActivity extends AppCompatActivity {

    /**
     * 使用黄油刀绑定多个onClick事件,可以使用插件ButterKnifeZelezny快速生成。
     * 1.下载安装完插件;
     * 2.在代码中选中布局id(此处为R.layout.activity_launch)
     * 3.Alt+Insert
     * 4.选择Generate ButteKnife Injections
     * 5.选择需要自动生成的控件及点击事件。
     * @param view
     */
    @OnClick({R.id.bt_lznby, R.id.bt_code_dog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_lznby:
                ARouter.getInstance().build(ARouterTools.MainActivity).navigation();
                break;
            case R.id.bt_code_dog:
                ARouter.getInstance().build(ARouterTools.WybMainActivity).navigation();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
    }
}
