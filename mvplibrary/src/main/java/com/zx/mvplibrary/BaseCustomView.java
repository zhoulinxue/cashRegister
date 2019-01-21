package com.zx.mvplibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zx.api.api.utils.AppUtil;

import butterknife.ButterKnife;

/**
 * Name: BaseCustomView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 13:28
 */
public abstract class BaseCustomView implements ICustomeView {
    private View rootView;
    protected Context mContext;
    private ViewGroup rootGroup;
    protected final int DEFAULT_MINUTES = 1000;

    public BaseCustomView(Context context, final ViewGroup rootGroup) {
        this.rootGroup = rootGroup;
        this.mContext = context;
        rootView= LayoutInflater.from(mContext).inflate(initLayout(), rootGroup);
        ButterKnife.bind(this, rootView);
        initView(context, rootView);
    }

    public BaseCustomView(Context context) {
        this(context, null);
    }

    protected abstract void initView(Context context, View rootView);


    @Override
    public View getView() {
        return rootView;
    }

    @Override
    public void gone() {
        getView().setVisibility(View.GONE);
    }

    @Override
    public void visiable() {
        getView().setVisibility(View.VISIBLE);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 单次点击事件
     *
     * @param v
     * @param listener
     */
    public void singleClickOnMinutes(View v, View.OnClickListener listener) {
        AppUtil.registerClick(DEFAULT_MINUTES, v, listener);
    }
}
