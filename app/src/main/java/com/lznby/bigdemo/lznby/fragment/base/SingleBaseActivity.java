package com.lznby.bigdemo.lznby.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.lznby.bigdemo.R;

/**
 * @author Lznby
 * @time 2018/9/4 9:29
 * Class Note: 仅用于填充一个Fragment的Activity基类.
 * Fragment:使用v4包中的Fragment
 */
public abstract class SingleBaseActivity extends AppCompatActivity {
    /**
     * 返回需要填充的碎片
     * @return
     */
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置碎片容器布局
        setContentView(R.layout.activity_fragment);

        //获取碎片管理器(v4包中的)
        FragmentManager fragmentManager = getSupportFragmentManager();
        //获取碎片布局中的Frame容器
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //当填充的碎片不为空,将碎片填充进碎片容器(帧布局)中.
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }


    }
}
