package com.shigoo.cashregister.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shigoo.cashregister.customViews.CommonTipDialog;
import com.shigoo.cashregister.wedgit.HidKeyboadDialog;
import com.zx.api.api.utils.AppUtil;

/**
 * Name: DialogUtil
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-03 10:57
 */
public class DialogUtil {

    public static Dialog contentDialog(Activity context, final View view) {
        Dialog dialog = new HidKeyboadDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (AppUtil.getScreenHeight(context) * 0.65);
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        dialog.show();
        return dialog;
    }

    public static CommonTipDialog showDialogTwoButton(Activity context, String title, String str, String leftBtnName, DialogInterface.OnClickListener leftDialogInterface, String RightBtnName, DialogInterface.OnClickListener rightDialogInterface) {
        final CommonTipDialog.Builder builder = new CommonTipDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(str);
        builder.setLeftBtn(leftBtnName, leftDialogInterface);
        builder.setRightBtn(RightBtnName, rightDialogInterface);
        CommonTipDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }
}
