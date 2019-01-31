package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.OrderPayDetailContact;
import com.xgsb.datafactory.bean.OrderPayDetailbean;
import com.xgsb.datafactory.bean.User;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenter;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

public class OrderPayDetailPresenter extends BasePresenterImpl<OrderPayDetailContact.view> implements OrderPayDetailContact.presenter {
    public OrderPayDetailPresenter(OrderPayDetailContact.view view) {
        super(view);
    }

    NetRequestCallBack<OrderPayDetailbean> mOrderPayResultCallBack = new NetRequestCallBack<OrderPayDetailbean>() {
        @Override
        public void onSuccess(OrderPayDetailbean user) {
            mView.dismissLoadingDiaog();
            mView.onPayDetailResualt(user);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void getOrderPayDetal(String... params) {
        NetRequest request = ApiManager.getInstance().getPayOrderDetail(params,mOrderPayResultCallBack);
        addRequest(request);
    }
}
