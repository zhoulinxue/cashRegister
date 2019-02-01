package com.zx.api.api.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.rmondjone.locktableview.DisplayUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Name: AppUtil
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-20 11:05
 */
public class AppUtil {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private static final String TAG = "KSystemUtils";
    private static int SCREEN_WIDTH = 480;
    private static int SCREEN_HEIGHT = 800;
    private static boolean isInited = false;
    private static float DENSITY = 1;
    private static int sNavigationBarHeight = Integer.MIN_VALUE;
    public static String WIFI = "wifi";

    private AppUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        AppUtil.context = context.getApplicationContext();
        initDisplayOpinion();
    }

    private static void initDisplayOpinion() {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(context, dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(context, dm.heightPixels);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static void registerClick(int timeminutes, final View v, final View.OnClickListener listener) {
        RxView.clicks(v)
                .throttleFirst(timeminutes, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
                        listener.onClick(v);
                    }
                });
    }

    public static void initSysSettings(Activity activity) {
        DisplayMetrics dm = getDM(activity);
        if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            SCREEN_WIDTH = dm.heightPixels;
            SCREEN_HEIGHT = dm.widthPixels;
        } else {
            SCREEN_WIDTH = dm.widthPixels;
            SCREEN_HEIGHT = dm.heightPixels;
        }
        DENSITY = dm.density;
        isInited = true;
    }

    public static DisplayMetrics getDM(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static float getDesity(Activity activity) {
        if (!isInited && activity != null) {
            initSysSettings(activity);
        }
        return DENSITY;
    }

    public static int getScreenWidth(Activity activity) {
        if (!isInited && activity != null) {
            initSysSettings(activity);
        }
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(Activity activity) {
        if (!isInited && activity != null) {
            initSysSettings(activity);
        }
        return SCREEN_HEIGHT;
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 由于线程竞争或进程竞争， 某些版本的android系统上 context.getFilesDir() 创建目录会失败，并返回null，
     * 为解决该问题， 当发现返回值为null时，try again。
     *
     * @param context
     * @return files directory
     */
    public static File getFilesDir(Context context) {
        File filesDir = context.getFilesDir();
        return filesDir != null ? filesDir : context.getFilesDir();
    }

    public static final String getPhoneModelWithManufacturer() {
        return Build.MANUFACTURER + " " + android.os.Build.MODEL;
    }

    public static final String getOsInfo() {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getPhoneId(Context context) {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        return id;
    }

    public static BigDecimal getFloatFromString(String bill_cost) {
        BigDecimal decimal = null;
        if (TextUtils.isEmpty(bill_cost)) {
            decimal = new BigDecimal("0");
        } else {
            decimal = new BigDecimal(bill_cost);
        }
        return decimal;
    }

    public static boolean isOrderDishes(Context context) {
        if ("com.shigoo.orderdishes".equals(((Activity) context).getApplication().getPackageName())) {
            return true;
        } else {
            return false;
        }
    }

    public static String mixPhone(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 一张SD卡的相关信息
     *
     * @author caisenchuan
     */
    public static class SDCard {
        private String path;
        private boolean mount;
        private boolean removeable;

        public SDCard(int index, String path, boolean mount, boolean removeable) {
            this.path = path;
            this.mount = mount;
            this.removeable = removeable;
        }

        /**
         * @return the path
         */
        public String getPath() {
            return path;
        }

        /**
         * @return the mount
         */
        public boolean isMount() {
            return mount;
        }

        /**
         * @return the removeable
         */
        public boolean isRemoveable() {
            return removeable;
        }
    }


    private static final String SCHEME = "package";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.1及之前版本)
     */
    private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
    /**
     * 调用系统InstalledAppDetails界面所需的Extra名称(用于Android 2.2)
     */
    private static final String APP_PKG_NAME_22 = "pkg";

    private static final String ACTION_APPLICATION_DETAILS_SETTINGS_23 = "android.settings.APPLICATION_DETAILS_SETTINGS";
    /**
     * InstalledAppDetails所在包名
     */
    private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
    /**
     * InstalledAppDetails类名
     */
    private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

    /**
     * 调用系统InstalledAppDetails界面显示已安装应用程序的详细信息。 对于Android 2.3（Api Level
     * 9）以上，使用SDK提供的接口； 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）。
     *
     * @param packageName 应用程序的包名
     */
    public static Intent getPackageDetailsIntent(String packageName) {
        Intent intent = new Intent();
        int apiLevel = 0;
        try {
            apiLevel = Build.VERSION.SDK_INT;
        } catch (Exception ex) {
        }
        if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(ACTION_APPLICATION_DETAILS_SETTINGS_23);
            Uri uri = Uri.fromParts(SCHEME, packageName, null);
            intent.setData(uri);
        } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
            final String appPkgName = (apiLevel > 7 ? APP_PKG_NAME_22 : APP_PKG_NAME_21);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
            intent.putExtra(appPkgName, packageName);
        }
        return intent;
    }

    /**
     * 清除所有通知
     *
     * @author caisenchuan
     */
    public static void clearAllNotification(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }


    // 判断APK是否安装
    // *** 如果安装了，但是已经被系统停用，也被认为是没有安装 ***
    public static boolean isAPPInstalled(Context context, String pkg_name) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            PackageInfo info = pm.getPackageInfo(pkg_name, PackageManager.GET_ACTIVITIES);
            if (info != null)
                installed = info.applicationInfo.enabled;

        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static boolean isAPPInstalled(Context context, String[] pkg_name) {
        for (String pkg : pkg_name) {
            if (isAPPInstalled(context, pkg)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isSdkVersionJellyBean() {
        return getSdkVersion() >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * @Note: 获取状态栏的高度
     */
    public static int getStatusBarHeight(Activity activity) {
        int height = 0;
        do {
            if (activity == null)
                break;
            if (activity.getWindow() == null)
                break;
            if (activity.getWindow().getDecorView() == null)
                break;
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            height = rect.top;
        } while (false);

        return height;
    }


    /**
     * 获取当前网络连接的类型
     *
     * @param context context
     * @return int
     */
    public static String getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); // 获取网络服务
        if (null == connManager) { // 为空则认为无网络
            return "";
        }
        // 获取网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return "";
        }
        // 判断是否为WIFI
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state) {
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return WIFI;
                }
            }
        }
        // 若不是WIFI，则去判断是2G、3G、4G网
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            /*
             GPRS : 2G(2.5) General Packet Radia Service 114kbps
             EDGE : 2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
             UMTS : 3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
             CDMA : 2G 电信 Code Division Multiple Access 码分多址
             EVDO_0 : 3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
             EVDO_A : 3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
             1xRTT : 2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡,
             HSDPA : 3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
             HSUPA : 3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
             HSPA : 3G (分HSDPA,HSUPA) High Speed Packet Access
             IDEN : 2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
             EVDO_B : 3G EV-DO Rev.B 14.7Mbps 下行 3.5G
             LTE : 4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
             EHRPD : 3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
             HSPAP : 3G HSPAP 比 HSDPA 快些
             */
            // 2G网络
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            // 3G网络
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            // 4G网络
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return "mobile";
        }
    }

}
