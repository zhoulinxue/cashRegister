package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.OrderPerformaceContact;
import com.xgsb.datafactory.bean.ListData;
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
            mView.onOrderPerformanceListResult(sellerbeans.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    @Override
    public void getOrderPerformanceList(String... params) {
        NetRequest request = ApiManager.getInstance().getOrderPerformace(params, mOrderPerformaceCallback);
        addRequest(request);
    }
}
