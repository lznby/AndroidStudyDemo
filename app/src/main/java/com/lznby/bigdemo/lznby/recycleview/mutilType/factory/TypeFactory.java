package com.lznby.bigdemo.lznby.recycleview.mutilType.factory;

import android.view.View;

import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.BannerBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.ContentBean;
import com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder.BaseViewHolder;

/**
 * @author Lznby
 * @time 2018/9/4 22:19
 * Class Note: bean type 工厂类型接口
 */
public interface TypeFactory {

    /**
     * BannerBean类型
     * @param bannerBean
     * @return
     */
    int type(BannerBean bannerBean);

    /**
     * ContentBean类型
     * @param contentBean
     * @return
     */
    int type(ContentBean contentBean);

    /**
     * 根据type创建对应的viewHolder
     * @param type
     * @param itemView
     * @return
     */
    BaseViewHolder createViewHolder(int type, View itemView);

}
