package com.lznby.bigdemo.lznby.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lznby
 * Note: RecycleView 的基本使用
 */
@Route(path = ARouterTools.RecycleViewActivity)
public class RecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.bt_recycle_view_multi_type)
    Button mBtRecycleViewMultiType;
    @BindView(R.id.bt_recycle_view_base)
    Button mBtRecycleViewBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_recycle_view_multi_type, R.id.bt_recycle_view_base})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_recycle_view_multi_type:
                ARouter.getInstance().build(ARouterTools.MultiTypeRecycleViewActivity).navigation();
                break;
            case R.id.bt_recycle_view_base:
                break;
            default:
                break;
        }
    }
}
