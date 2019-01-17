package com.zx.mvplibrary;

import android.app.Activity;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.zx.mvplibrary.wedgit.SystemDialog;

/**
 * Copyright (C),zhx_2018
 * FileName: ImmersionBarDialog
 * Author: zhx
 * Date: 2018\10\23 0023 21:49
 * Description: ${DESCRIPTION}
 */
public class ImmersionBarDialog extends SystemDialog {

    public ImmersionBarDialog(Activity mContext, int mStringSrc) {
        super(mContext, mStringSrc);
    }

    public ImmersionBarDialog(Activity mContext, String mLoadingMsg) {
        super(mContext, mLoadingMsg);
    }

    @Override
    protected void preShow() {
        ImmersionBar.with(mContext, getDialog()).statusBarDarkFont(true)
                .hideBar(BarHide.FLAG_SHOW_BAR).init();
    }

    @Override
    public void dissmiss() {
        super.dissmiss();
        ImmersionBar.with(mContext, getDialog()).destroy();
    }
}
