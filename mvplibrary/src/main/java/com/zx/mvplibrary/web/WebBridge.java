package com.zx.mvplibrary.web;

import android.support.v7.widget.RecyclerView;

import com.zx.api.api.utils.AppLog;

import io.starteos.dappsdk.DAppApi;
import io.starteos.dappsdk.DAppBridge;
import io.starteos.dappsdk.Request;
import io.starteos.dappsdk.annotation.Namespace;

@Namespace(WebNameSpace.SYSTEM)
public class WebBridge extends DAppApi implements onOperateLisenter{
    public WebBridge(DAppBridge bridge) {
        super(bridge);
    }

    @Override
    protected void onDestroy() {

    }

    /**
     * 获取当前 页面信息
     *
     * @param request
     */
    public void getTableInfo(final Request request) {
        AppLog.print("WebBridge  " + "getTableInfo");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).getTableInfo(request);
        }
    }

    public  void initWebview(final Request request){
        AppLog.print("WebBridge  " + "initWebview");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).initWebview(request);
        }
    }

    public void searchOperate(Request request) {
        AppLog.print("WebBridge  " + "searchOperate");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).searchOperate(request);
        }
    }

    @Override
    public void currentPage(Request request) {
        AppLog.print("WebBridge  " + "currentPage");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).currentPage(request);
        }
    }

    public void operateHandle(Request request) {
        AppLog.print("WebBridge  " + "operateHandle");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).onOperate(request);
        }
    }
    public void orderDetailsData(Request request){
        AppLog.print("WebBridge  " + "orderDetailsData");
        if (bridge instanceof XgDAppBridge) {
            ((XgDAppBridge) bridge).orderDetailsData(request);
        }
    }
}
