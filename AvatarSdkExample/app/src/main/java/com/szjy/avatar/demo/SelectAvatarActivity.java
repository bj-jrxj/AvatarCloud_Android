package com.szjy.avatar.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.jrxj.avatar.GlideUtils;
import com.jrxj.avatar.config.AvatarConfig;
import com.jrxj.avatar.config.AvatarSelector;
import com.jrxj.avatar.ui.view.ShadowLayout;
import com.szjy.avatar.demo.ui.SettingParmDialog;

public class SelectAvatarActivity extends Activity implements View.OnClickListener {
    private ImageView imageView, iv_select_avatar, iv_back, iv_setting;
    private ShadowLayout mShadowLayout;
    private SettingParmDialog.ParmBean defaultBean;
    private Bitmap selectData;
    private TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectavatar);
        ImmersionBar.with(this).statusBarDarkFont(true).titleBarMarginTop(R.id.rel_t).init();
        imageView = findViewById(R.id.imageView);
        iv_select_avatar = findViewById(R.id.iv_select_avatar);
        iv_back = findViewById(R.id.iv_back);
        mShadowLayout = findViewById(R.id.mShadowLayout);
        iv_setting = findViewById(R.id.iv_setting);
        tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this::onClick);
        iv_select_avatar.setOnClickListener(this::onClick);
        iv_back.setOnClickListener(this::onClick);
        mShadowLayout.setOnClickListener(this::onClick);
        iv_setting.setOnClickListener(this::onClick);
        defaultBean = new SettingParmDialog.ParmBean();
        defaultBean.isCircle = true;
        defaultBean.imageWidth = 200;
        defaultBean.radius = 12;
        setImage(ImageUtils.drawable2Bitmap(getResources().getDrawable(R.mipmap.touxiang)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_login:
//                ActivityUtils.startActivity(TestRecyActivity.class);
                break;
            case R.id.mShadowLayout:
            case R.id.iv_select_avatar:
                AvatarSelector.create(SelectAvatarActivity.this)
                        .setShapeType(defaultBean.isCircle ? AvatarConfig.AVATAR_SHAPE_CIRCLE_CODE : AvatarConfig.AVATAR_SHAPE_ROUND_CODE)
                        .setShapeRadius(defaultBean.radius)
                        .setBearingView(imageView)
                        .forResult(data -> {
                            selectData = data;
                        });
                break;
            case R.id.iv_setting:
                SettingParmDialog settingParmDialog = new SettingParmDialog(this, defaultBean, new SettingParmDialog.OnHandleListener() {
                    @Override
                    public void onParm(SettingParmDialog.ParmBean parmBean) {
                        defaultBean = parmBean;

                        setImage(selectData == null ? ImageUtils.drawable2Bitmap(getResources().getDrawable(R.mipmap.touxiang)) : selectData);
                    }
                });
                settingParmDialog.show();
                break;
        }
    }

    private void setImage(Bitmap data) {
        if (defaultBean.isCircle) {
            GlideUtils.setCircleImage(imageView.getContext(), imageView, data, R.mipmap.touxiang);
        } else if (defaultBean.radius > 0) {
            GlideUtils.setRoundImage(imageView.getContext(), imageView, data, defaultBean.radius, R.mipmap.touxiang);
        } else {
            GlideUtils.setImage(imageView.getContext(), imageView, data, R.mipmap.touxiang);
        }
    }
}
