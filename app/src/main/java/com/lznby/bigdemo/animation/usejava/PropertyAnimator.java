package com.lznby.bigdemo.animation.usejava;

/**
 * View Animation说明
 *
 * 组成：
 * View Animation 包含 Tween Animation （补间动画） 以及 Frame Animation（逐帧动画）
 *
 * 作用范围：
 * 适用于由View衍生出来的视图。
 *
 * Tween Animation 包含：
 * alpha、translate、rotate、scale
 *
 * 逐帧动画主要是用来实现动画的；
 * 补间动画才能实现控件的渐入渐出、移动、旋转和缩放的；
 *
 */

/**
 * Property Animator（属性动画） 与 View Animation 的区别
 *
 * 1、Property Animator能实现补间动画无法实现的功能（如改变控件的颜色）。
 * 2、View Animation仅能对指定的控件做动画，而Property Animator是通过改变控件某一属性值来做动画的。
 * 3、补间动画虽能对控件做动画，但并没有改变控件内部的属性值。而Property Animator则是恰恰相反，Property Animator是通过改变控件内部的属性值来达到动画效果的
 */


import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;

import com.lznby.bigdemo.utils.LogUtil;

/**
 * @author Lznby
 * @time 2018/7/16 9:02
 * Class Note: Property Animator（属性动画） —ValueAnimator高级进阶（一）
 * Study Note:Property Animator（属性动画）组成：ValueAnimator、ObjectAnimator
 *
 * Property Animator 功能：
 * 1、ValueAnimator只负责对指定的数字区间进行动画运算
 * 2、我们需要对运算过程进行监听，然后自己对控件做动画操作
 */
public class PropertyAnimator {

    /**
     * View Animation 不会改变 View 属性值
     * @param view
     */
    public static void startViewAnimation(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 180, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 180);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }


    /**
     * Value Animator 的初步使用
     * @param view
     */
    public static void startValueAnimator(final View view) {
        //1.创建ValueAnimator实例
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
        valueAnimator.setDuration(1000);

        //2.添加监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                //.layout()函数中上下左右的点的坐标是以屏幕坐标来建立的
                //.layout()函数中四个点代表view视图四个顶点在坐标轴中的位置
                view.layout(curValue,curValue,curValue+view.getWidth(),curValue+view.getHeight());
            }
        });

        valueAnimator.start();
    }

    /**
     * Value Animator 常用方法
     *
     * 1、ofInt与ofFloat
     * public static ValueAnimator ofInt(int... values)
     * public static ValueAnimator ofFloat(float... values)
     *
     * 他们的参数类型都是可变参数长参数，所以我们可以传入任何数量的值；传进去的值列表，就表示动画时的变化范围；
     * 比如ofInt(2,90,45)就表示从数值2变化到数字90再变化到数字45；所以我们传进去的数字越多，动画变化就越复杂。
     * 从参数类型也可以看出ofInt与ofFloat的唯一区别就是传入的数字类型不一样，
     * ofInt需要传入Int类型的参数，而ofFloat则表示需要传入Float类型的参数。
     *
     * 2、ValueAnimator setDuration(long duration)
     * 设置动画时长，单位是毫秒
     *
     * 3、Object getAnimatedValue()
     * 获取valueAnimation在运动是，当前运动点的值
     *
     * 4、void start()
     * 开始动画
     *
     * 5、void setRepeatCount(int value)
     * 设置循环次数，设置为INFINITE表示无限循环
     *
     * 6、setRepeatMode(int value)
     * 设置循环模式
     * value值有：RESTART、REVERSE
     *
     * 7、void cancel()
     * 取消动画
     *
     */

    /**
     * Value Animator ofFloat 多个值的使用
     * @param view
     */
    public static void startValueAnimatorMoreValue(final View view) {
        //1.创建ValueAnimator实例
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,200f,100f,300f,100f);
        valueAnimator.setDuration(1000);

        //2.添加监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //强制转换原因：.getAnimatedValue()返回类型是Object
                Float curValueFloat = (Float) animation.getAnimatedValue();
                int curValue = curValueFloat.intValue();
                //.layout()函数中上下左右的点的坐标是以屏幕坐标来建立的
                //.layout()函数中四个点代表view视图四个顶点在坐标轴中的位置
                view.layout(curValue,curValue,curValue+view.getWidth(),curValue+view.getHeight());
            }
        });

        valueAnimator.start();
    }

    /**
     * ValueAnimator 常用方法
     * @param view
     * @return
     */
    public static ValueAnimator startValueAnimatorCommentMethod(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });
        //RESTART:重新开始;REVERSE:相反
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //INFINITE:循环执行
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置持续时间
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        return valueAnimator;

    }

    /**
     * ValueAnimator 两种监听方法及设置方法
     * AnimatorUpdateListener：  添加监听的方法：addUpdateListener()    监听动画实时变化
     * AnimatorListener：        添加监听的方法：addListener()          监听Animation的四个状态，start、end、cancel、repeat
     * @param view
     * @return
     */
    public static ValueAnimator startValueAnimatorListenerMethod(final View view) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);

        //动画实时监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });

        //动画状态监听
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                LogUtil.e("animation start!");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtil.e("animation end!");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogUtil.e("animation cancel!");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogUtil.e("animation repeat!");
            }
        });

        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1000);
        valueAnimator.start();

        return valueAnimator;
    }

    /**
     * 取消监听的方法
     *
     * 1、移除AnimatorUpdateListener
     * void removeUpdateListener(AnimatorUpdateListener listener);
     * void removeAllUpdateListeners();
     *
     * 2、移除AnimatorListener
     * void removeListener(AnimationListener listener);
     * void removeAllListeners();
     *
     * 说明：
     * removeListener(AnimatorListener listener)用于在animator中移除指定的监听器，
     * 而removeAllListeners()用于移除animator中所有的AnimatorListener监听器；
     */

    /**
     * 延长开始时间，单位毫秒
     * public void setStartDelay(long starDelay)
     * @param view
     */
    public static void startValueAnimatorSetDelay(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });

        //设置动画开始时间1000毫秒以后执行
        valueAnimator.setStartDelay(1000);
        valueAnimator.setDuration(1000);
        valueAnimator.start();
    }


    /**
     * clone 克隆方法，克隆ValueAnimator对象
     * .clone()
     * @param view
     * @return
     */
    public static ValueAnimator startValueAnimatorClone(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1000);
        return valueAnimator;
    }

    /**
     * 设置插值器(又称加速器)
     * 插值器种类参见res/anim/interpolator.xml一共九种
     * @param view
     */
    public static void startValueAnimatorSetInterpolator(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });

        valueAnimator.setDuration(1000);
        //设置插值器（加速器）
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }

    /**
     * 自定义插值器（加速器）的使用——Interpolator
     * @param view
     */
    public static void startValueAnimatorCustomInterpolator(final View view){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,600);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new CustomInterpolator());
        valueAnimator.start();
    }

    /**
     * 自定义Interpolator插值器（加速器）类
     * input为0~1之间的浮点数
     * getInterpolator中返回的值为动画的进度
     */
    public static class CustomInterpolator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            return 1-input;
        }
    }

    /**
     * AnimatorUpdateListener中得到当前动画所对应数值的整个过程。
     *
     * ofInt(0,400)——>Interpolator(加速器)——>Evaluator(计算器)——>AnimatorUpdateListener(监听器返回)
     *
     * (1)、ofInt(0,400)表示指定动画的数字区间，是从0运动到400；
     * (2)、加速器：上面我们讲了，在动画开始后，通过加速器会返回当前动画进度所对应的数字进度，但这个数字进度是百分制的，以小数表示，如0.2
     * (3)、Evaluator:我们知道我们通过监听器拿到的是当前动画所对应的具体数值，而不是百分制的进度。
     * 那么就必须有一个地方会根据当前的数字进度，将其转化为对应的数值，这个地方就是Evaluator；
     * Evaluator就是将从加速器返回的数字进度转成对应的数字值。所以上部分中，我们讲到的公式：
     * 当前的值 = 100 + （400 - 100）* 显示进度
     * 这个公式就是在Evaluator计算的；在拿到当前数字进度所对应的值以后，将其返回
     * (4)、监听器：我们通过在AnimatorUpdateListener监听器使用animation.getAnimatedValue()函数拿到Evaluator中返回的数字值。
     讲了这么多，Evaluator其实就是一个转换器，他能把小数进度转换成对应的数值位置
     */


    /**
     * 自定义Evaluator(计算器)
     * @param view
     */
    public static void startValueAnimatorCustomEvaluator(final View view) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.layout(view.getLeft(),curValue,view.getRight(),curValue+view.getHeight());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setEvaluator(new CustomEvaluator());
        valueAnimator.start();

    }

    /**
     * 自定义Evaluator计算器类
     */
    public static class CustomEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int startInt = startValue;
            return (int) (200+startInt+fraction*(endValue-startInt));
        }
    }


    /**
     * ARGBEvaluator 自定义颜色计算器类
     */
    public static class CustomArgEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24);
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;
            return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                    (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                    (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                    (int) ((startB + (int) (fraction * (endB - startB))));
        }
    }

    /**
     * ValueAnimator ArgbEvaluator(argb计算器)动画设置背景颜色渐变
     * @param view
     */
    public static void startValueAnimatorCustomArgbEvaluator(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0xffffff00,0xff0000ff);
        valueAnimator.setDuration(3000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                view.setBackgroundColor(curValue);
            }
        });

        valueAnimator.setEvaluator(new CustomArgEvaluator());

        valueAnimator.start();
    }

}
