package com.lznby.bigdemo.lznby.fragment.control.recycleview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.fragment.modle.Crime;
import com.lznby.bigdemo.lznby.fragment.modle.CrimeLab;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Lznby
 * @time 2018/9/5 21:18
 * Class Note:
 * 1.Fragment 结合 RecyclerView的使用.
 */
public class CrimeListFragment extends Fragment {

    @BindView(R.id.rv_fragment_recycler_view)
    RecyclerView mRvFragmentRecyclerView;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.设置Fragment布局填充到父容器
        View view = inflater.inflate(R.layout.activity_crime_recycle_view_fragment, container, false);

        unbinder = ButterKnife.bind(this, view);

        //2.外RecyclerView添加布局管理器
        mRvFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //3.为Adapter设置数据,并为RecyclerView设置Adapter
        initData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 处理与RecyclerView相关的业务
     */
    void initData() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        //1.为Adapter设置数据
        CrimeAdapter crimeAdapter = new CrimeAdapter(crimes);
        //2.为RecyclerView设置Adapter
        mRvFragmentRecyclerView.setAdapter(crimeAdapter);

    }

    /**
     * 定义 RecyclerView Adapter 的 ViewHolder
     */
    class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        @BindView(R.id.crime_content)
        TextView mCrimeContent;
        @BindView(R.id.crime_id)
        TextView mCrimeId;

        /**
         * 构造方法
         * @param inflater
         * @param parent
         */
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            //填充item布局到父容器
            super(inflater.inflate(R.layout.crime_recycle_item, parent, false));

            //绑定item布局中的控件
            itemView.setOnClickListener(this);

            ButterKnife.bind(this,itemView);

        }

        /**
         * 优化
         * @param crime
         */
        public void bind(Crime crime) {
            //用于在Adapter设置item中控件属性
            mCrime = crime;
            mCrimeId.setText(mCrime.getUUID().toString());
            mCrimeContent.setText(mCrime.getContent());
        }


        /**
         * 设置RecyclerView item 的点击事件.
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),"我点击的是"+mCrime.getContent(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 定义RecyclerView的Adapter
     */
    class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        /**
         * RecyclerView数据
         */
        private List<Crime> mCrimes;

        /**
         * 构造方法
         * @param crimes
         */
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //1.获取布局填充器
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //2.返回一个ViewHolder对象
            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            //1.获取数据
            Crime crime = mCrimes.get(position);
            //2.为ViewHolder绑定数据
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            //1.返回RecyclerView的数量
            return mCrimes.size();
        }
    }
}
