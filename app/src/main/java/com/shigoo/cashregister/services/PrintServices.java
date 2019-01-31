package com.shigoo.cashregister.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.shigoo.cashregister.activitys.RouterActivity;
import com.shigoo.cashregister.mvp.contacts.PrintContacts;
import com.shigoo.cashregister.mvp.presenter.PrintPresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.User;
import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.BaseMvpService;
import com.zx.network.Param;

import java.util.List;

public class PrintServices extends BaseMvpService<PrintPresenter> implements PrintContacts.view {
    protected final String DEFAULT_TOKEN = "default_token";

    @Override
    public void onCreate() {
        super.onCreate();
        mPresenter.getPrintList(Param.Keys.TOKEN, getToken());
    }

    @Override
    public void quitUser() {
        SPUtil.getInstance().clear();
        startActivity(new Intent(this, RouterActivity.class));
    }

    @Override
    protected PrintPresenter onCtreatPresenter() {
        return new PrintPresenter(this);
    }

    @Override
    public void onPrintResult(List<Printbean> list) {
        SPUtil.getInstance().putString(Param.Keys.PRINT, JSONManager.getInstance().toJson(list));
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showToast(int res) {

    }

    @Override
    public void dismissLoadingDiaog() {

    }

    @Override
    public MvpDialog onCreatCustomDialog() {
        return null;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public String getToken() {
        return getUser().getToken();
    }

    public User getUser() {
        if (TextUtils.isEmpty(SPUtil.getInstance().getString(Param.Keys.USER))) {
            User user = new User();
            user.setToken(DEFAULT_TOKEN);
            return user;
        }
        String userjson = SPUtil.getInstance().getString(Param.Keys.USER);
        return (User) JSONManager.getInstance().parseObject(userjson, User.class);
    }
}
