package com.lznby.bigdemo.animation.usejava;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

import com.lznby.bigdemo.R;

/**
 * @author Lznby
 * @time 2018/9/21 13:01
 * Class Note:
 * 组合(联合)动画(通过Xml实现)
 * 1.讲述使用xml的形式实现ValueAnimator、ObjectAnimator和AnimatorSet
 */
public class AnimatorSetByXml {

    /**
     * 使用xml形式实现ValueAnimator
     *
     * @param context
     * @param view
     */
    public static void doValueAnimatorByXml(Context context, View view) {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(context, R.animator.xml_animator);
        valueAnimator.addUpdateListener(animation -> {
            int offset = (int) animation.getAnimatedValue();
            view.layout(offset, offset, view.getWidth() + offset, view.getHeight() + offset);
        });
        valueAnimator.start();
    }

    /**
     * 使用xml形式实现ObjectAnimator
     *
     * @param context
     * @param view
     */
    public static void doObjectAnimatorByXml(Context context, View view) {
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(context,R.animator.xml_object_animator);
        animator.setTarget(view);
        animator.start();
    }

    /**
     * 使用xml形式实现ObjectAnimator中使用android:valueFrom、android:valueTo的color属性用法
     * @param context
     * @param view
     */
    public static void doObjectAnimatorColorByXml(Context context, View view) {
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(context,R.animator.xml_object_animtor_color);
        animator.setTarget(view);
        animator.start();
    }

    /**
     * 使用xml形式实现AnimatorSet动画
     * @param context
     * @param view
     */
    public static void doAnimatorSetByXml(Context context, View view) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,R.animator.xml_set);
        set.setTarget(view);
        set.start();
    }

    //TODO 开篇示例——AnimatorSet应用 没写不想写了要看自己看下面的网址
    //https://blog.csdn.net/harvic880925/article/details/50763286
}
