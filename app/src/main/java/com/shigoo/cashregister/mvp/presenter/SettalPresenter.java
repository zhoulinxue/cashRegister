package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SettalContact;
import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Paymentbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class SettalPresenter extends BasePresenterImpl<SettalContact.view> implements SettalContact.presenter {
    public SettalPresenter(SettalContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<Favorablebean>> mFavorableListCallback = new NetRequestCallBack<List<Favorablebean>>() {

        @Override
        public void onSuccess(List<Favorablebean> member) {
            mView.onFavourableListResult(member);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<List<PayTypebean>> mPaymentTypeListCallback = new NetRequestCallBack<List<PayTypebean>>() {

        @Override
        public void onSuccess(List<PayTypebean> member) {
            mView.onPayTypeResult(member);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mPayOrderCallback = new NetRequestCallBack<String>() {

        @Override
        public void onSuccess(String msg) {
            mView.dismissLoadingDiaog();
            mView.onPayResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getFavourableList(String... params) {
        NetRequest request = ApiManager.getInstance().getFavorableList(params, mFavorableListCallback);
        addRequest(request);
    }

    @Override
    public void getPayTypeList(String... params) {
        NetRequest request = ApiManager.getInstance().getPaymentType(params, mPaymentTypeListCallback);
        addRequest(request);
    }

    @Override
    public void payOrder(Paymentbean mAddPayment) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().payOrder(mAddPayment, mPayOrderCallback);
        addRequest(request);
    }
}
