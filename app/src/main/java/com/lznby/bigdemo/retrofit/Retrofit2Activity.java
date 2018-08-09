package com.lznby.bigdemo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.retrofit.demo1.BlogClient;
import com.lznby.bigdemo.retrofit.demo10.BlogClient10;
import com.lznby.bigdemo.retrofit.demo2.BlogClient2;
import com.lznby.bigdemo.retrofit.demo3.BlogClient3;
import com.lznby.bigdemo.retrofit.demo4.BlogClient4;
import com.lznby.bigdemo.retrofit.demo5.BlogClient5;
import com.lznby.bigdemo.retrofit.demo6.BlogClient6;
import com.lznby.bigdemo.retrofit.demo7.BlogClient7;
import com.lznby.bigdemo.retrofit.demo8.BlogClient8;
import com.lznby.bigdemo.retrofit.demo9.BlogClient9;
import com.lznby.bigdemo.tools.ARouterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @author Lznby
 * @time 2018/7/7 10:48
 * Class Note:  Retrofit2学习
 */
@Route(path = ARouterTools.Retrofit2Activity)
public class Retrofit2Activity extends AppCompatActivity implements View.OnClickListener{
    //1.使用黄油刀绑定视图
    @BindView(R.id.bt_retrofit_1)
    Button btRetrofit1;
    @BindView(R.id.bt_retrofit_2)
    Button btRetrofit2;
    @BindView(R.id.bt_retrofit_3)
    Button btRetrofit3;
    @BindView(R.id.bt_retrofit_4)
    Button btRetrofit4;
    @BindView(R.id.bt_retrofit_5)
    Button btRetrofit5;
    @BindView(R.id.bt_retrofit_6)
    Button btRetrofit6;
    @BindView(R.id.bt_retrofit_7)
    Button btRetrofit7;
    @BindView(R.id.bt_retrofit_8)
    Button btRetrofit8;
    @BindView(R.id.bt_retrofit_9)
    Button btRetrofit9;
    @BindView(R.id.bt_retrofit_10)
    Button btRetrofit10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        //2.将黄油刀框架绑定到这个Activity上
        ButterKnife.bind(this);

        //3.为Button设置监听事件
        btRetrofit1.setOnClickListener(this);
        btRetrofit2.setOnClickListener(this);
        btRetrofit3.setOnClickListener(this);
        btRetrofit4.setOnClickListener(this);
        btRetrofit5.setOnClickListener(this);
        btRetrofit6.setOnClickListener(this);
        btRetrofit7.setOnClickListener(this);
        btRetrofit8.setOnClickListener(this);
        btRetrofit9.setOnClickListener(this);
        btRetrofit10.setOnClickListener(this);
    }

    /**
     * 点击监听事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_retrofit_1:
                BlogClient.invokeInterface();
                break;
            case R.id.bt_retrofit_2:
                BlogClient2.invokeInterface();
                break;
            case R.id.bt_retrofit_3:
                BlogClient3.invokeInterface();
                break;
            case R.id.bt_retrofit_4:
                BlogClient4.invokeInterface();
                break;
            case R.id.bt_retrofit_5:
                BlogClient5.invokeInterface();
                break;
            case R.id.bt_retrofit_6:
                BlogClient6.invokeInterface();
                break;
            case R.id.bt_retrofit_7:
                BlogClient7.invokeInterface();
                break;
            case R.id.bt_retrofit_8:
                BlogClient8.invokeInterface();
                break;
            case R.id.bt_retrofit_9:
                BlogClient9.invokeInterface();
                break;
            case R.id.bt_retrofit_10:
                BlogClient10.invokeInterface();
                break;
            default:
                break;
        }
    }
}
