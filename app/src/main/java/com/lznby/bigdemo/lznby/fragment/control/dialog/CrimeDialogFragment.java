package com.lznby.bigdemo.lznby.fragment.control.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lznby.bigdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Lznby
 * @time 2018/9/6 11:36
 * Class Note:
 * 1.Dialog与Fragment的结合使用.
 */
public class CrimeDialogFragment extends Fragment {
    Unbinder unbinder;

    /**
     *
     */
    private static final String DIALOG_DATE = "DialogDate";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_dialog_fragment_content, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_dialog_fragment)
    public void onViewClicked() {
        //创建并显示DialogFragment
        FragmentManager manager = getFragmentManager();
        MyDialogFragment dialogFragment = new MyDialogFragment();
        //设置为DialogFragment设置tag
        dialogFragment.show(manager,DIALOG_DATE);
    }
}
