package com.lznby.bigdemo.animation.usejava;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.lznby.bigdemo.animation.AnimatorTextView;

/**
 * @author Lznby
 * @time 2018/9/17 15:37
 * Class Note:
 * 1.ValueAnimator和ObjectAnimator的ofPropertyValuesHolder
 * 2.ValueAnimator和ObjectAnimator的ofPropertyValuesHolder使用类似。
 * 3.本类中只讲ObjectAnimator中的ofPropertyValuesHolder的使用,ValueAnimator中该方法使用较少。
 */
public class ValueAndObjectProperty {

    /**
     * PropertyValuesHolder的四个构造方法:
     * public static PropertyValuesHolder ofFloat(String propertyName, float... values)
     * public static PropertyValuesHolder ofInt(String propertyName, int... values)
     * public static PropertyValuesHolder ofObject(String propertyName, TypeEvaluator evaluator,Object... values)
     * public static PropertyValuesHolder ofKeyframe(String propertyName, Keyframe... values)
     */

    /**
     * ValueObject中PropertyValuesHolder的使用之ofInt与ofFloat的使用
     * <p>
     * 1.propertyName:需要做动画效果的属性值, value:动画属性值,可以为任意非零数量
     * public static PropertyValuesHolder ofInt(String propertyName, int... values)
     *
     * @param view
     */
    public static void doPropertyValuesAnimator(View view) {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
        PropertyValuesHolder colorHolder = PropertyValuesHolder.ofInt("BackgroundColor", 0xffffffff, 0xffff00ff, 0xffffff00, 0xffffffff);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, rotationHolder, colorHolder);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }


    /**
     * PropertyValuesHolder之ofObject的使用
     * public static PropertyValuesHolder ofObject(String propertyName, TypeEvaluator evaluator,Object... values)
     */
    public static void doOfObjectPropertyHolder(AnimatorTextView textView) {

        PropertyValuesHolder charHolder = PropertyValuesHolder.ofObject("CharText", new SeniorValueAnimator.CharEvaluator(), new Character('A'), new Character('Z'));
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(textView, charHolder);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }


    /**
     * KeyFrame:关键帧(动画),可以理解为和Flash中相似的原理
     * <p>
     * 基本使用步骤：
     * 第一步：生成Keyframe对象；
     * 第二步：利用PropertyValuesHolder.ofKeyframe()生成PropertyValuesHolder对象
     * 第三步：ObjectAnimator.ofPropertyValuesHolder()生成对应的Animator
     *
     * @param view
     */
    public static void doOfObjectKeyFrame(View view) {
        //fraction:表示动画进度(0.1表示动画进行到了10%)
        //value:表示动画对应的值
        Keyframe kf0 = Keyframe.ofFloat(0f, 0);
        Keyframe kf1 = Keyframe.ofFloat(0.1f, 20);
        Keyframe kf2 = Keyframe.ofFloat(1, 0);

        //propertyName:动画所需操作的属性名
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        Animator animator = ObjectAnimator.ofPropertyValuesHolder(view, frameHolder);
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * Keyframe：
     * 1.Keyframe.ofFloat
     * 2.Keyframe.ofInt
     * 3.Keyframe.ofObject
     *
     *
     * 设置fraction参数，即Keyframe所对应的进度
     * 1.setFraction(float fraction)
     * 设置当前Keyframe所对应的值
     * 2.setValue(Object value)
     * *设置Keyframe动作期间所对应的插值器*
     * 3.setInterpolator(TimeInterpolator interpolator)
     *
     * setInterpolator说明：
     * 1.setInterpolator设置的是连续的两个Keyframe之间的Interpolator
     * 2.如在第二个Keyframe中添加,则表示在第一个Keyframe和第二个Keyframe之间使用了Interpolator
     *
     * Keyframe的fraction与value必须是要被初始化过的,初始化方式可以任意！
     */

    /**
     * Keyframe 之 无interpolator(插值器)的使用
     * <p>
     * 当Keyframe 没有选择 interpolator 时, 会默认使用线性插值器(LinearInterpolator)
     */
    public static void doNoInterpolatorKeyframeAnimation(View view) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe kf2 = Keyframe.ofFloat(1);
        //使用.setValue设置Keyframe的value值
        kf2.setValue(0f);

        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(view, frameHolder);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * Keyframe 之 interpolator(插值器)的使用
     */
    public static void doInterpolatorKeyframeAnimation(View view) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 100f);
        Keyframe kf2 = Keyframe.ofFloat(1);
        //使用.setValue设置Keyframe的value值
        kf2.setValue(0f);

        //设置Keyframe的Interpolator为BounceInterpolator
        kf2.setInterpolator(new BounceInterpolator());

        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);

        Animator animator = ObjectAnimator.ofPropertyValuesHolder(view, frameHolder);
        animator.setDuration(3000);
        animator.start();
    }


}
