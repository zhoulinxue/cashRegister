package com.zx.mvplibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.User;
import com.zx.api.api.app.MvpDialog;
import com.zx.api.api.mvp.BaseView;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.wedgit.SystemDialog;
import com.zx.network.Param;

public class BaseViewImpl implements BaseView {
    private MvpDialog mDialog;
    private Context mContext;
    protected Handler mHandler;
    protected final String DEFAULT_TOKEN = "default_token";

    public BaseViewImpl(Context mContext, Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
        mDialog = onCreatCustomDialog();
    }

    @Override
    public void showLoadingDialog() {
        if (mDialog != null)
            mDialog.show();
    }

    @Override
    public void showToast(String msg) {
        mHandler.post(new ToastRunable(mContext, msg));
    }

    @Override
    public void showToast(int res) {
        if (res != 0)
            showToast(mContext.getResources().getString(res));
    }

    @Override
    public void dismissLoadingDiaog() {
        if (mDialog != null) {
            mDialog.dissmiss();
        }
    }

    @Override
    public MvpDialog onCreatCustomDialog() {
        if (mContext instanceof Activity) {
            return new ImmersionBarDialog((Activity) mContext, R.string.loading_text);
        } else {
            return new SystemDialog(mContext, R.string.loading_text);
        }
    }

    @Override
    public void onSuccess(Object object) {

    }

    protected boolean isNetWorkconnected() {
        return !TextUtils.isEmpty(AppUtil.getNetworkState(mContext));
    }

    @Override
    public void onError(String msg) {
        if (!isNetWorkconnected()) {
            showToast("网络连接已断开");
            return;
        }
        showToast(msg);
    }

    @Override
    public void onError(int code, String msg) {
        onError(msg);
        if (code == 300) {
            if ("登录已过期".equals(msg)) {
                MvpApplication.getInstance().quitUser();
            }
        }
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

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public String getToken() {
        return getUser().getToken();
    }
}
