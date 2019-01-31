package com.zx.mvplibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zx.mvplibrary.presenter.BasePresenter;

public abstract class BaseMvpService<P extends BasePresenter> extends Service {
    protected P mPresenter;
    public static String mAction = "re_login";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && mAction.equals(intent.getAction())) {
            quitUser();
        }
        return START_STICKY;
    }

    public abstract void quitUser();

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destory();
        }
    }
}
