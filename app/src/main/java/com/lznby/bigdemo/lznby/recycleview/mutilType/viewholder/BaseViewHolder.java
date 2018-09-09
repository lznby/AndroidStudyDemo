package com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * @author Lznby
 * @time 2018/9/4 22:42
 * Class Note:多类型 RecyclerView Adapter 的 ViewHolder 基类
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    /**
     * 当前item 的 view
     */
    private View itemView;

    /**
     *
     */
    SparseArray<View> views;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        //初始化存储view的集合
        views = new SparseArray<View>();
    }

    /**
     * 获取当前item的view
     * @param resId
     * @return
     */
    public View getViews(int resId) {
        View view = views.get(resId);
        if (view == null) {
            view = itemView;
            views.put(resId, itemView);
        }
        return view;
    }

    /**
     * 绑定itemView的数据
     * @param data
     */
    public abstract void bindViewData(T data);
}
