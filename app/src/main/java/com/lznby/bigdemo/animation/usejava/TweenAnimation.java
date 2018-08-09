package com.lznby.bigdemo.animation.usejava;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * View Animation   （视图动画） 之 Tween Animation （补间动画）
 *
 * View Animation   只能实现动画效果而不能改变View的值，如Button平移后Click作用范围不变。
 *
 * @author Lznby
 * @time 2018/7/15 20:48
 * Class Note: 代码实现alpha、scale、translate、rotate、set动画效果
 * Study Note:
 * 1、scale——ScaleAnimation              渐变尺寸伸缩动画效果
 * 2、alpha——AlphaAnimation              渐变透明动画效果
 * 3、rotate——RotateAnimation            画面转移旋转动画效果
 * 4、translate——TranslateAnimation      画面转移移动动画效果
 * 5、set——AnimationSet                  动画集合
 */

public class TweenAnimation {
    /**
     * Animation公共类
     *
     * Animation类是所有动画（scale、alpha、translate、rotate）的基类，它所有标签及对应函数为：
     *
     * android:duration       setDuration(long)	            动画持续时间，以毫秒为单位
     * android:fillAfter      setFillAfter(boolean)	        如果设置为true，控件动画结束时，将保持动画最后时的状态
     * android:fillBefore     setFillBefore(boolean)	    如果设置为true,控件动画结束时，还原到开始动画前的状态
     * android:fillEnabled    setFillEnabled(boolean)	    与android:fillBefore 效果相同，都是在动画结束时，将控件还原到初始化状态
     * android:repeatCount    setRepeatCount(int)	        重复次数
     * android:repeatMode     setRepeatMode(int)	        重复类型，有reverse和restart两个值，取值为RESTART或 REVERSE，必须与repeatCount一起使用才能看到效果。因为这里的意义是重复的类型，即回放时的动作。
     * android:interpolator   setInterpolator(Interpolator) 设定插值器，其实就是指定的动作效果，比如弹跳效果等
     */


    /**
     * ScaleAnimation类
     *
     * 渐变尺寸伸缩动画效果、ScaleAnimation自有属性
     *
     * android:fromXScale    起始的X方向上相对自身的缩放比例，浮点值，比如1.0代表自身无变化，0.5代表起始时缩小一倍，2.0代表放大一倍；
     * android:toXScale      结尾的X方向上相对自身的缩放比例，浮点值；
     * android:fromYScale    起始的Y方向上相对自身的缩放比例，浮点值，
     * android:toYScale      结尾的Y方向上相对自身的缩放比例，浮点值；
     * android:pivotX        缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，当为数值时，表示在当前View的左上角，即原点处加上50px，做为起始缩放点；如果是50%，表示在当前控件的左上角加上自己宽度的50%做为起始点；如果是50%p，那么就是表示在当前的左上角加上父控件宽度的50%做为起始点x轴坐标。（具体意义，后面会举例演示）
     * android:pivotY        缩放起点Y轴坐标，取值及意义跟android:pivotX一样。
     *
     * ScaleAnimation有下面几个构造函数：
     *
     * ScaleAnimation(Context context, AttributeSet attrs)  从XML文件加载动画，基本用不到
     * ScaleAnimation(float fromX, float toX, float fromY, float toY)
     * ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)
     * ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
     *
     * 在标签属性android:pivotX中有三种取值，数，百分数，百分数p；
     * 体现在构造函数中，就是最后一个构造函数的pivotXType,
     * 它的取值有三个，Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；
     */


    /**
     * 代码设置ScaleAnimation动画——渐变尺寸伸缩
     * @param view
     */
    public static void startScaleAnimation(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f, Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(700);
        view.startAnimation(scaleAnimation);
    }

    /**
     * AlphaAnimation类
     *
     * 渐变透明动画效果、AlphaAnimation自有属性
     *
     * android:fromAlpha   动画开始的透明度，从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     * android:toAlpha     动画结束时的透明度，也是从0.0 --1.0 ，0.0表示全透明，1.0表示完全不透明
     *
     * AlphaAnimation有以下几个构造函数
     *
     * AlphaAnimation(Context context, AttributeSet attrs)  同样，从本地XML加载动画，基本不用
     * AlphaAnimation(float fromAlpha, float toAlpha)
     */

    /**
     * 代码设置AlphaAnimation动画——渐变透明度
     * @param view
     */
    public static void startAlphaAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }

    /**
     * RotateAnimation类
     *
     * 旋转动画效果、RotateAnimation自有属性
     *
     * android:fromDegrees   开始旋转的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:toDegrees     结束时旋转到的角度位置，正值代表顺时针方向度数，负值代码逆时针方向度数
     * android:pivotX        缩放起点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
     * android:pivotY        缩放起点Y轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p
     *
     * RotateAnimation类的构造方法
     *
     * RotateAnimation(Context context, AttributeSet attrs)　　从本地XML文档加载动画，同样，基本不用
     * RotateAnimation(float fromDegrees, float toDegrees)
     * RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
     * RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
     *
     * RotateAnimation跟ScaleAnimation差不多，关键问题同样是pivotXType和pivotYType的选择，
     * 同样有三个取值：Animation.ABSOLUTE、Animation.RELATIVE_TO_SELF和Animation.RELATIVE_TO_PARENT；
     */

    /**
     * 代码设置RotateAnimation动画——旋转效果
     * @param view
     */
    public static void startRotateAnimation(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        view.startAnimation(rotateAnimation);
    }

    /**
     * TranslateAnimation类
     *
     * 画面转移移动效果、TranslateAnimation自有属性
     *
     * android:fromXDelta   起始点X轴坐标，可以是数值、百分数、百分数p 三种样式，比如 50、50%、50%p，具体意义已在scale标签中讲述，这里就不再重讲
     * android:fromYDelta   起始点Y轴从标，可以是数值、百分数、百分数p 三种样式；
     * android:toXDelta     结束点X轴坐标
     * android:toYDelta     结束点Y轴坐标
     *
     * TranslateAnimation有以下构造方法
     *
     * TranslateAnimation(Context context, AttributeSet attrs)  同样，基本不用
     * TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
     * TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
     *
     * 由于fromXDelta、fromYDelta、toXDelta、toYDelta这三个属性都具有三种状态，
     * 所以在构造函数中，最理想的状态就是第三个构造函数，能够指定每个值的类型，
     * 第二个构造函数：TranslateAnimation (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)使用是绝对数值。
     * 只有最后一个构造函数可以指定百分数和相对父控件的百分数。
     */

    /**
     * 代码设置TranslateAnimation动画——画面移动效果
     * @param view
     */
    public static void startTranslateAnimation(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE,-80,Animation.ABSOLUTE,0,Animation.ABSOLUTE,-80);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }

    /**
     * AnimationSet类
     *
     * 对应属性动画中set，设置自定义动画集合、AnimationSet没有自有属性，完全继承至Animation
     *
     * AnimationSet有以下构造方法
     *
     * AnimationSet(Context context, AttributeSet attrs)  同样，基本不用
     * AnimationSet(boolean shareInterpolator)  shareInterpolator取值true或false，
     * 取true时，指在AnimationSet中定义一个插值器（interpolater），它下面的所有动画共同。
     * 如果设为false，则表示它下面的动画自己定义各自的插值器。
     *
     */

    /**
     * 代码设置AnimationSet动画集合
     * @param view
     */
    public static void startAnimationSet(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.1f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.4f,0.0f,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);

        animationSet.setDuration(1000);
        animationSet.setFillAfter(true);

        view.startAnimation(animationSet);
    }

    /**
     * Interpolator插值器——参见anim中interpolator.xml
     */

    /**
     * 代码设置Interpolator插值器
     * @param view
     */
    public static void startInterpolate(View view) {
        ScaleAnimation interpolateScaleAnim=new ScaleAnimation(0.0f,1.4f,0.0f,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        interpolateScaleAnim.setInterpolator(new BounceInterpolator());
        interpolateScaleAnim.setDuration(3000);
        view.startAnimation(interpolateScaleAnim);
    }

}
