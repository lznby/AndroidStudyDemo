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
import butterknife.OnClick;

/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note: Lznby'Demo HomePage
 */
@Route(path = ARouterTools.MainActivity)
public class MainActivity extends AppCompatActivity {

    //1.使用黄油刀绑定视图

    @BindView(R.id.bt_retrofit)
    Button mBtRetrofit;
    @BindView(R.id.bt_animation)
    Button mBtAnimation;
    @BindView(R.id.bt_rxjava2)
    Button mBtRxjava2;
    @BindView(R.id.bt_smart_table)
    Button mBtSmartTable;
    @BindView(R.id.bt_fragment)
    Button mBtFragment;
    @BindView(R.id.bt_recycle_view)
    Button mBtRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2.将黄油刀框架绑定到这个Activity上
        ButterKnife.bind(this);

    }


    /**
     * 2.使用黄油刀绑定点击监听事件
     *
     * @param view
     */
    @OnClick({R.id.bt_retrofit, R.id.bt_animation, R.id.bt_rxjava2, R.id.bt_smart_table, R.id.bt_fragment,R.id.bt_recycle_view,R.id.bt_ok_http})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                break;
            case R.id.bt_smart_table:
                ARouter.getInstance().build(ARouterTools.SmartTableActivity).navigation();
                break;
            case R.id.bt_fragment:
                //启动Fragment页面
                ARouter.getInstance().build(ARouterTools.FragmentUseActivity).navigation();
                break;
            case R.id.bt_recycle_view:
                //启动RecycleView页面
                ARouter.getInstance().build(ARouterTools.RecycleViewActivity).navigation();
                break;
            case R.id.bt_ok_http:
                //启动OkHttpActivity页面
                ARouter.getInstance().build(ARouterTools.OkHttpActivity).navigation();
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

}
