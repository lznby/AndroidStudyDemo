package com.lznby.bigdemo.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.animation.usejava.ObjectAnimatorDemo;
import com.lznby.bigdemo.animation.usejava.PropertyAnimator;
import com.lznby.bigdemo.animation.usejava.SeniorValueAnimator;
import com.lznby.bigdemo.animation.usejava.TweenAnimation;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note: 动画学习
 */
@Route(path = ARouterTools.AnimationActivity)
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    //Value Animator

    ValueAnimator valueAnimator = new ValueAnimator();

    //1.使用黄油刀绑定视图

    @BindView(R.id.tv_animation)
    TextView tvAnimation;
    @BindView(R.id.bt_animation_1)
    Button btAnimation1;
    @BindView(R.id.bt_animation_2)
    Button btAnimation2;
    @BindView(R.id.bt_animation_3)
    Button btAnimation3;
    @BindView(R.id.bt_animation_4)
    Button btAnimation4;
    @BindView(R.id.bt_animation_5)
    Button btAnimation5;
    @BindView(R.id.bt_animation_6)
    Button btAnimation6;
    @BindView(R.id.bt_animation_7)
    Button btAnimation7;
    @BindView(R.id.bt_animation_8)
    Button btAnimation8;
    @BindView(R.id.bt_animation_9)
    Button btAnimation9;
    @BindView(R.id.bt_animation_10)
    Button btAnimation10;
    @BindView(R.id.bt_animation_11)
    Button btAnimation11;
    @BindView(R.id.bt_animation_12)
    Button btAnimation12;
    @BindView(R.id.bt_animation_13)
    Button btAnimation13;
    @BindView(R.id.bt_animation_14)
    Button btAnimation14;
    @BindView(R.id.bt_animation_15)
    Button btAnimation15;
    @BindView(R.id.bt_animation_16)
    Button btAnimation16;
    @BindView(R.id.bt_animation_17)
    Button btAnimation17;
    @BindView(R.id.bt_animation_18)
    Button btAnimation18;
    @BindView(R.id.bt_animation_19)
    Button btAnimation19;
    @BindView(R.id.bt_animation_20)
    Button btAnimation20;
    @BindView(R.id.bt_animation_21)
    Button btAnimation21;
    @BindView(R.id.bt_animation_22)
    Button btAnimation22;
    @BindView(R.id.bt_animation_23)
    Button btAnimation23;
    @BindView(R.id.bt_animation_24)
    Button btAnimation24;
    @BindView(R.id.bt_animation_25)
    Button btAnimation25;
    @BindView(R.id.bt_animation_26)
    Button btAnimation26;
    @BindView(R.id.bt_animation_27)
    Button btAnimation27;
    @BindView(R.id.bt_animation_28)
    Button btAnimation28;
    @BindView(R.id.bt_animation_29)
    Button btAnimation29;
    @BindView(R.id.bt_animation_30)
    Button btAnimation30;
    @BindView(R.id.bt_animation_31)
    Button btAnimation31;
    @BindView(R.id.bt_animation_32)
    Button btAnimation32;
    @BindView(R.id.bt_animation_33)
    Button btAnimation33;
    @BindView(R.id.bt_animation_34)
    Button btAnimation34;
    @BindView(R.id.bt_animation_35)
    Button btAnimation35;
    @BindView(R.id.my_point_view)
    SeniorValueAnimator.MyPointView myPointView;
    @BindView(R.id.object_my_point_view)
    ObjectAnimatorDemo.MyPointView1 objectMyPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        //2.将黄油刀框架绑定到这个Activity上
        ButterKnife.bind(this);

        //设置监听
        initOnClickListener();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_animation:
                //为animation设置点击事件
                Toast.makeText(this, "我被点击了！", Toast.LENGTH_SHORT).show();
                //停止ValueAnimator
                valueAnimator.cancel();
                break;
            case R.id.bt_animation_1:
                //scale功能：渐变尺寸伸缩动画效果
                startAnimation(tvAnimation, R.anim.scale);
                break;
            case R.id.bt_animation_2:
                //alpha功能：渐变透明动画效果
                startAnimation(tvAnimation, R.anim.alpha);
                break;
            case R.id.bt_animation_3:
                //rotate功能：画面转移旋转动画
                startAnimation(tvAnimation, R.anim.rotate);
                break;
            case R.id.bt_animation_4:
                //translate功能：渐变尺寸伸缩动画效果
                startAnimation(tvAnimation, R.anim.translate);
                break;
            case R.id.bt_animation_5:
                //set功能：定义动作合集
                startAnimation(tvAnimation, R.anim.set);
                break;
            case R.id.bt_animation_6:
                //Interpolator属性功能：设置动画效果
                startAnimation(tvAnimation, R.anim.interpolator);
                break;
            case R.id.bt_animation_7:
                //使用代码实现scale渐变尺寸伸缩的效果
                TweenAnimation.startScaleAnimation(tvAnimation);
                break;
            case R.id.bt_animation_8:
                //使用代码实现alpha渐变透明度的效果
                TweenAnimation.startAlphaAnimation(tvAnimation);
                break;
            case R.id.bt_animation_9:
                //使用代码实现rotate旋转动画效果
                TweenAnimation.startRotateAnimation(tvAnimation);
                break;
            case R.id.bt_animation_10:
                //使用代码实现translate画面移动效果
                TweenAnimation.startTranslateAnimation(tvAnimation);
                break;
            case R.id.bt_animation_11:
                //使用代码实现set动画集合
                TweenAnimation.startAnimationSet(tvAnimation);
                break;
            case R.id.bt_animation_12:
                //使用代码实现设置Interpolate插值器
                TweenAnimation.startInterpolate(tvAnimation);
                break;
            case R.id.bt_animation_13:
                //View Animation 不能改变View属性值
                PropertyAnimator.startViewAnimation(tvAnimation);
                break;
            case R.id.bt_animation_14:
                //Property Animator 能够改变控件属性值
                PropertyAnimator.startValueAnimator(tvAnimation);
                break;
            case R.id.bt_animation_15:
                //Value Animator ofFloat多个值
                PropertyAnimator.startValueAnimatorMoreValue(tvAnimation);
                break;
            case R.id.bt_animation_16:
                //Value Animator 常用方法
                valueAnimator = PropertyAnimator.startValueAnimatorCommentMethod(tvAnimation);
                break;
            case R.id.bt_animation_17:
                //Value Animator 两种监听方法
                valueAnimator = PropertyAnimator.startValueAnimatorListenerMethod(tvAnimation);
                break;
            case R.id.bt_animation_18:
                //Value Animator 取消监听的方法
                valueAnimator.removeAllUpdateListeners();
                valueAnimator.removeAllListeners();
                break;
            case R.id.bt_animation_19:
                //Value Animator 延迟开始执行
                PropertyAnimator.startValueAnimatorSetDelay(tvAnimation);
                break;
            case R.id.bt_animation_20:
                //Value Animator clone 克隆 Value Animation 动画对象
                valueAnimator = PropertyAnimator.startValueAnimatorClone(tvAnimation);
                //clone出来的对象独立于原来的对象，但是动画效果等都相同
                ValueAnimator valueAnimatorClone = valueAnimator.clone();
                valueAnimatorClone.start();
                break;
            case R.id.bt_animation_21:
                //Value Animator Interpolator插值器（加速器）
                PropertyAnimator.startValueAnimatorSetInterpolator(tvAnimation);
                break;
            case R.id.bt_animation_22:
                //自定义插值器（加速器）
                PropertyAnimator.startValueAnimatorCustomInterpolator(tvAnimation);
                break;
            case R.id.bt_animation_23:
                //自定义Evaluator（计算器）
                PropertyAnimator.startValueAnimatorCustomEvaluator(tvAnimation);
                break;
            case R.id.bt_animation_24:
                //自定义ArgbEvaluator（颜色计算器）——实现控件颜色渐变
                PropertyAnimator.startValueAnimatorCustomArgbEvaluator(tvAnimation);
                break;
            case R.id.bt_animation_25:
                //ValueAnimator ofObject 实现TextView字符变换
                SeniorValueAnimator.startValueAnimatorCharEvaluator(tvAnimation);
                break;
            case R.id.bt_animation_26:
                //ValueAnimator ofObject 实现自定义参数类型(圆)半径的变化
                SeniorValueAnimator.startValueAnimatorDrawPoint(myPointView);
                break;
            case R.id.bt_animation_27:
                //ObjectAnimator 改变属性值 alpha 透明度
                ObjectAnimatorDemo.startObjectAnimatorAlpha(tvAnimation);
                break;
            case R.id.bt_animation_28:
                //ObjectAnimator 改变属性值 rotation 旋转
                ObjectAnimatorDemo.startObjectAnimatorRotation(tvAnimation);
                break;
            case R.id.bt_animation_29:
                //ObjectAnimator setRotationX 上下旋转(立体)
                ObjectAnimatorDemo.startObjectAnimatorRotationX(tvAnimation);
                break;
            case R.id.bt_animation_30:
                //ObjectAnimator setRotationY 左右翻转(立体)
                ObjectAnimatorDemo.startObjectAnimatorRotationY(tvAnimation);
                break;
            case R.id.bt_animation_31:
                //ObjectAnimator setTranslationX (X轴方向平移)
                ObjectAnimatorDemo.startObjectAnimatorTranslationX(tvAnimation);
                break;
            case R.id.bt_animation_32:
                //ObjectAnimator setTranslationY (Y轴方向平移)
                ObjectAnimatorDemo.startObjectAnimatorTranslationY(tvAnimation);
                break;
            case R.id.bt_animation_33:
                //ObjectAnimator setScaleX 水平拉伸(缩放)
                ObjectAnimatorDemo.startObjectAnimatorScaleX(tvAnimation);
                break;
            case R.id.bt_animation_34:
                //ObjectAnimator setScaleY 垂直拉伸(缩放)
                ObjectAnimatorDemo.startObjectAnimatorScaleY(tvAnimation);
                break;
            case R.id.bt_animation_35:
                //ObjectAnimator 使用自定义ObjectAnimator set方法
                ObjectAnimatorDemo.doPointViewAnimator(objectMyPointView);
                break;
            default:
                break;
        }
    }

    /**
     * 设置监听
     */
    private void initOnClickListener() {
        tvAnimation.setOnClickListener(this);
        btAnimation1.setOnClickListener(this);
        btAnimation2.setOnClickListener(this);
        btAnimation3.setOnClickListener(this);
        btAnimation4.setOnClickListener(this);
        btAnimation5.setOnClickListener(this);
        btAnimation6.setOnClickListener(this);
        btAnimation7.setOnClickListener(this);
        btAnimation8.setOnClickListener(this);
        btAnimation9.setOnClickListener(this);
        btAnimation10.setOnClickListener(this);
        btAnimation11.setOnClickListener(this);
        btAnimation12.setOnClickListener(this);
        btAnimation13.setOnClickListener(this);
        btAnimation14.setOnClickListener(this);
        btAnimation15.setOnClickListener(this);
        btAnimation16.setOnClickListener(this);
        btAnimation17.setOnClickListener(this);
        btAnimation18.setOnClickListener(this);
        btAnimation19.setOnClickListener(this);
        btAnimation20.setOnClickListener(this);
        btAnimation21.setOnClickListener(this);
        btAnimation22.setOnClickListener(this);
        btAnimation23.setOnClickListener(this);
        btAnimation24.setOnClickListener(this);
        btAnimation25.setOnClickListener(this);
        btAnimation26.setOnClickListener(this);
        btAnimation27.setOnClickListener(this);
        btAnimation28.setOnClickListener(this);
        btAnimation29.setOnClickListener(this);
        btAnimation30.setOnClickListener(this);
        btAnimation31.setOnClickListener(this);
        btAnimation32.setOnClickListener(this);
        btAnimation33.setOnClickListener(this);
        btAnimation34.setOnClickListener(this);
        btAnimation35.setOnClickListener(this);
    }

    /**
     * 使用xml方式设置动画的方法
     *
     * @param view 设置动画的视图
     * @param id   设置动画的xml
     */
    private void startAnimation(View view, @AnimRes int id) {
        Animation animation = AnimationUtils.loadAnimation(this, id);
        view.startAnimation(animation);
    }
}
