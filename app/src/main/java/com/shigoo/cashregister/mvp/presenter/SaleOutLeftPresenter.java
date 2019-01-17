package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SaleOutContact;
import com.xgsb.datafactory.bean.SaleOutbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class SaleOutLeftPresenter extends BasePresenterImpl<SaleOutContact.view> implements SaleOutContact.presenter {
    public SaleOutLeftPresenter(SaleOutContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<SaleOutbean>> mSaleOutListCallBack = new NetRequestCallBack<List<SaleOutbean>>() {
        @Override
        public void onSuccess(List<SaleOutbean> listData) {
            mView.dismissLoadingDiaog();
            mView.onSaleDishesList(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mUpdateSaleOutCallBack = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onUpdateResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mDeleteCallBack = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onDeleteResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<String> mDeleteAllCallBack = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onClean(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getSaleOutList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getSaleOutList(params, mSaleOutListCallBack);
        addRequest(request);
    }

    @Override
    public void updateSaleOut(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().updateSaleOutList(params, mUpdateSaleOutCallBack);
        addRequest(request);
    }

    @Override
    public void deleteSaleOut(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().deleteSaleOut(params, mDeleteCallBack);
        addRequest(request);
    }

    @Override
    public void deleteAllSaleOut(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().deleteAll(params, mDeleteAllCallBack);
        addRequest(request);
    }
}
