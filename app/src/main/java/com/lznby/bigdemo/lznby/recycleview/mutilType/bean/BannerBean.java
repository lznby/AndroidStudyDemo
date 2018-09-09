package com.lznby.bigdemo.lznby.recycleview.mutilType.bean;

import com.lznby.bigdemo.lznby.recycleview.mutilType.decorate.Visitable;
import com.lznby.bigdemo.lznby.recycleview.mutilType.factory.TypeFactory;

/**
 * @author Lznby
 * @time 2018/9/4 22:25
 * Class Note: bean 图片 实现item bean 统一接口
 */
public class BannerBean implements Visitable{
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BannerBean(String url) {
        this.url = url;
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
