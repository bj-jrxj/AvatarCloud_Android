package com.szjy.avatar.demo;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ProcessUtils;
import com.jrxj.avatar.BuildConfig;
import com.jrxj.avatar.FApplication;
import com.jrxj.avatar.config.AvatarSelectClient;
import com.jrxj.avatar.http.HttpManager;
import com.jrxj.avatar.swipebacklib.SlideFinishManager;
import com.szjy.avatar.demo.utils.Constant;

public class MyApplication extends Application {
    public static final String TAG = "FApplication";

    private static MyApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        if (ProcessUtils.isMainProcess()) {
            AvatarSelectClient.registerAvatarSelect(this, Constant.APP_ID, Constant.APP_KEY, new AvatarSelectClient.InitAvatarListener() {

                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
        }
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

}