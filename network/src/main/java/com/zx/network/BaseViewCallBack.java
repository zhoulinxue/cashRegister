package com.zx.network;

import com.zx.api.api.mvp.BaseView;
import com.zx.api.api.netWork.NetRequestCallBack;

/**
 * Name: BaseViewCallBack
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-13 10:40
 */
public class BaseViewCallBack<T> implements NetRequestCallBack {
    private BaseView mBaseView;
    private String mRequestCode;

    public BaseViewCallBack(BaseView mBaseView, String mRequestCode) {
        this.mBaseView = mBaseView;
        this.mRequestCode = mRequestCode;
    }

    @Override
    public void onSuccess(Object o) {
        if(mBaseView!=null){
            mBaseView.onSuccess(o);
        }

    }

    @Override
    public void onError(int responseCode, String msg) {
        if(mBaseView!=null){
            mBaseView.onError(msg);
        }
    }
}
