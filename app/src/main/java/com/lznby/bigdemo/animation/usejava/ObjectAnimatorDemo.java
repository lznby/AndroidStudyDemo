package com.lznby.bigdemo.animation.usejava;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Lznby
 * @time 2018/8/11 22:25
 * Class Note: ObjectAnimator 基本使用
 * <p>
 * ValueAnimator缺点：
 * 1.只能对数值对动画计算。我们要想对哪个控件操作，需要监听动画过程，在监听中对控件操作
 * <p>
 * ObjectAnimator特点：
 * 1.为了让动画直接与对应控件相关联，以使我们从监听动画过程中解放出来，谷歌的开发人员在ValueAnimator的基础上，
 * 又派生了一个类ObjectAnimator
 * 2.由于ObjectAnimator是派生自ValueAnimator的，所以ValueAnimator中所能使用的方法，在ObjectAnimator中都可以正常使用。
 */
public class ObjectAnimatorDemo {

    /**
     * ValueAnimator动画流程与ObjectAnimator动画流程对比
     *
     * 1.ValueAnimator动画流程
     * offInt(0,200)[设置动画数字区间]->加速器[返回当前数字进度,如0.2]->Evaluator[根据数字进度计算当前值]->在[AnimatorUpdateListener]中返回
     *
     * 2.ObjectAnimator动画流程
     * ofFloat(view,"ScaleX",0,3,1)[定义动画对象及区间]->加速器[返回当前数字进度]->Evaluator[根据数字进度计算当前值]->调用set函数[根据属性拼装set函数并根据反射调用,并将当前值作为参数传入]
     *
     * Note:ValueAnimator和ObjectAnimator设置加速器和Evaluator是一样的
     */

    /**
     * setter函数
     *
     * 1.ObjectAnimator做动画，并不是根据控件xml中的属性来改变的，而是通过指定属性所对应的set方法来改变的。
     * 2.比如，我们指定改变rotation的属性值，ObjectAnimator在做动画时就会到指定控件（TextView）中去找对应的setRotation()方法来改变控件中对应的值。
     * 3.View中继承过来的，在View中有关动画，总共有下面几组set方法
     *
     * //1、透明度：alpha
     * public void setAlpha(float alpha)
     *
     * //2、旋转度数：rotation、rotationX、rotationY
     * public void setRotation(float rotation)
     * public void setRotationX(float rotationX)
     * public void setRotationY(float rotationY)
     *
     * //3、平移：translationX、translationY
     * public void setTranslationX(float translationX)
     * public void setTranslationY(float translationY)
     *
     * //4、缩放：scaleX、scaleY
     * public void setScaleX(float scaleX)
     * public void setScaleY(float scaleY
     *
     * 4.View中已经实现了有关alpha,rotaion,translate,scale相关的set方法。所以我们在构造ObjectAnimator时可以直接使用。
     *
     * 特点：
     * 1、要使用ObjectAnimator来构造对画，要操作的控件中，必须存在对应的属性的set方法
     * 2、setter 方法的命名必须以骆驼拼写法命名，即set后每个单词首字母大写，其余字母小写，即类似于setPropertyName所对应的属性为propertyName
     */

    /**
     * ObjectAnimator setAlpha 改变透明度
     *
     * @param view
     */
    public static void startObjectAnimatorAlpha(View view) {
        /**
         * 1.第一个参数用于指定这个动画要操作的是哪个控件
         * 2.第二个参数用于指定这个动画要操作这个控件的哪个属性
         * 3.第三个参数是可变长参数，这个就跟ValueAnimator中的可变长参数的意义一样了，就是指这个属性值是从哪变到哪。
         */
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1, 0, 1);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * ObjectAnimator setRotation 控件旋转(平面旋转)
     *
     * @param view
     */
    public static void startObjectAnimatorRotation(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, 180, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * ObjectAnimator setRotationX 上下旋转(立体)
     *
     * @param view
     */
    public static void startObjectAnimatorRotationX(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0, 270, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * ObjectAnimator setRotationY 左右翻转(立体)
     *
     * @param view
     */
    public static void startObjectAnimatorRotationY(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0, 180, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * setter函数 平移动画
     * <p>
     * setTranslationX与setTranslationY
     * <p>
     * 1.setTranslationX(float translationX) :表示在X轴上的平移距离,以当前控件为原点，向右为正方向，参数translationX表示移动的距离。
     * 2.setTranslationY(float translationY) :表示在Y轴上的平移距离，以当前控件为原点，向下为正方向，参数translationY表示移动的距离。
     *
     * Note:每次移动距离的计算都是以原点为中心的(控件原坐标)
     */

    /**
     * ObjectAnimator setTranslationX (X轴方向平移)
     *
     * @param view
     */
    public static void startObjectAnimatorTranslationX(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 200, -200, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * ObjectAnimator setTranslationY (Y轴方向平移)
     *
     * @param view
     */
    public static void startObjectAnimatorTranslationY(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, 200, -100, 0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * setter函数 拉伸(缩放)动画
     *
     * setScale与setScaleY
     * 1.setScaleX(float scaleX):在X轴上缩放，scaleX表示缩放倍数
     * 2.setScaleY(float scaleY):在Y轴上缩放，scaleY表示缩放倍数
     */

    /**
     * setScaleX 水平拉伸(缩放)
     *
     * @param view
     */
    public static void startObjectAnimatorScaleX(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 0, 3, 1);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * setScaleY 垂直拉伸(缩放)
     *
     * @param view
     */
    public static void startObjectAnimatorScaleY(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleY", 0, 3, 1);
        animator.setDuration(2000);
        animator.start();
    }

    /******************************************以上为所有View自带的ObjectAnimator set函数*********************************************/

    /******************************************以下为自定义ObjectAnimator属性(set方法)*********************************************/

    /**
     * 自定义一个保存圆信息的类
     */
    public static class Point {
        private int mRadius;

        public Point(int radius) {
            mRadius = radius;
        }

        public int getRadius() {
            return mRadius;
        }

        public void setRadius(int radius) {
            mRadius = radius;
        }
    }

    /**
     * 自定义控件——圆形控件MyPointView
     *
     * 1.第一点，这个set函数所对应的属性应该是pointRadius或者PointRadius。前面我们已经讲了第一个字母大小写无所谓，后面的字母必须保持与set函数完全一致。
     * 2.第二点，在setPointRadius中，先将当前动画传过来的值保存到mPoint中，做为当前圆形的半径。然后强制界面刷新
     * 3.在界面刷新后，就开始执行onDraw()函数
     * 4.在onDraw函数中，就是根据当前mPoint的半径值在(300,300)点外画一个圆；
     */
    public static class MyPointView1 extends View {
        private Point point = new Point(0);

        public MyPointView1(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (point != null){
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(100,100, point.getRadius(),paint);
            }
            super.onDraw(canvas);
        }


        /**
         * 自定义的ObjectAnimator set方法
         *
         * @param radius
         */
        void setPoint(int radius) {
            //设置圆半径
            point.setRadius(radius);
            //强制刷新
            invalidate();
        }

        public int getPoint(){
            return 50;
        }
    }

    /**
     * 使用自定义ObjectAnimator set方法
     *
     * @param view
     */
    public static void doPointViewAnimator(MyPointView1 view) {
        ObjectAnimator animator = ObjectAnimator.ofInt(view,"point",0,100,0);
        animator.setDuration(2000);
        animator.start();
    }

    /**
     * ObjectAnimator 使用ArgbEvaluator
     * @param view
     */
    public static void doBackgroundColorChange(View view) {
        ObjectAnimator animator = ObjectAnimator.ofInt(view,"BackgroundColor",0xffff00ff,0xffffff00,0xffff00ff);
        animator.setDuration(8000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

}


