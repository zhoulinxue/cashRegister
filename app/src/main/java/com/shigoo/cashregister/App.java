package com.shigoo.cashregister;

import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
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
    public String getApiHost() {
        AppLog.print("BASE_URL  " + BuildConfig.API_HOST);
        return BuildConfig.API_HOST;
    }
}
