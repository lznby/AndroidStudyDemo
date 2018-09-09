package com.lznby.bigdemo.lznby.fragment.control.arguments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lznby.bigdemo.R;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Lznby
 * @time 2018/9/5 22:21
 * Class Note: fragment 使用 argument 传值
 * 1.fragment arguments 用于 activity 传值给 fragment 或 fragment 间传值。两者用法基本相同。
 */
public class CrimeArgumentsFragment extends Fragment {
    /**
     * 标记传值的key值
     */
    private static final String ARG_CRIME_ID = "crime_id";
    @BindView(R.id.tv_fragment_arguments)
    TextView mTvFragmentViewpager;
    Unbinder unbinder;

    /**
     * 存放过来的传递的值
     */
    private UUID mUUID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         *arguments取值方法
         */
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUUID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_crime_arguments, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 传入需要的参数，设置arguments，用于传值。
     * @param uuid
     * @return
     */
    public static CrimeArgumentsFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,uuid);

        CrimeArgumentsFragment fragment = new CrimeArgumentsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
