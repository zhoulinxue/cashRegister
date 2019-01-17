package com.zx.api.api.pictrue;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Name: ImageLoadCallback
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:48
 */
public interface ImageLoadCallback<T> {
    public void onSuc(T t);

    public void onError(T t);
}
