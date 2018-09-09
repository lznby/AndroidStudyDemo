package com.lznby.bigdemo.lznby.fragment.control.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.fragment.modle.CrimeLab;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Lznby
 * @time 2018/9/5 20:18
 * Class Note:
 * 1.fragment arguments 用于 activity 传递给 fragment, 或 fragment 间传递值。两种用法基本相同。
 */
public class CrimeFragment extends Fragment {

    /**
     * 标记传递的key值
     */
    private static final String ARG_CRIME_ID = "crime_id";
    @BindView(R.id.tv_fragment_arguments)
    TextView mTvFragmentViewpager;
    Unbinder unbinder;
    /**
     * 用于存放传递的值
     */
    private UUID mUUID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * arguments取值方法
         */
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUUID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viwe_pager, container, false);
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        unbinder = ButterKnife.bind(this, view);
        mTvFragmentViewpager.setText(crimeLab.getCrime(mUUID).getContent());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 传入需要的参数，设置给arguments,用于传值
     * @param uuid
     * @return
     */
    public static CrimeFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,uuid);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
