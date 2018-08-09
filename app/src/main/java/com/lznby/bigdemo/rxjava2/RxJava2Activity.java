package com.lznby.bigdemo.rxjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = ARouterTools.RxJava2Activity)
public class RxJava2Activity extends AppCompatActivity implements View.OnClickListener{

    //1.使用黄油到绑定控件

    @BindView(R.id.tv_rxjava2)
    TextView tvRxJava2;
    @BindView(R.id.bt_rxjava2_1)
    Button btRxJava1;
    @BindView(R.id.bt_rxjava2_2)
    Button btRxJava2;
    @BindView(R.id.bt_rxjava2_3)
    Button btRxJava3;
    @BindView(R.id.bt_rxjava2_4)
    Button btRxJava4;
    @BindView(R.id.bt_rxjava2_5)
    Button btRxJava5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);

        //2.将黄油刀绑定到这个Activity
        ButterKnife.bind(this);

        //3.为控件添加点击事件监听
        initOnClickListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rxjava2_1:
                RxJava2.flowableSubscribe();
                break;
            case R.id.bt_rxjava2_2:
                RxJava2.flowableActions();
                break;
            case R.id.bt_rxjava2_3:
                RxJava2.observableSubscript();
                break;
            case R.id.bt_rxjava2_4:
                break;
            case R.id.bt_rxjava2_5:
                break;
            default:
                break;
        }
    }

    /**
     * 为控件添加点击事件监听
     */
    private void initOnClickListener() {
        btRxJava1.setOnClickListener(this);
        btRxJava2.setOnClickListener(this);
        btRxJava3.setOnClickListener(this);
        btRxJava4.setOnClickListener(this);
        btRxJava5.setOnClickListener(this);
    }
}
