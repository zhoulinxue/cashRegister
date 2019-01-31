package com.shigoo.cashregister;

import android.content.Intent;
import android.printservice.PrintService;
import android.util.DisplayMetrics;

import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.rmondjone.locktableview.DisplayUtil;
import com.shigoo.cashregister.print.PrintManager;
import com.shigoo.cashregister.services.PrintServices;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpApplication;

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
        initDisplayOpinion();
    }

    private void startPrintService() {
        startService(new Intent(this, PrintServices.class));
    }

    @Override
    public String getApiHost() {
        AppLog.print("BASE_URL  " + BuildConfig.API_HOST);
        return BuildConfig.API_HOST;
    }
    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
}
