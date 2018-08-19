package com.lznby.bigdemo.wyb.network;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/8/19
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface Api {

    String HOST = "http://www.wanandroid.com";

    ///user/register
    @POST("/user/register")
    Observable<InfoEntity> register(@Body RegisterParams params);
}
