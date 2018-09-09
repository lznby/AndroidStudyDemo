package com.lznby.bigdemo.lznby.fragment.control.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lznby.bigdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.Unbinder;

/**
 * @author Lznby
 * @time 2018/9/4 9:55
 * Class Note: Fragment的基本使用
 */
public class CrimeFragment extends Fragment{

    /**
     * 1.ButterKnife在Fragment中使用需要解绑(在onDestroyView中进行)。
     * 2.ButterKnife在Activity中使用不需要解绑。
     */
    private Unbinder mUnbinder;

    @BindView(R.id.tv_show)
    TextView mTextView;

    @OnLongClick(R.id.bt_long_touch)
    public boolean showToastLongTouch() {
        Toast.makeText(getActivity(),"黄油刀绑定长按事件", Toast.LENGTH_SHORT).show();
        return true;
    }

    @OnClick(R.id.bt_short_touch)
    public void showToastShortTouch() {
        Toast.makeText(getActivity(),"黄油刀绑定点击事件", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment,container,false);
        //黄油刀绑定
       mUnbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /**
         * Fragment中需要解绑ButterKnife。
         */
        mUnbinder = ButterKnife.bind(getActivity());
    }
}
