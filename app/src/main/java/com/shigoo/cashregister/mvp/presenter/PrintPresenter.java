package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintContacts;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.Printbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

public class PrintPresenter extends BasePresenterImpl<PrintContacts.view> implements PrintContacts.printPresenter {
    public PrintPresenter(PrintContacts.view view) {
        super(view);
    }
    NetRequestCallBack<ListData<Printbean>> mPrintCallback = new NetRequestCallBack<ListData<Printbean>>() {
        @Override
        public void onSuccess(ListData<Printbean> sellerbeans) {
            mView.onPrintResult(sellerbeans.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    @Override
    public void getPrintList(String... params) {
        NetRequest request = ApiManager.getInstance().getPrintList(params,mPrintCallback );
        addRequest(request);
    }
}
