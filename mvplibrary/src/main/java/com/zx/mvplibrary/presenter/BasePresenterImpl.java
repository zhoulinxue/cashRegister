package com.zx.mvplibrary.presenter;

import com.zx.api.api.mvp.BaseView;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: BasePresenterImpl
 * Author: zhx
 * Date: 2018\10\23 0023 19:41
 * Description: mvp presenter 基类 主要实现 网络请求的 添加及移除逻辑
 */
public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    private final String TAG=BasePresenterImpl.class.getSimpleName();
    List<NetRequest> netRequests = new ArrayList<>();
    protected T mView;

    public BasePresenterImpl(T view) {
        this.mView = view;
    }

    @Override
    public void destory() {
        for (NetRequest netRequest : netRequests) {
            remove(netRequest);
        }
        netRequests.clear();
    }

    protected void addRequest(NetRequest request) {
        if (request != null) {
            netRequests.add(request);
        }else {
            AppLog.print(TAG+"NetRequest...  can not be null ");
        }
    }

    @Override
    public void remove(NetRequest netRequest) {
        if (netRequest != null && !netRequest.isDestoryed()) {
            netRequest.cancel();
        }
    }
}
