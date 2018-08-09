package com.lznby.bigdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.tools.ARouterTools;
import com.lznby.bigdemo.utils.Accessibility;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note: MainActivity
 */
@Route(path = ARouterTools.MainActivity)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //1.使用黄油刀绑定视图

    @BindView(R.id.bt_retrofit)
    Button btRetrofit;
    @BindView(R.id.bt_animation)
    Button btAnimation;
    @BindView(R.id.bt_rxjava2)
    Button btRxJava2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.将黄油刀框架绑定到这个Activity上
        ButterKnife.bind(this);

        //3.为Button设置监听
        initOnClickListener();
    }



    /**
     * 点击监听事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_retrofit:
                //启动retrofit界面
                ARouter.getInstance().build(ARouterTools.Retrofit2Activity).navigation();
                break;
            case R.id.bt_animation:
                //启动Animation页面
                ARouter.getInstance().build(ARouterTools.AnimationActivity).navigation();
                break;
            case R.id.bt_rxjava2:
                //启动RxJava2页面
                ARouter.getInstance().build(ARouterTools.RxJava2Activity).navigation();
            default:
                break;
        }
    }

    /**
     * 请求权限结果反馈，及错误提示
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //请求结果反馈
        Accessibility.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    /**
     * 设置点击事件监听
     */
    private void initOnClickListener() {
        btRetrofit.setOnClickListener(this);
        btAnimation.setOnClickListener(this);
        btRxJava2.setOnClickListener(this);
    }
}
