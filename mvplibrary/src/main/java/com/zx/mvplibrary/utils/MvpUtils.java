package com.zx.mvplibrary.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: MvpUtils
 * Author: zhx
 * Date: 2018\10\23 0023 20:51
 * Description: ${DESCRIPTION}
 */
public class MvpUtils {
    public static boolean isAppRunningForeground(Context var0) {
        ActivityManager var1 = (ActivityManager) var0.getSystemService(Context.ACTIVITY_SERVICE);
        List var2 = var1.getRunningTasks(1);
        boolean var3 = var0.getPackageName().equalsIgnoreCase(((ActivityManager.RunningTaskInfo)
                var2.get(0)).baseActivity.getPackageName());
        return var3;
    }
}
