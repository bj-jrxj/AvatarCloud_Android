package com.szjy.avatar.demo.ui;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialog;

import com.jrxj.avatar.GlideUtils;
import com.suke.widget.SwitchButton;
import com.szjy.avatar.demo.R;


public class SettingParmDialog extends AppCompatDialog implements View.OnClickListener {

    private Context mContext;
    private OnHandleListener onHandleListener;
    private RelativeLayout rl_root;
    private AmountView mAmountView, avWidth;
    private ImageView ivDemo;
    private SwitchButton sbIsCircle;
    private View cancleView;
    private ParmBean selectBean;
    private TextView btn_end;

    public SettingParmDialog(Context mContext, ParmBean defaultBean, OnHandleListener onHandleListener) {
        super(mContext, R.style.dialog_operate_message);
        this.mContext = mContext;
        this.onHandleListener = onHandleListener;
        selectBean = defaultBean != null ? defaultBean : new ParmBean();

        initView();
        setLayout();

        setCanceledOnTouchOutside(true);
    }


    private void initView() {
        setContentView(R.layout.dialog_setting_parm);
        rl_root = findViewById(R.id.rl_root);
        mAmountView = findViewById(R.id.mAmountView);
        avWidth = findViewById(R.id.avWidth);
        ivDemo = findViewById(R.id.iv_demo);
        sbIsCircle = findViewById(R.id.switch_button);
        cancleView = findViewById(R.id.cancleView);
        btn_end = findViewById(R.id.btn_end);
        cancleView.setOnClickListener(this::onClick);
        btn_end.setOnClickListener(this::onClick);
        mAmountView.setGoods_storage(40);
        avWidth.setGoods_storage(400);

        avWidth.setAmount(selectBean.imageWidth);
        mAmountView.setAmount(selectBean.radius);
        sbIsCircle.setChecked(selectBean.isCircle);
        updateImage();
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                selectBean.radius = amount;
                if (!sbIsCircle.isChecked()) {
                    updateImage();
                }
            }
        });
        sbIsCircle.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                selectBean.isCircle = isChecked;
                updateImage();
            }
        });

    }

    private void updateImage() {
        if (sbIsCircle.isChecked()) {
            GlideUtils.setCircleImage(ivDemo.getContext(), ivDemo, R.mipmap.ic_demo_launcher, R.mipmap.ic_demo_launcher);
        } else {
            if (selectBean.radius > 0) {
                GlideUtils.setRoundImage(ivDemo.getContext(), ivDemo, R.mipmap.ic_demo_launcher, selectBean.radius, R.mipmap.ic_demo_launcher);
            } else {
                GlideUtils.setImage(ivDemo.getContext(), ivDemo, R.mipmap.ic_demo_launcher, R.mipmap.ic_demo_launcher);
            }
        }
    }

    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancleView:
                dismiss();
                break;
            case R.id.btn_end:
                if (onHandleListener != null) {
                    onHandleListener.onParm(selectBean);
                }
                dismiss();
                break;
        }
    }


    public interface OnHandleListener {
        void onParm(ParmBean parmBean);
    }

    public static class ParmBean {
        public int radius = 0;
        public int imageWidth = 200;
        public boolean isCircle = true;
    }
}