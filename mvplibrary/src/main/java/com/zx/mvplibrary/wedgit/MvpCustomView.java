package com.zx.mvplibrary.wedgit;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.xgsb.datafactory.bean.User;
import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.mvp.BaseView;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.BaseActivity;
import com.zx.mvplibrary.BaseCustomView;
import com.zx.mvplibrary.MvpApplication;
import com.zx.mvplibrary.presenter.BasePresenter;


/**
 * Name: MvpCustomView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-04 16:38
 */
public abstract class MvpCustomView<P extends BasePresenter> extends BaseCustomView implements BaseView {
    protected P mPresenter;
    private MvpDialog mDialog;
    protected Handler mHandler;

    /**
     * 初始化 presenter
     *
     * @return
     */
    protected abstract P onCtreatPresenter();

    public MvpCustomView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    public MvpCustomView(Context context) {
        super(context);
    }

    @Override
    protected void initView(Context context, View rootView) {
        mHandler = new Handler();
        mPresenter = onCtreatPresenter();
        onInitView(context, rootView);
        onInitData();
    }

    protected abstract void onInitData();

    protected abstract void onInitView(Context context, View rootView);

    @Override
    public void showToast(int res) {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).showToast(res);
        }
    }

    @Override
    public void showLoadingDialog() {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).showLoadingDialog();
        }
    }

    @Override
    public void onError(String msg) {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).onError(msg);
        }
    }

    @Override
    public void onSuccess(Object object) {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).onSuccess(object);
        }
    }

    @Override
    public void showToast(String msg) {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).showToast(msg);
        }
    }

    @Override
    public void dismissLoadingDiaog() {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).dismissLoadingDiaog();
        }
    }

    @Override
    public MvpDialog onCreatCustomDialog() {
        if (getContext() instanceof BaseActivity) {
            return ((BaseActivity) getContext()).onCreatCustomDialog();
        }
        return null;
    }

    public User getUser() {
        if (getContext() instanceof BaseActivity) {
            return ((BaseActivity) getContext()).getUser();
        }
        return null;
    }

    @Override
    public String getToken() {
        if (getContext() instanceof BaseActivity) {
            return ((BaseActivity) getContext()).getToken();
        }
        return "";
    }

    @Override
    public void onError(int code, String msg) {
        if (getContext() instanceof BaseActivity) {
            ((BaseActivity) getContext()).onError(code, msg);
        }
    }
}
