package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.FanJZContact;
import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.FanJZbean;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class FanJZPresenter extends BasePresenterImpl<FanJZContact.view> implements FanJZContact.presenter {
    public FanJZPresenter(FanJZContact.view view) {
        super(view);
    }

    @Override
    public void FJZOrder(FanJZbean fanJZbean) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().fanJzOrder(fanJZbean, mFanjzCallback);
        addRequest(request);

    }

    NetRequestCallBack<SettalOrderResultbean> mMenuDishesListCallback = new NetRequestCallBack<SettalOrderResultbean>() {
        @Override
        public void onSuccess(SettalOrderResultbean listData) {
            mView.onOrderDishesListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<List<Remarkbean>> mFanjzResonCallback = new NetRequestCallBack<List<Remarkbean>>() {
        @Override
        public void onSuccess(List<Remarkbean> listData) {
            mView.onReasonlResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<String> mFanjzCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onFJZOrderResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void getFJZReasonList(String... params) {
        NetRequest request = ApiManager.getInstance().getRemarkList(params, mFanjzResonCallback);
        addRequest(request);
    }

    @Override
    public void getPayStatus(String... params) {
        NetRequest request = ApiManager.getInstance().getPayList(params, mPaymentTypeCallback);
        addRequest(request);
    }
    NetRequestCallBack<OrderPayStatusbean> mPaymentTypeCallback = new NetRequestCallBack<OrderPayStatusbean>() {

        @Override
        public void onSuccess(OrderPayStatusbean member) {
            mView.onPayResult(member);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
}
