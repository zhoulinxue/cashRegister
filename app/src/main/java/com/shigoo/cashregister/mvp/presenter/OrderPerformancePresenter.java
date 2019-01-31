package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.OrderPerformaceContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.OrderPerformanceDetailbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

public class OrderPerformancePresenter extends BasePresenterImpl<OrderPerformaceContact.view> implements OrderPerformaceContact.presenter {
    public OrderPerformancePresenter(OrderPerformaceContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<OrderPerformancebean>> mOrderPerformaceCallback = new NetRequestCallBack<ListData<OrderPerformancebean>>() {
        @Override
        public void onSuccess(ListData<OrderPerformancebean> sellerbeans) {
            mView.dismissLoadingDiaog();
            mView.onOrderPerformanceListResult(sellerbeans.getData());
            mView.onOrderCount(sellerbeans.getCount_dish_qty() + "", sellerbeans.getCount_finally_price() + "");
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<ListData<OrderPerformanceDetailbean>> mOrderPerformaceDetailCallback = new NetRequestCallBack<ListData<OrderPerformanceDetailbean>>() {
        @Override
        public void onSuccess(ListData<OrderPerformanceDetailbean> sellerbeans) {
            mView.dismissLoadingDiaog();
            mView.onOrderPerformanceDetail(sellerbeans.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void getOrderPerformanceList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getOrderPerformace(params, mOrderPerformaceCallback);
        addRequest(request);
    }

    @Override
    public void getOrderPerformanceDetail(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getOrderPerformaceDetail(params, mOrderPerformaceDetailCallback);
        addRequest(request);
    }
}
