package com.zx.mvplibrary;

import android.content.Context;
import android.view.View;

/**
 * Name: ICustomeView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 13:25
 */
public interface ICustomeView {

    public int initLayout();

    public View getView();

    public void gone();

    public void visiable();

    public Context getContext();

}
