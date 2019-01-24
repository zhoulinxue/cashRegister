package com.shigoo.cashregister.print.attr;

import android.content.Context;

import com.shigoo.cashregister.print.utils.SharePreferenceUtils;

import java.util.List;

public class PrintConfig {
    final static String QrcodefilePath = "";
    private SharePreferenceUtils mSharePreference;
    private List<String> mList;
    private String formatName = "PrintConfig";

    public PrintConfig(Context context) {
        mSharePreference = new SharePreferenceUtils(context, formatName);

    }

    public void clearFormat() {
        mSharePreference.clear();
        mSharePreference.commit();
    }

    public void removeFormat(ESC_SYTLE.CONFIG_PRINT key) {
        mSharePreference.remove("" + key);
        mSharePreference.commit();
    }

    public String getParameter(ESC_SYTLE.CONFIG_PRINT key) {
        String value = mSharePreference.getString("" + key, "");
        return value;
    }

    public void setParameter(ESC_SYTLE.CONFIG_PRINT key, String value) {
        mSharePreference.put("" + key, value);
        mSharePreference.commit();

    }

    public List<String> getKeyList() {
        mList = (List<String>) mSharePreference.getObjectValue(formatName);
        return mList;
    }
}
