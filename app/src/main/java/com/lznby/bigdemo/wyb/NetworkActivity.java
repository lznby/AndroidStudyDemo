package com.lznby.bigdemo.wyb;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lznby.bigdemo.R;
import com.lznby.bigdemo.databinding.ActivityNetworkBinding;
import com.lznby.bigdemo.tools.ARouterTools;
import com.lznby.bigdemo.wyb.network.Api;
import com.lznby.bigdemo.wyb.network.ApiUtils;
import com.lznby.bigdemo.wyb.network.RegisterParams;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@Route(path = ARouterTools.NetworkActivity)
public class NetworkActivity extends AppCompatActivity {

    //初始化注册参数
    RegisterParams params = new RegisterParams();

    //初始化Api
    public Api api = ApiUtils.INSTANCE.getApi(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNetworkBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_network);
        binding.setVm(this);
        binding.setParams(params);
    }

    public void onRegisterClick(View view) {
        if (!(TextUtils.isEmpty(params.getUsername()) && TextUtils.isEmpty(params.getPassword()))) {
            //Disposable aaa = api.register(params)
            Disposable aaa = api.register(params.getUsername(),params.getPassword(),params.getRepassword())
                    .doOnNext(a -> Timber.i("返回数据" + a.getData() + a.getErrorCode() + a.getErrorMsg()))
                    .subscribe(i -> Timber.i("i.getErrorMsg()"+i.getErrorMsg()+"i.getErrorCode()"+i.getErrorCode()), throwable -> Timber.i("网络错误"));


        }
    }


    public void onLoginClick(View view) {

    }
}
