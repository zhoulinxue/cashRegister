package com.zx.api.api.pictrue;

import android.view.View;

/**
 * Name: LoaderBuilder
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 13:24
 */
public interface LoaderBuilder {
    public LoaderBuilder placeHolder(int rec);

    public LoaderBuilder onError(int rec);

    public LoaderBuilder from(String url);

    public LoaderBuilder override(int width, int height);

    public void into(View container);

    public void into(ImageLoadCallback callback);

}
