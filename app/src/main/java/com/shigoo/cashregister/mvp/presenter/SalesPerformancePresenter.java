package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SalesPerformanceContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.xgsb.datafactory.bean.Sellerbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class SalesPerformancePresenter extends BasePresenterImpl<SalesPerformanceContact.view> implements SalesPerformanceContact.presenter {

    public SalesPerformancePresenter(SalesPerformanceContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<SalePerformancebean>> mSalePerformaceCallback = new NetRequestCallBack<ListData<SalePerformancebean>>() {
        @Override
        public void onSuccess(ListData<SalePerformancebean> sellerbeans) {
            mView.onSalePerformanceListResult(sellerbeans.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    NetRequestCallBack<ListData<OrderPerformancebean>> mOrderPerformaceCallback = new NetRequestCallBack<ListData<OrderPerformancebean>>() {
        @Override
        public void onSuccess(ListData<OrderPerformancebean> sellerbeans) {
            mView.onOrderPerformanceListResult(sellerbeans.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    @Override
    public void getSalePerformanceList(String... params) {

    }

    @Override
    public void getOrderPerformanceList(String... params) {
        NetRequest request = ApiManager.getInstance().getOrderPerformace(params, mOrderPerformaceCallback);
        addRequest(request);
    }
}
