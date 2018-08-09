package com.lznby.bigdemo.wyb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.tools.ARouterTools;

@Route(path = ARouterTools.WybMainActivity)
public class WybMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyb_main);
    }
}
