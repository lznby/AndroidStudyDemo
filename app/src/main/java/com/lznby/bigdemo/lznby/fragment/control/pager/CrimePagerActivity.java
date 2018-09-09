package com.lznby.bigdemo.lznby.fragment.control.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.fragment.modle.Crime;
import com.lznby.bigdemo.lznby.fragment.modle.CrimeLab;
import com.lznby.bigdemo.tools.ARouterTools;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * @time 2018/9/5 20:36
 * Class Note:
 * 1.fragment arguments 用于 activity 传递给 fragment, 或 fragment 间传递值。两种用法基本相同。
 * 2.fragment 和 ViewPager 的简单使用。
 */
@Route(path = ARouterTools.CrimePagerActivity)
public class CrimePagerActivity extends AppCompatActivity {

    /**
     * 用于标记UUID传递的key值
     */
    private static final String EXTRA_CRIME_ID = "pager_crime_id";

    /**
     * ViewPager
     */
    @BindView(R.id.vp_fragment_viewpager)
    ViewPager mVpFragmentViewpager;


    /**
     * ViewPager的数据
     */
    private List<Crime> mCrimes;


    /**
     * 启动activity的方法
     *
     * @param packageContent
     * @param uuid
     * @return
     */
    public static Intent newIntent(Context packageContent, UUID uuid) {
        Intent intent = new Intent(packageContent, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        ButterKnife.bind(this);

        //获取UUID(传递的信息)
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        //获取ViewPager显示的数据
        mCrimes = CrimeLab.get(this).getCrimes();

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //为ViewPager设置Adapter
        mVpFragmentViewpager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                //返回当前ViewPager对应的Fragment
                return CrimeFragment.newInstance(crime.getUUID());
            }

            @Override
            public int getCount() {
                //返回ViewPager页面数目
                return mCrimes.size();
            }
        });

    }
}
