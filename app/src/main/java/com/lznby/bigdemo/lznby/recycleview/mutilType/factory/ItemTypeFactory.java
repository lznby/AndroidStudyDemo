package com.lznby.bigdemo.lznby.recycleview.mutilType.factory;

import android.view.View;

import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.BannerBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.ContentBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder.BannerViewHolder;
import com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder.BaseViewHolder;
import com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder.ContentViewHolder;

/**
 * @author Lznby
 * @time 2018/9/4 22:56
 * Class Note: 实现type工厂类
 */
public class ItemTypeFactory implements TypeFactory{
    /**
     * 将id作为type传入adapter
     */

    /**
     * 图片类型布局
     */
    public static final int BANNER_ITEM_LAYOUT = R.layout.item_banner_mulittype;
    /**
     * 文字类型布局
     */
    public static final int CONTENT_ITEM_LAYOUT = R.layout.item_content_mulittype;


    @Override
    public int type(BannerBean bannerBean) {
        return BANNER_ITEM_LAYOUT;
    }

    @Override
    public int type(ContentBean contentBean) {
        return CONTENT_ITEM_LAYOUT;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case BANNER_ITEM_LAYOUT:
                return new BannerViewHolder(itemView);
            case CONTENT_ITEM_LAYOUT:
                return new ContentViewHolder(itemView);
            default:
                return null;
        }
    }
}
