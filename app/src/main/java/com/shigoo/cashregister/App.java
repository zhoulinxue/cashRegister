package com.shigoo.cashregister;

import android.app.Activity;
import android.content.Intent;

import com.shigoo.cashregister.activitys.RouterActivity;
import com.shigoo.cashregister.print.PrintManager;
import com.shigoo.cashregister.services.PrintServices;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.MvpApplication;
import com.zx.network.Param;

/**
 * Name: App
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:27
 */
public class App extends MvpApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        PrintManager.getInstance().initPrint(this);
        startPrintService();
    }

    private void startPrintService() {
        startService(new Intent(this, PrintServices.class));
    }

    @Override
    public String getApiHost() {
        AppLog.print("BASE_URL  " + BuildConfig.API_HOST);
        return BuildConfig.API_HOST;
    }

    @Override
    public Class<? extends Activity> getLoginClass() {
        return RouterActivity.class;
    }
}
