package com.lznby.bigdemo.lznby.recycleview.mutilType.viewholder;

import android.view.View;
import android.widget.TextView;

import com.lznby.bigdemo.R;
import com.lznby.bigdemo.lznby.recycleview.mutilType.bean.BannerBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lznby
 * @time 2018/9/5 9:20
 * Class Note:图片的ViewHolder
 */
public class BannerViewHolder extends BaseViewHolder{
    @BindView(R.id.tv_banner)
    TextView tv_banner;
    public BannerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindViewData(Object data) {
        tv_banner.setText(((BannerBean)data).getUrl());
    }
}
