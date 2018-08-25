package com.lznby.bigdemo.wyb.network;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 可以去查一下GsonBuilder 的用法
 * @author Richard_Y_Wang
 * @version $Rev$
 * @des 2018/8/19
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ApiParams {

    public RequestBody entityToJson() {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        return RequestBody.create(mediaType, new Gson().toJson(this));
    }

    public String encrypt(Object json) {
        return json.toString();
    }
}
