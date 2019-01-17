package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.TableDialogContact;
import com.xgsb.datafactory.bean.Chedanbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class TableDialogPresenter extends BasePresenterImpl<TableDialogContact.view> implements TableDialogContact.presenter {
    public TableDialogPresenter(TableDialogContact.view view) {
        super(view);
    }

    @Override
    public void cancelOrder(Chedanbean chedanbean) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().cancelOrder(chedanbean, mCancelOrderCallback);
        addRequest(request);
    }

    @Override
    public void getCancelList(String... params) {
        NetRequest request = ApiManager.getInstance().cancelOrderList(params, mCancelResonCallback);
        addRequest(request);
    }

    NetRequestCallBack<String> mCancelOrderCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onCancelOrderResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<List<String>> mCancelResonCallback = new NetRequestCallBack<List<String>>() {
        @Override
        public void onSuccess(List<String> listData) {
            mView.onCancelResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
}
