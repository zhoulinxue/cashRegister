package com.zx.mvplibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.presenter.BasePresenter;
import com.zx.network.Param;


/**
 * Copyright (C), zhx-2018
 * FileName: MvpActivity
 * Author: zhx
 * Date: 2018\10\23 0023 19:33
 * Description: mvpActivity 主要是 实现 BaseView类的 公共方法实现 、presenter的 初始化
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = onCtreatPresenter();
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化 presenter
     *
     * @return
     */
    protected abstract P onCtreatPresenter();

    @Override
    public MvpDialog onCreatCustomDialog() {
        return new ImmersionBarDialog(this, R.string.loading_text);
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.destory();
        }
        super.onStop();
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        showToast(msg);
    }


}
