package com.zx.pictruemodel.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.zx.api.api.pictrue.PictrueContainer;

/**
 * Name: BaseContainer
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 14:54
 */
public abstract class BaseContainer<T extends View> implements PictrueContainer {
    protected T mContainer;

    public BaseContainer(T mContainer) {
        this.mContainer = mContainer;
    }

    @Override
    public View getView() {
        return mContainer;
    }

}
