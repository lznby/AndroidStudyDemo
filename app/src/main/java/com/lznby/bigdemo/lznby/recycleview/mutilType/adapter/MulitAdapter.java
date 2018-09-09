package com.lznby.bigdemo.lznby.recycleview.mutilType.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lznby.bigdemo.lznby.recycleview.mutilType.decorate.Visitable;
import com.lznby.bigdemo.lznby.recycleview.mutilType.factory.ItemTypeFactory;
import com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @author Lznby
 * @time 2018/9/4 22:36
 * Class Note: 多类型RecycleView的Adapter
 */
public class MulitAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    /**
     * Type工厂类对象
     */
    private ItemTypeFactory mItemTypeFactory;

    /**
     * item bean 接口集合(多态存储多类型bean)
     */
    List<Visitable> mVisitables;

    /**
     * 构造方法
     */
    public MulitAdapter(List<Visitable> data) {
        this.mItemTypeFactory = new ItemTypeFactory();
        mVisitables = data;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);

        return mItemTypeFactory.createViewHolder(viewType,view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindViewData(mVisitables.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mVisitables.get(position).type(mItemTypeFactory);
    }

    @Override
    public int getItemCount() {
        return (mVisitables != null ? mVisitables.size() : 0);
    }
}
