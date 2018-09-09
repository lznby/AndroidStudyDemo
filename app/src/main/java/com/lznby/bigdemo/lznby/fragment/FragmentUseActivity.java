package com.lznby.bigdemo.lznby.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lznby
 * Note: Fragment 使用入口界面
 */
@Route(path = ARouterTools.FragmentUseActivity)
public class FragmentUseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_use);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_fragment_base_use, R.id.bt_viewpager_intent,R.id.bt_fragment_recycler_view,R.id.bt_fragment_arguments,R.id.bt_dialog_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_fragment_base_use:
                //启动CrimeActivity页面(Fragment的基本使用)
                ARouter.getInstance().build(ARouterTools.CrimeActivity).navigation();
                break;
            case R.id.bt_viewpager_intent:
                //启动CrimeActivity页面(Fragment间传值以及Fragment与Activity间传值,简单Fragment与ViewPager使用)
                ARouter.getInstance().build(ARouterTools.CrimePagerActivity).navigation();
                break;
            case R.id.bt_fragment_recycler_view:
                //启动CrimeRecycleViewFragmentActivity页面(Fragment与RecyclerView的结合使用)
                ARouter.getInstance().build(ARouterTools.CrimeRecycleViewFragmentActivity).navigation();
                break;
            case R.id.bt_fragment_arguments:
                //启动CrimeArgumentsActivity页面(Fragment与Activity间使用arguments方式进行传值)
                ARouter.getInstance().build(ARouterTools.CrimeArgumentsActivity).navigation();
                break;
            case R.id.bt_dialog_fragment:
                //CrimeDialogFragmentActivity(DialogFragment的简单使用)
                ARouter.getInstance().build(ARouterTools.CrimeDialogFragmentActivity).navigation();
                break;
            default:
                break;
        }
    }
}
