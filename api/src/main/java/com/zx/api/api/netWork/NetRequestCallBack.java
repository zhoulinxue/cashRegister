package com.zx.api.api.netWork;

/**
 * Copyright (C),zhx_2018
 * FileName: NetRequestCallBack
 * Author: zhx
 * Date: 2018\10\31 0031 17:29
 * Description: ${DESCRIPTION}
 */
public interface NetRequestCallBack<T> {
    public  void onSuccess(T t);

    public  void onError(int responseCode, String msg);

}
