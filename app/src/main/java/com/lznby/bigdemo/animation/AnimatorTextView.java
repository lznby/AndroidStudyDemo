package com.lznby.bigdemo.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author Lznby
 * @time 2018/9/17 16:40
 * Class Note:
 * 1.Animator 中 ObjectAnimator 的 PropertyValuesHolder 的 ofObject中使用的自定义TextView
 */
public class AnimatorTextView extends TextView {

    public AnimatorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setCharText(Character character){
        setText(String.valueOf(character));
    }
}
