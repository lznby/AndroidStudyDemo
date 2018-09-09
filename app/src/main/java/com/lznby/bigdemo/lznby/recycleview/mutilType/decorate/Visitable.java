package com.lznby.bigdemo.lznby.recycleview.mutilType.decorate;

import com.lznby.bigdemo.lznby.recycleview.mutilType.factory.TypeFactory;

/**
 * @author Lznby
 * @time 2018/9/4 22:22
 * Class Note:所有 item bean 统一实现该接口
 */
public interface Visitable {
    /**
     * 所有 item bean 统一实现类型抽象方法
     * @param typeFactory
     * @return
     */
    int type(TypeFactory typeFactory);
}
