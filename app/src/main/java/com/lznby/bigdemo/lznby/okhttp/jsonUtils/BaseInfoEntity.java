package com.lznby.bigdemo.lznby.okhttp.jsonUtils;

/**
 * @author Lznby
 * @time 2018/9/9 17:15
 * Class Note:
 * 1.请求结果的基类(解析泛型对象的基类)
 */
public class BaseInfoEntity<T> {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":10227,"password":"123456","token":"","type":0,"username":"Lznbys"}
     * errorCode : 0
     * errorMsg :
     */

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
