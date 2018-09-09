package com.lznby.bigdemo.lznby.recycleview.mutilType;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.recycleview.mutilType.adapter.MulitAdapter;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.BannerBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.ContentBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.decorate.Visitable;
import com.lznby.bigdemo.tools.ARouterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Note: RecycleView 多类型 item 使用。
 * 参考地址：https://www.jianshu.com/p/26615b22fe9e
 * @author Lznby
 */
@Route(path = ARouterTools.MultiTypeRecycleViewActivity)
public class MultiTypeRecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.rv_mutli_type)
    RecyclerView mRvMutliType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type_recycle_view);
        ButterKnife.bind(this);

        //1.创建多类型ViewHolderAdapter,并传入数据
        MulitAdapter mulitAdapter = new MulitAdapter(initData());
        //2.创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //3.为RecyclerView设置布局管理器
        mRvMutliType.setLayoutManager(linearLayoutManager);
        //4.为RecyclerView设置adapter
        mRvMutliType.setAdapter(mulitAdapter);
    }

    /**
     * 模拟数据
     */
    private List<Visitable> initData() {
        List<Visitable> visitables = new ArrayList<>();
        visitables.add(new BannerBean("www.baidu.com"));
        visitables.add(new ContentBean("标题6666"));
        visitables.add(new BannerBean("www.baidu.com"));
        visitables.add(new ContentBean("标题6666"));
        visitables.add(new ContentBean("标题6666"));
        visitables.add(new ContentBean("标题6666"));
        return visitables;
    }
}
