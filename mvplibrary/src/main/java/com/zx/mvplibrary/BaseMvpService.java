package com.zx.mvplibrary;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.mvp.BaseView;
import com.zx.mvplibrary.presenter.BasePresenter;

public abstract class BaseMvpService<P extends BasePresenter> extends Service implements BaseView {
    private Handler mHandler;
    protected P mPresenter;
    public static String mAction = "re_login";
    private BaseView mBaseView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /**
     * 初始化 presenter
     *
     * @return
     */
    protected abstract P onCtreatPresenter();

    @Override
    public void onCreate() {
        mPresenter = onCtreatPresenter();
        super.onCreate();
        mHandler = new Handler();
        mBaseView = new BaseViewImpl(this, mHandler);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }
    }

    @Override
    public void showLoadingDialog() {
        mBaseView.showLoadingDialog();
    }

    @Override
    public void showToast(String msg) {
        mBaseView.showToast(msg);
    }

    @Override
    public void showToast(int res) {
        mBaseView.showToast(res);
    }

    @Override
    public void dismissLoadingDiaog() {
        mBaseView.dismissLoadingDiaog();
    }

    @Override
    public MvpDialog onCreatCustomDialog() {
        return mBaseView.onCreatCustomDialog();
    }

    @Override
    public void onSuccess(Object object) {
        mBaseView.onSuccess(object);
    }

    @Override
    public void onError(String msg) {
        mBaseView.onError(msg);
    }

    @Override
    public void onError(int code, String msg) {
        mBaseView.onError(code, msg);
    }

    @Override
    public Context getContext() {
        return mBaseView.getContext();
    }

    @Override
    public String getToken() {
        return mBaseView.getToken();
    }
}
