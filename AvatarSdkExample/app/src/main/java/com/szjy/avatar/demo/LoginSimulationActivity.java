package com.szjy.avatar.demo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jrxj.avatar.FApplication;
import com.jrxj.avatar.config.AvatarConfig;
import com.jrxj.avatar.config.AvatarSelector;
import com.jrxj.avatar.sso.AvatarGenAuthThemeConfig;
import com.jrxj.avatar.sso.AvatarGenAuthnHelper;
import com.jrxj.avatar.sso.AvatarGenBackPressedListener;
import com.jrxj.avatar.sso.AvatarGenCheckBoxListener;
import com.jrxj.avatar.sso.AvatarGenCheckedChangeListener;
import com.jrxj.avatar.sso.AvatarGenCustomInterfaceListener;
import com.jrxj.avatar.sso.AvatarGenLoginClickListener;
import com.jrxj.avatar.sso.AvatarGenLoginPageInListener;
import com.jrxj.avatar.sso.AvatarGenTokenListener;
import com.szjy.avatar.demo.login.LoginParmsActivity;
import com.szjy.avatar.demo.utils.Constant;
import com.szjy.avatar.demo.utils.StringFormat;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class LoginSimulationActivity extends Activity {
    private TextView tv_login, tv_user_xy;
    private ImageView iv_agreement;
    AvatarGenAuthnHelper avatarGenAuthnHelper;
    AvatarGenAuthThemeConfig.Builder themeConfigBuilder;

    Dialog alertDialog;
    Context mContext;
    private AvatarGenTokenListener genTokenListener = null;
    public String mResultString;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE_PRE = 1000;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE_IMPLICIT_LOGIN = 2000;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE_DISPLAY_LOGIN = 3000;
    private static final int CMCC_SDK_REQUEST_GET_PHONE_INFO_CODE = 1111;
    private static final int CMCC_SDK_REQUEST_MOBILE_AUTH_CODE = 2222;
    private static final int CMCC_SDK_REQUEST_LOGIN_AUTH_CODE = 3333;
    private static final int CMCC_SDK_REQUEST_TOKEN_VALIDATE_CODE = 4444;
    private static final int CMCC_SDK_REQUEST_PHONE_VALIDATE_CODE = 5555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mContext = this;
        tv_login = findViewById(R.id.tv_login);
        iv_agreement = findViewById(R.id.iv_agreement);
        tv_user_xy = findViewById(R.id.tv_user_xy);
        tv_login.setSelected(true);

        avatarGenAuthnHelper = AvatarGenAuthnHelper.getInstance(FApplication.getInstance());
        initConfig();
        addListener();

        tv_user_xy.setOnClickListener(v -> ActivityUtils.startActivity(LoginParmsActivity.class));
        PGWGetMobile();
    }

    private void addListener() {
        genTokenListener = (i, jObj) -> {
            if (jObj != null) {
                mResultString = jObj.toString();
                LogUtils.e("handleMessage", "RESULT", StringFormat
                        .logcatFormat(mResultString));

                if (jObj.has("token")) {
                    String mAccessToken = jObj.optString("token");
                    avatarGenAuthnHelper.quitAuthActivity();
                    finish();
                    ActivityUtils.startActivity(SelectAvatarActivity.class);
                } else {
                    ToastUtils.showLong(StringFormat
                            .logcatFormat(mResultString));
                }

                if (jObj.has("resultCode") && jObj.optInt("resultCode") == 103000 && !jObj.has("token")) {
                    avatarGenAuthnHelper.setAuthThemeConfig(themeConfigBuilder.build());
                    displayLogin();
                }
            }
        };


        avatarGenAuthnHelper.setPageInListener((resultCode, jsonObject) -> {
            if (resultCode.equals("200087")) {
                Log.d("initSDK", "page in---------------");
            }
            LogUtils.e("avatardemo", "onLoginPageInComplete = code" + resultCode + "jsonObject = " + jsonObject);
        });
        tv_login.setOnClickListener(view -> {
            //是否弹窗模式
//            themeConfigBuilder.setAuthPageWindowMode(300, 300)
//                    .setNumFieldOffsetY(80)
//                    .setLogBtnOffsetY(150)
////                        .setWindowBottom(1)
////                        .setAuthPageWindowOffset(0,0)
//                    .setThemeId(R.style.loginDialog);


//            avatarGenAuthnHelper = AvatarGenAuthnHelper.getInstance(FApplication.getInstance());
//            avatarGenAuthnHelper.setAuthThemeConfig(themeConfigBuilder.build());
//            displayLogin();

            finish();
            ActivityUtils.startActivity(SelectAvatarActivity.class);
        });
        iv_agreement.setOnClickListener(v -> iv_agreement.setSelected(!iv_agreement.isSelected()));

    }

    private void displayLogin() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE_DISPLAY_LOGIN);
            } else {
                avatarGenAuthnHelper.loginAuth( genTokenListener, CMCC_SDK_REQUEST_LOGIN_AUTH_CODE);
            }
        } else {
            avatarGenAuthnHelper.loginAuth( genTokenListener, CMCC_SDK_REQUEST_LOGIN_AUTH_CODE);
        }
    }

    private void PGWGetMobile() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE_PRE);
            } else {
                avatarGenAuthnHelper.getPhoneInfo( genTokenListener, CMCC_SDK_REQUEST_GET_PHONE_INFO_CODE);
            }
        } else {
            avatarGenAuthnHelper.getPhoneInfo( genTokenListener, CMCC_SDK_REQUEST_GET_PHONE_INFO_CODE);
        }
    }

    private void initConfig() {
        themeConfigBuilder = new AvatarGenAuthThemeConfig.Builder()
                .setIsUseDefault(false)
                .setStatusBar(0xffffffff, true)
                .setSalognText("欢迎来到头像云！")
                .setLogoDrawableId(R.mipmap.ic_demo_launcher)
                .setPrivacyState(false)
                .setSwitchText("切换账号")
                .setCloseDrawableId(com.jrxj.avatar.R.mipmap.ic_close)
                .setAvatarGenBackPressedListener(new AvatarGenBackPressedListener() {
                    @Override
                    public void onBackPressed() {
                        Toast.makeText(LoginSimulationActivity.this, "返回键回调", Toast.LENGTH_SHORT).show();
                    }
                })
                .setAvatarGenCustomInterfaceListener(new AvatarGenCustomInterfaceListener() {
                    @Override
                    public void onSwitchLoginMode(Context context) {
                        avatarGenAuthnHelper.quitAuthActivity();
                        Toast.makeText(LoginSimulationActivity.this, "切换登录方式", Toast.LENGTH_SHORT).show();
                    }
                })
                .setLogBtnClickListener(new AvatarGenLoginClickListener() {
                    @Override
                    public void onLoginClickStart(Context context, JSONObject jsonObj) {
                        alertDialog = new AlertDialog.Builder(context, R.style.custom_alertdialog_background).create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                if (event.getAction() == KeyEvent.ACTION_UP) {
                                    dialog.dismiss();
                                    alertDialog = null;
                                }
                                return keyCode == KeyEvent.KEYCODE_BACK;
                            }
                        });
                        alertDialog.show();
                        alertDialog.setContentView(LayoutInflater.from(mContext).inflate(R.layout.ssoloading_alert, null));
                    }

                    @Override
                    public void onLoginClickComplete(Context context, JSONObject jsonObj) {
                        if (alertDialog != null && alertDialog.isShowing()) {
                            alertDialog.dismiss();
                            alertDialog = null;
                        }
                    }
                })

                .setAvatarGenCheckBoxListener(new AvatarGenCheckBoxListener() {
                    @Override
                    public void onLoginClick(Context context, JSONObject jsonObj) {
                        Toast.makeText(context, "自定义勾选文本", Toast.LENGTH_LONG).show();
                    }
                })

                .setGenCheckedChangeListener(new AvatarGenCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(boolean b) {
                        Log.d("是否勾选协议", b + "");
                    }
                });

//        avatarGenAuthnHelper.setAuthThemeConfig(themeConfigBuilder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        avatarGenAuthnHelper.setAuthThemeConfig(null);
        avatarGenAuthnHelper.setPageInListener(null);
        genTokenListener = null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_PHONE_STATE_DISPLAY_LOGIN:
                avatarGenAuthnHelper.loginAuth( genTokenListener, CMCC_SDK_REQUEST_LOGIN_AUTH_CODE);
                break;
            default:
                break;
        }
    }

}
