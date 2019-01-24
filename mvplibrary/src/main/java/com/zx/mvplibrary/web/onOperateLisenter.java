package com.zx.mvplibrary.web;

import io.starteos.dappsdk.Request;

/**
 * Name: onOperateLisenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 17:59
 */
public interface onOperateLisenter {
    public void initWebview(Request request);

    public void getTableInfo(Request request);

    public void operateHandle(Request request);

    public void searchOperate(Request request);

    public void currentPage(Request request);

    public void orderDetailsData(Request request);

    public void handoverPrint(Request request);

    public void handDutyHistroyListPrint(Request request);
}
