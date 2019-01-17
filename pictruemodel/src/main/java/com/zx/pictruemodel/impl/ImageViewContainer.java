package com.zx.pictruemodel.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Name: ImageViewContainer
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:52
 */
public class ImageViewContainer extends BaseContainer<ImageView> {

    public ImageViewContainer(ImageView mContainer) {
        super(mContainer);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mContainer.setImageBitmap(bitmap);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        mContainer.setImageDrawable(drawable);
    }

    @Override
    public void setImageRec(int imageRec) {
        mContainer.setImageResource(imageRec);
    }
}
