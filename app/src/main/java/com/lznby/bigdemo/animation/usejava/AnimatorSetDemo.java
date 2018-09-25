package com.lznby.bigdemo.animation.usejava;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.lznby.bigdemo.utils.LogUtil;

/**
 * @author Lznby
 * @time 2018/9/18 14:48
 * Class Note:
 * 组合(联合)动画(使用Java代码形式实现)
 * 1.ValueAnimator和ObjectAnimator都只能单单实现一个动画
 * 2.AnimatorSet用于实现组合动画。
 * 3.AnimatorSet针对ValueAnimator和ObjectAnimator都是适用的，但一般而言，我们不会用到ValueAnimator的
 * 组合(联合)动画，所以我们这篇仅讲解ObjectAnimator下的组合动画实现。
 * <p>
 * AnimatorSet 提供的两个方法:
 * 1.playSequentially：所有动画顺序播放
 * 2.playTogether：所有动画同时播放
 */
public class AnimatorSetDemo {

    /**
     * AnimatorSet 之 playSequentially(顺序播放动画)
     * <p>
     * public void playSequentially(Animator... items);
     * <p>
     * public void playSequentially(List<Animator> items)
     *
     * @param views
     */
    public static void doAnimatorSetPlaySequentially(View... views) {

        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 300, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 300, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bgAnimator, translateY, translateX);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * AnimatorSet 之 playTogether(同时播放)
     * <p>
     * public void playTogether(Animator... items);
     * <p>
     * public void playTogether(Collection<Animator> items)
     *
     * @param views
     */
    public static void doAnimatorSetPlayTogether(View... views) {
        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 300, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 300, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(bgAnimator, translateY, translateX);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * AnimatorSet playTogether中的循环与延时
     *
     * @param views
     */
    public static void doAnimatorSetCyclePlay(View... views) {
        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 300, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 300, 0);

        //设置动画为无限循环
        bgAnimator.setRepeatCount(ValueAnimator.INFINITE);
        translateY.setRepeatCount(ValueAnimator.INFINITE);
        translateX.setRepeatCount(ValueAnimator.INFINITE);

        //设置动画延迟时间(1s)
        translateY.setStartDelay(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(bgAnimator, translateY, translateX);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    /**
     * AnimatorSet playSequentially(顺序播放)中的循环与延时
     * 1.当循环的动画开始执行时,在此之后的动画都不会执行
     * 2.延时时间未到时,后续动画也不会执行,此处就不写Demo了。
     */


    /**
     * 自由设置动画顺序——AnimatorSet.Builder
     *
     * 使用的场合：
     * 比如我们有三个动画A,B,C我们想先播放C然后同时播放A和B。
     *
     * 使用说明：
     * 1.调用AnimatorSet中的play方法是获取AnimatorSet.Builder对象的唯一途径
     * 2.play(Animator anim)表示当前在播放哪个动画，另外的with(Animator anim)、before(Animator anim)、
     * after(Animator anim)都是以play中的当前所播放的动画为基准的。
     *
     * AnimatorSet.Builder常用方法说明：
     * 1.public Builder play(Animator anim)
     * 调用AnimatorSet中的play方法是获取AnimatorSet.Builder对象的唯一途径;
     * 表示要播放哪个动画;
     *
     * 2.public Builder with(Animator anim)
     * 和前面动画(play中动画)一起执行
     *
     * 3.public Builder before(Animator anim)
     * 执行先执行这个动画再执行前面动画(play中动画)
     *
     * 4.public Builder after(long delay)
     * 延迟n毫秒之后执行动画
     */


    /**
     * AnimatorSet.Builder 自定义顺序动画播放
     *
     * @param views
     */
    public static void doAnimatorSetBuilder(View... views) {
        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 400, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 400, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        //先播放bgAnimator,在播放完bgAnimator后再播放translateY,与此同时播放translateX
        AnimatorSet.Builder builder = animatorSet.play(translateY).with(translateX).after(bgAnimator);
        animatorSet.setDuration(2000);
        animatorSet.start();

    }

    /**
     * AnimatorSet 监听器以及取消动画
     * <p>
     * 添加监听：
     * animatorSet.addListener()
     * <p>
     * 取消监听：
     * animatorSet.cancel()
     *
     * @param views
     * @return
     */
    public static AnimatorSet doAnimatorSetListener(View... views) {

        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 400, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 400, 0);

        AnimatorSet animatorSet = new AnimatorSet();

        //为animatorSet设置监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //当AnimatorSet开始时调用
                LogUtil.e("animator start!");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //当AnimatorSet结束时调用
                LogUtil.e("animator end!");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //当AnimatorSet被取消时调用
                LogUtil.e("animator cancel!");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //当AnimatorSet重复时调用,由于AnimatorSet没有设置repeat的函数,所以这个方法永远不会被调用
                LogUtil.e("animator repeat");
            }
        });

        //设置translateX为无限循环
        translateX.setRepeatCount(ValueAnimator.INFINITE);

        //先播放bgAnimator,在播放完bgAnimator后再播放translateY,与此同时播放translateX
        animatorSet.play(translateY).with(translateX).after(bgAnimator);
        animatorSet.setDuration(2000);
        animatorSet.start();

        return animatorSet;
    }

    /**
     * 通用函数逐个设置与AnimatorSet设置的区别
     *
     * AnimatorSet中部分方法说明：
     * 1.public AnimatorSet setDuration(long duration);
     * 设置单次动画时长
     *
     * 2.public void setInterceptor(TimeInterpolator interpolator)
     * 设置加速器(插值器)Interceptor
     *
     * 3.public void setTarget(Object target)
     * 设置ObjectAnimator动画目标控件
     *
     * 注意：
     * 在ObjectAnimator中也都有这几个函数。那在AnimatorSet中设置与在单个ObjectAnimator中设置有什么区别呢？
     *
     * 区别就是：在AnimatorSet中设置以后，会覆盖单个ObjectAnimator中的设置；即如果AnimatorSet中没有设置，
     * 那么就以ObjectAnimator中的设置为准。如果AnimatorSet中设置以后，ObjectAnimator中的设置就会无效。
     */

    /**
     * AnimatorSet与ObjectAnimator中动画设置方法的比较（所有和单个的比较）:setInterceptor和setDuration
     *
     * @param views
     */
    public static void doAnimatorSetAllAndSingleSetting(View... views) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 100, 0);
        //为该动画对象单独设置动画执行时间,但是由于我们又在AnimatorSet中进行了设置,所以下面的代码是无效的。
        translateY.setDuration(500000);
        //单独设置加速器（插值器）且在AnimatorSet中未进行设置,因此以下代码是有效的。
        translateY.setInterpolator(new BounceInterpolator());

        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 100, 0);
        //单独设置加速器（插值器）且在AnimatorSet中未进行设置,因此以下代码是有效的。
        translateX.setInterpolator(new AccelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        //播放translateY的同时播放translateX动画
        animatorSet.play(translateY).with(translateX);
        //设置全部动画的执行时间,使用ObjectAnimator进行单独设置的执行时间将失效。
        animatorSet.setDuration(2000);
        animatorSet.start();

    }

    /**
     * AnimatorSet与ObjectAnimator中设置动画目标setTarget方法的比较。
     *
     * @param views
     */
    public static void doAnimatorSetSetTarget(View... views) {
        //设置动画目标为views[0],但是由于AnimatorSet使用setTarget方法设置动画目标为views[1],所以此处的代码失效了。
        ObjectAnimator bgAnimator = ObjectAnimator.ofInt(views[0], "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        //设置动画目标为views[1]
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[1], "translationY", 0, 100, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(bgAnimator, translateY);
        animatorSet.setDuration(2000);
        //设置animatorSet中所有动画目标都为views[1]
        animatorSet.setTarget(views[1]);
        animatorSet.start();
    }

    /**
     * AnimatorSet的延时是仅针对性的延长AnimatorSet激活时间的，对单个动画的延时设置没有影响。
     *
     * AnimatorSet真正激活延时 = AnimatorSet.startDelay+第一个动画.startDelay
     *
     * @param views
     */
    public static void doAnimatorSetSetStartDelay(View... views) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(views[0], "translationY", 0, 100, 0);
        ObjectAnimator translateX = ObjectAnimator.ofFloat(views[1], "translationX", 0, 100, 0);
        //设置translateX的延迟为2000ms
        translateX.setStartDelay(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translateY).with(translateX);
        //设置animatorSet的启动延时为2000ms
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(2000);
        animatorSet.start();

    }


}
