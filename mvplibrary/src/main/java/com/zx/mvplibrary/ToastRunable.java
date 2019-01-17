package com.zx.mvplibrary;

import android.content.Context;
import android.widget.Toast;

import com.zx.mvplibrary.utils.MvpUtils;

/**
 * Copyright (C), 2015-2018
 * FileName: ToastRunable
 * Author: zhx
 * Date: 2018\10\23 0023 20:44
 * Description: ${DESCRIPTION}
 */
public class ToastRunable implements Runnable {
    private Context mContext;
    private String mToastMsg;

    public ToastRunable(Context mContext, String mToastMsg) {
        this.mContext = mContext;
        this.mToastMsg = mToastMsg;
    }

    @Override
    public void run() {
        if (MvpUtils.isAppRunningForeground(mContext)) {
            //app 处于后台时 不显示提示信息
            Toast.makeText(mContext, mToastMsg, Toast.LENGTH_LONG).show();
        }
    }
}
