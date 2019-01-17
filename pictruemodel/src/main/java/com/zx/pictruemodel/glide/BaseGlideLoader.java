package com.zx.pictruemodel.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.zx.api.api.pictrue.ImageLoadCallback;
import com.zx.api.api.pictrue.PictrueLoader;
import com.zx.pictruemodel.impl.BaseContainer;
import com.zx.pictruemodel.impl.ImageViewContainer;
import com.zx.pictruemodel.impl.ScaleViewContainer;

/**
 * Name: BaseGlideLoader
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:39
 */
public class BaseGlideLoader implements PictrueLoader<BaseContainer> {

    @Override
    public void init(Context context) {
        // 初始化配置 glide
    }

    @Override
    public BaseContainer getContainer(View view) {
        if (view instanceof SubsamplingScaleImageView) {
            return new ScaleViewContainer((SubsamplingScaleImageView) view);
        }
        return new ImageViewContainer((ImageView) view);
    }


    @Override
    public void loadImage(Context context, String url, BaseContainer container) {
        loadImage(context, url, container, null);
    }

    @Override
    public void loadImage(Context context, String url, final BaseContainer container, final ImageLoadCallback callback) {
        try {
            Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    if (callback != null) {
                        callback.onSuc(resource);
                    } else {
                        if (container != null) {
                            container.setImageDrawable(resource);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void loadImage(Context context, String url, final BaseContainer container, int width, int height, int placeHolder, int errorHolder, final ImageLoadCallback callback) {
        RequestOptions options = new RequestOptions();
        if (width != 0 && height != 0) {
            options.override(width, height);
        }
        if (placeHolder != 0) {
            options.placeholder(placeHolder);
        }
        if (errorHolder != 0) {
            options.error(errorHolder);
        }
        try {
            Glide.with(context).load(url).apply(options).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    if (callback != null) {
                        callback.onSuc(resource);
                    } else {
                        if (container != null) {
                            container.setImageDrawable(resource);
                        }
                    }
                }

                @Override
                public void onLoadFailed(@Nullable Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    if (errorDrawable != null && container != null) {
                        container.setImageDrawable(errorDrawable);
                    }
                    if (callback != null) {
                        callback.onError(errorDrawable);
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
