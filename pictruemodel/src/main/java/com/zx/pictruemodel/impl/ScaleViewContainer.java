package com.zx.pictruemodel.impl;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.zx.pictruemodel.BitmapUtil;

/**
 * Name: ScaleViewContainer
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 14:51
 */
public class ScaleViewContainer extends BaseContainer<SubsamplingScaleImageView> {

    public ScaleViewContainer(SubsamplingScaleImageView mContainer) {
        super(mContainer);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        mContainer.setImage(ImageSource.bitmap(bitmap));
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        mContainer.setImage(ImageSource.bitmap(BitmapUtil.drawableToBitmap(drawable)));
    }

    @Override
    public void setImageRec(int imageRec) {
        mContainer.setImage(ImageSource.resource(imageRec));
    }
}
