package com.zx.api.api.pictrue;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Name: PictrueContainer
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:33
 */
public interface PictrueContainer{
    public void setImageBitmap(Bitmap bitmap);

    public void setImageDrawable(Drawable drawable);

    public void setImageRec(int imageRec);

    public View getView();
}
