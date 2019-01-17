package com.zx.pictruemodel;

import android.content.Context;
import android.view.View;

import com.zx.api.api.pictrue.ImageLoadCallback;
import com.zx.api.api.pictrue.LoaderBuilder;
import com.zx.api.api.pictrue.PictrueLoader;
import com.zx.api.api.utils.AppLog;

/**
 * Name: ImageLoader
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 11:39
 */
public class ImageLoader {
    private static final String TAG=ImageLoader.class.getSimpleName();
    private static PictrueLoader mLoader;
    private static ImageLoader mLoaderManager;

    public static void init(PictrueLoader loader) {
        if (mLoaderManager == null) {
            mLoaderManager = new ImageLoader(loader);
        }
    }

    public ImageLoader(PictrueLoader loader) {
        mLoader = loader;
    }

    public static LoaderBuilder with(Context context) {
        return new ImageLoader.Builder(context, mLoader);
    }

    public static class Builder implements LoaderBuilder {
        private Context mContext;
        private PictrueLoader mLoader;
        private int mHolderImage;
        private int mErrorImage;
        private String mUrl;
        private View mContainer;
        private int mWidth, mHeight;

        public Builder(Context mContext, PictrueLoader mLoader) {
            this.mContext = mContext;
            this.mLoader = mLoader;
        }

        @Override
        public LoaderBuilder placeHolder(int rec) {
            mHolderImage = rec;
            return this;
        }

        @Override
        public LoaderBuilder onError(int rec) {
            mErrorImage = rec;
            return this;
        }

        @Override
        public LoaderBuilder from(String url) {
            this.mUrl = url;
            return this;
        }

        @Override
        public LoaderBuilder override(int width, int height) {
            this.mWidth = width;
            this.mHeight = height;
            return this;
        }

        @Override
        public void into(View container) {
            this.mContainer = container;
            loadImage(mContext, mUrl, mContainer);
        }

        @Override
        public void into(ImageLoadCallback callback) {
                loadImage(mContext, mUrl, null, callback);
        }

        private void loadImage(Context context, String url, View container) {
                loadImage(context, url, container, null);
        }

        private void loadImage(Context context, String url, View container, ImageLoadCallback callback) {
            if (mLoader != null) {
                mLoader.loadImage(context, url, mLoader.getContainer(container), mWidth, mHeight, mHolderImage, mErrorImage, callback);
            }else {
                AppLog.print(TAG+"mLoader==null");
            }
        }

    }
}
