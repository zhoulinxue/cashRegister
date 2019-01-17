package com.zx.api.api.pictrue;

import android.content.Context;
import android.view.View;

/**
 * Name: PictrueLoader
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:30
 */
public interface PictrueLoader<T extends PictrueContainer> {
    public void init(Context context);

    public T getContainer(View view);

    public void loadImage(Context context, String url, T container);

    public void loadImage(Context context, String url, T container, ImageLoadCallback callback);

    public void loadImage(Context context, String url, T container, int width, int height, int placeHolder, int errorHolder, ImageLoadCallback callback);
}
