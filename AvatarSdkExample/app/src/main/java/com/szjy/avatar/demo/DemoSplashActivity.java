package com.szjy.avatar.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.gyf.immersionbar.ImmersionBar;

public class DemoSplashActivity extends Activity {
    private ImageView iv_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demosplash);
        iv_bg = findViewById(R.id.iv_bg);
        ImmersionBar.with(this).init();
        iv_bg.postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.startActivity(LoginSimulationActivity.class);
                finish();
            }
        }, 2000);
//        AvatarSelector.create(DemoSplashActivity.this)
//                .setShapeType(AvatarConfig.AVATAR_SHAPE_CIRCLE_CODE)
//                .isSaveAlbum(true)
//                .forResult(new CommonCallBack<Bitmap>() {
//                    @Override
//                    public void onSuccess(Bitmap data) {
////                        imageView.setImageBitmap(data);
//                    }
//                });
//        finish();
    }

}
