package com.zx.mvplibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.zx.api.api.utils.LangUtils;
import com.zx.api.api.utils.SPUtil;

/**
 * Name: InternationalizationActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-14 14:15
 */
public class InternationalizationActivity extends AppCompatActivity {
    protected final String KEY_LANGUAGE = "language";

    /**
     * 获取当前 语言选择  默认为简体中文
     * @return
     */
    protected int getCurrentLan() {
        return Integer.valueOf(SPUtil.getInstance().getInt(KEY_LANGUAGE, LangUtils.SIMPLE_CHINESE) + "");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, getCurrentLan()));
    }

    protected void setLanguage(int language) {
        SPUtil.getInstance().put(KEY_LANGUAGE, language);
        recreate();
    }
}
