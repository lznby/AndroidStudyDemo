package com.lznby.bigdemo.animation;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.animation.usejava.AnimatorSetByXml;
import com.lznby.bigdemo.animation.usejava.AnimatorSetDemo;
import com.lznby.bigdemo.animation.usejava.ObjectAnimatorDemo;
import com.lznby.bigdemo.animation.usejava.PropertyAnimator;
import com.lznby.bigdemo.animation.usejava.SeniorValueAnimator;
import com.lznby.bigdemo.animation.usejava.TweenAnimation;
import com.lznby.bigdemo.animation.usejava.ValueAndObjectProperty;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note: 动画学习
 */
@Route(path = ARouterTools.AnimationActivity)
public class AnimationActivity extends AppCompatActivity{

    /**
     * Value Animator
     */
    ValueAnimator valueAnimator = new ValueAnimator();

    /**
     * 用于取消animatorSet动画
     */
    AnimatorSet animatorSet;

    //1.使用黄油刀绑定视图

    @BindView(R.id.tv_animation)
    TextView tvAnimation;
    @BindView(R.id.animator_tv_property)
    AnimatorTextView mAnimatorTextView;
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

    @OnClick({R.id.bt_animation_1, R.id.bt_animation_2, R.id.bt_animation_3, R.id.bt_animation_4,
            R.id.bt_animation_5, R.id.bt_animation_6, R.id.bt_animation_7, R.id.bt_animation_8,
            R.id.bt_animation_9, R.id.bt_animation_10, R.id.bt_animation_11, R.id.bt_animation_12,
            R.id.bt_animation_13, R.id.bt_animation_14, R.id.bt_animation_15, R.id.bt_animation_16,
            R.id.bt_animation_17, R.id.bt_animation_18, R.id.bt_animation_19, R.id.bt_animation_20,
            R.id.bt_animation_21, R.id.bt_animation_22, R.id.bt_animation_23, R.id.bt_animation_24,
            R.id.bt_animation_25, R.id.bt_animation_26, R.id.bt_animation_27, R.id.bt_animation_28,
            R.id.bt_animation_29, R.id.bt_animation_30, R.id.bt_animation_31, R.id.bt_animation_32,
            R.id.bt_animation_33, R.id.bt_animation_34, R.id.bt_animation_35, R.id.bt_animation_36,
            R.id.bt_animation_37, R.id.bt_animation_38, R.id.bt_animation_39, R.id.bt_animation_40,
            R.id.bt_animation_41, R.id.bt_animation_42, R.id.bt_animation_43, R.id.bt_animation_44,
            R.id.bt_animation_45, R.id.bt_animation_46, R.id.bt_animation_47, R.id.bt_animation_48,
            R.id.bt_animation_49, R.id.bt_animation_50, R.id.bt_animation_51, R.id.bt_animation_52,
            R.id.bt_animation_53, R.id.bt_animation_54, R.id.tv_animation
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.bt_animation_36:
                //ObjectAnimator 使用 ArgbEvaluator
                //ObjectAnimator的函数都是从ValueAnimator中继承而来的，所以用法和效果与ValueAnimator是完全一样的.
                ObjectAnimatorDemo.doBackgroundColorChange(tvAnimation);
                break;
            case R.id.bt_animation_37:
                //ObjectAnimator PropertyValuesHolder的使用
                ValueAndObjectProperty.doPropertyValuesAnimator(tvAnimation);
                break;
            case R.id.bt_animation_38:
                //PropertyValueHolder 之 ofObject 的使用
                ValueAndObjectProperty.doOfObjectPropertyHolder(mAnimatorTextView);
                break;
            case R.id.bt_animation_39:
                //PropertyValueHolder 之 KeyFrame 的使用
                ValueAndObjectProperty.doOfObjectKeyFrame(tvAnimation);
                break;
            case R.id.bt_animation_40:
                //Keyframe 无 Interpolator
                ValueAndObjectProperty.doNoInterpolatorKeyframeAnimation(tvAnimation);
                break;
            case R.id.bt_animation_41:
                //Keyframe 之使用 Interpolator
                ValueAndObjectProperty.doInterpolatorKeyframeAnimation(tvAnimation);
                break;
            case R.id.bt_animation_42:
                //AnimatorSet 之 playSequentially(顺序播放动画)
                AnimatorSetDemo.doAnimatorSetPlaySequentially(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_43:
                //AnimatorSet 之 playTogether(同时播放)
                AnimatorSetDemo.doAnimatorSetPlayTogether(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_44:
                //AnimatorSet 之 playTogether(同时播放) 循环与延时
                AnimatorSetDemo.doAnimatorSetCyclePlay(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_45:
                //AnimatorSet.Builder 自定义顺序动画播放
                AnimatorSetDemo.doAnimatorSetBuilder(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_46:
                //AnimatorSet 设置监听
                animatorSet = AnimatorSetDemo.doAnimatorSetListener(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_47:
                //AnimatorSet 取消动画
                animatorSet.cancel();
                break;
            case R.id.bt_animation_48:
                //AnimatorSet与ObjectAnimator中动画设置方法的比较（所有和单个的比较）
                AnimatorSetDemo.doAnimatorSetAllAndSingleSetting(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_49:
                //AnimatorSet与ObjectAnimator中设置动画目标setTarget方法的比较
                AnimatorSetDemo.doAnimatorSetSetTarget(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_50:
                //AnimatorSet的延时是仅针对性的延长AnimatorSet激活时间的，对单个动画的延时设置没有影响。
                AnimatorSetDemo.doAnimatorSetSetStartDelay(tvAnimation,mAnimatorTextView);
                break;
            case R.id.bt_animation_51:
                //使用xml形式实现ValueAnimator
                AnimatorSetByXml.doValueAnimatorByXml(this,tvAnimation);
                break;
            case R.id.bt_animation_52:
                //使用xml形式实现ObjectAnimator
                AnimatorSetByXml.doObjectAnimatorByXml(this,tvAnimation);
                break;
            case R.id.bt_animation_53:
                //使用xml形式实现ObjectAnimator中使用android:valueFrom、android:valueTo的color属性用法
                AnimatorSetByXml.doObjectAnimatorColorByXml(this,tvAnimation);
                break;
            case R.id.bt_animation_54:
                //使用xml形式实现AnimatorSet动画
                AnimatorSetByXml.doAnimatorSetByXml(this,tvAnimation);
            default:
                break;
        }
    }
}
