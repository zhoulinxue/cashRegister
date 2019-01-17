package com.zx.mvplibrary;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.zx.api.api.utils.AppUtil;
import com.zx.network.OKHttp.ApiManager;
import com.zx.pictruemodel.ImageLoader;
import com.zx.pictruemodel.glide.BaseGlideLoader;

/**
 * Copyright (C),zhx_2018
 * FileName: MvpApplication
 * Author: zhx
 * Date: 2018\10\31 0031 16:00
 * Description: ${DESCRIPTION}
 */
public abstract class MvpApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.init(this);
        ImageLoader.init(new BaseGlideLoader());
        ApiManager.init(this, getApiHost());
    }

    public abstract String getApiHost();

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
}
