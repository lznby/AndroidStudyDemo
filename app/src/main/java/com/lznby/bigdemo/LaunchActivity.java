package com.lznby.bigdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * Note:启动界面
 */
@Route(path = ARouterTools.LaunchActivity)
public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.bt_lznby)
    Button btLznby;
    @BindView(R.id.bt_code_dog)
    Button btCodeDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        btLznby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterTools.MainActivity).navigation();
            }
        });

        btCodeDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
