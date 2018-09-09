package com.lznby.bigdemo.lznby.recycleview.mutilType.bean;

import com.lznby.bigdemo.lznby.recycleview.mutilType.decorate.Visitable;
import com.lznby.bigdemo.lznby.recycleview.mutilType.factory.TypeFactory;

/**
 * @author Lznby
 * @time 2018/9/4 22:26
 * Class Note: bean 文字 实现item bean 统一接口
 */
public class ContentBean implements Visitable{
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ContentBean(String title) {
        this.title = title;
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
