package com.zx.mvplibrary.web;

import android.content.Context;
import android.os.Handler;
import android.webkit.WebView;

import io.starteos.dappsdk.DAppBridge;
import io.starteos.dappsdk.Request;

/**
 * Name: XgDAppBridge
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 17:58
 */
public class XgDAppBridge extends DAppBridge {
    private onOperateLisenter mLisenter;
    private Handler mHandler;

    public XgDAppBridge(Context context, WebView webView) {
        super(context, webView);
    }

    public XgDAppBridge(Context context, WebView webView, onOperateLisenter mLisenter, Handler handler) {
        super(context, webView);
        this.mLisenter = mLisenter;
        this.mHandler = handler;
    }

    public void getTableInfo(final Request request) {
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.getTableInfo(request);
                }
            });
        }
    }

    public void initWebview(final Request request) {
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.initWebview(request);
                }
            });
        }
    }


    public void onOperate(final Request request) {
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.operateHandle(request);
                }
            });
        }
    }

    public void searchOperate(final Request request) {
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.searchOperate(request);
                }
            });
        }
    }
    public void orderDetailsData(final Request request){
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.orderDetailsData(request);
                }
            });
        }
    }
    public void currentPage(final Request request) {
        if (mLisenter != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mLisenter.currentPage(request);
                }
            });
        }
    }

    public void setLisenter(onOperateLisenter lisenter) {
        this.mLisenter = lisenter;
    }
}
