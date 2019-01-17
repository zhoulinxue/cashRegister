package com.shigoo.cashregister.wedgit;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

/**
 * Name: HidKeyboadDialog
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-05 13:26
 */
public class HidKeyboadDialog extends Dialog {
    private InputMethodManager mInputMethodManager;

    public HidKeyboadDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected void hideInputMethod() {
        if (getWindow().getDecorView() != null && this.mInputMethodManager != null) {
            this.mInputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public HidKeyboadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected HidKeyboadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        hideInputMethod();
        return super.onTouchEvent(event);
    }
}
