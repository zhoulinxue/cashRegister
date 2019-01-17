package com.zx.mvplibrary.wedgit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zx.api.api.app.MvpDialog;
import com.zx.mvplibrary.R;

/**
 * Copyright (C), 2015-2018
 * FileName: SystemDialog
 * Author: zhx
 * Date: 2018\10\23 0023 20:59
 * Description: 自定义 加载框  实现一个 系统自带Dialog
 */
public class SystemDialog implements MvpDialog {
    protected Activity mContext;
    private String mLoadingMsg;
    private Dialog mDialog;
    private static int mStringSrc;

    public SystemDialog(Activity mContext, int mStringSrc) {
        this.mContext = mContext;
        this.mStringSrc = mStringSrc;
    }

    public SystemDialog(Activity mContext, String mLoadingMsg) {
        this.mContext = mContext;
        this.mLoadingMsg = mLoadingMsg;
    }

    @Override
    public void show() {
        if (mDialog == null) {
            mDialog = createLoadingDialog(mContext, mLoadingMsg);
        }
        if (mDialog != null && !mDialog.isShowing()) {
            preShow();
            mDialog.show();
        }
    }

    protected void preShow() {
    }

    @Override
    public void dissmiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public boolean isShowing() {
        return mDialog!=null&&mDialog.isShowing();
    }

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_loading_dialog, null);// 得到加载view
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.rl_dialog_view);// 加载布局
        TextView tipTextView = (TextView) view.findViewById(R.id.tv_loading_text);
        if (!TextUtils.isEmpty(msg)||mStringSrc!=0) {
            // 设置加载信息
            tipTextView.setText(msg);
        } else {
            // 没文字时不显示
            tipTextView.setVisibility(View.GONE);
        }
        Dialog dialog = new Dialog(context, R.style.LoadingDialogStyle);// 创建自定义样式dialog
        dialog.setCancelable(true);// 不可以用“返回键”取消
        // 设置布局
        dialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return dialog;
    }

    public Dialog getDialog() {
        return mDialog;
    }
}
