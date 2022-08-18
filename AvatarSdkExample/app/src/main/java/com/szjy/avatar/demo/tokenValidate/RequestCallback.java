package com.szjy.avatar.demo.tokenValidate;

import org.json.JSONObject;

/**
 * 请求回调接口
 * 
 */
public interface RequestCallback {
    /**
     * 请求结束后，会调用此方法
     */
    void onRequestComplete(String resultCode, String resultDes, JSONObject jsonobj);
}