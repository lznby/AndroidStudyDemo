package com.lznby.bigdemo.animation.usejava;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

/**
 * @author Lznby
 * @time 2018/8/11 19:00
 * Class Note: ValueAnimator高级进阶（二）
 * ofObject():
 * 1.ofInt()和ofFloat()来定义动画具有局限性,ofInt()只能传入Integer类型的值，而ofFloat（）则只能传入Float类型的值.
 * 2.ValueAnimator还有一个函数ofObject(),可以传进去任何类型的变量.
 * <p>
 * 方法定义如下:
 * public static ValueAnimator ofObject(TypeEvaluator evaluator, Object... values);
 * <p>
 * 参数说明:
 * 1.TypeEvaluator evaluator：自定义的Evaluator
 * 2.Object... values：可变长参数，Object类型的
 */
public class SeniorValueAnimator {
    /*************************************************分割线***************************************************************/

    /**
     * ValueAnimator 之 ofObject()的使用
     * 字母变换(A-Z)
     *
     * @param view
     */
    public static void startValueAnimatorCharEvaluator(final TextView view) {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char) animation.getAnimatedValue();
                view.setText(String.valueOf(text));
            }
        });
        animator.setDuration(10000);
        //设置插值器
        //AccelerateInterpolator为加速插值器（加速插值器的特点就是随着动画的进行，速度会越来越快）
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    /**
     * 自定义字符变换Evaluator计算器类
     */
    public static class CharEvaluator implements TypeEvaluator<Character> {
        @Override
        public Character evaluate(float fraction, Character startValue, Character endValue) {
            int startInt = (int) startValue;
            int endInt = (int) endValue;
            int curInt = (int) (startInt + fraction * (endInt - startInt));
            char result = (char) curInt;
            return result;
        }
    }

    /*************************************************分割线***************************************************************/

    /**
     * 自定义类Point（圆）
     */
    public static class Point {
        /**
         * 圆的半径
         */
        private int radius;

        public Point(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }
    }

    /**
     * 自定义圆形View控件
     */
    public static class MyPointView extends View {
        private Point point;

        public MyPointView(Context context, AttributeSet attrs){
            super(context,attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (point!=null) {
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(50,50,point.getRadius(),paint);
            }
        }
    }

    /**
     * 自定义Evaluator圆形计算器类
     */
    public static class PointEvaluator implements TypeEvaluator<Point> {
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int start = startValue.getRadius();
            int end  = endValue.getRadius();
            int curValue = (int)(start + fraction * (end - start));
            return new Point(curValue);
        }
    }


    /**
     * 自定义圆形控件缩放动画
     * @param view
     */
    public static void startValueAnimatorDrawPoint(final MyPointView view){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(),new Point(10),new Point(50));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.point = (Point)animation.getAnimatedValue();
                view.invalidate();
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();
    }

    /*************************************************分割线***************************************************************/

}
