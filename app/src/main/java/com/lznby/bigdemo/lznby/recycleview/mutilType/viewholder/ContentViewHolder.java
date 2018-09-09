package com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder;

import android.view.View;
import android.widget.TextView;

import com.lznby.bigdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * @time 2018/9/5 9:30
 * Class Note:图片的ViewHolder
 */
public class ContentViewHolder extends BaseViewHolder {
    @BindView(R.id.tv_text)
    TextView tv_text;

    public ContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bindViewData(Object data) {
        //做一些绑定视图数据的事情
    }
}
