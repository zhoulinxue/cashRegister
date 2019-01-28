package com.zx.mvplibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.luojilab.component.componentlib.service.AutowiredService;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.User;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.mvp.BaseView;
import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.network.Param;

/**
 * Copyright (C),zhx_2018
 * FileName: BaseActivity
 * Author: zhx
 * Date: 2018\10\23 0023 21:43
 * Description: 项目基础类
 */
public abstract class BaseActivity extends InternationalizationActivity implements BaseView {
    protected Handler mHandler;
    private MvpDialog mDialog;
    protected final int DEFAULT_MINUTES = 1000;
    private InputMethodManager mInputMethodManager;
    protected final String DEFAULT_TOKEN = "default_token";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler = new Handler();
        mDialog = onCreatCustomDialog();
        AutowiredService.Factory.getSingletonImpl().autowire(this);
        //设置布局文件
        setContentView(initLayout());
        //初始化view
        onCreateView();
        //获取已保存数据、或者网络请求
        onInitData(savedInstanceState);
        this.mInputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void fullWindow() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 初始化 界面布局
     *
     * @return 布局 xml
     */
    protected abstract int initLayout();

    /**
     * 初始化布局
     */
    protected abstract void onCreateView();


    /**
     * 初始化数据
     *
     * @param savedInstanceState 获取已保存的数据
     */
    protected abstract void onInitData(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingDialog() {
        if (mDialog != null)
            mDialog.show();
    }

    @Override
    public void showToast(String msg) {
        mHandler.post(new ToastRunable(this, msg));
    }

    @Override
    public void showToast(int res) {
        if (res != 0)
            showToast(getResources().getString(res));
    }

    @Override
    public void dismissLoadingDiaog() {
        mDialog.dissmiss();
    }


    @Override
    public MvpDialog onCreatCustomDialog() {
        return new ImmersionBarDialog(this, R.string.loading_text);
    }

    /**
     * 单次点击事件
     *
     * @param v
     * @param listener
     */
    public void singleClickOnMinutes(View v, View.OnClickListener listener) {
        AppUtil.registerClick(DEFAULT_MINUTES, v, listener);
    }

    protected void onLoginSuc(User user) {
        SPUtil.getInstance().put(Param.Keys.USER, JSONManager.getInstance().toJson(user));
    }

    @Override
    public String getToken() {
        return getUser().getToken();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        fullWindow();
        if (isHideInput())
            hideInputMethod();
        return super.dispatchTouchEvent(ev);
    }

    protected boolean isHideInput() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fullWindow();
    }

    @Override
    public void onError(String msg) {
        if (!isNetWorkconnected()) {
            showToast("网络连接已断开");
            return;
        }
    }

    protected boolean isNetWorkconnected() {
        return !TextUtils.isEmpty(AppUtil.getNetworkState(this));
    }

    protected void hideInputMethod() {
        if (this.getWindow().getDecorView() != null && this.mInputMethodManager != null) {
            this.mInputMethodManager.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public void onSuccess(Object object) {

    }

    public User getUser() {
        if (TextUtils.isEmpty(SPUtil.getInstance().getString(Param.Keys.USER))) {
            User user = new User();
            user.setToken(DEFAULT_TOKEN);
            return user;
        }
        String userjson=SPUtil.getInstance().getString(Param.Keys.USER);
        return (User) JSONManager.getInstance().parseObject(userjson, User.class);
    }
    public void quitUser() {
        SPUtil.getInstance().putString(Param.Keys.USER, "");
    }
}
