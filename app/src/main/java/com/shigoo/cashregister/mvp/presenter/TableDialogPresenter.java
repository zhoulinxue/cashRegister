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
    public void getMultReasonList(String... params) {

    }

    @Override
    public void chargePrice(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().changePrice(params, mRePriceCallback);
        addRequest(request);
    }

    @Override
    public void discountDishes(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().reDiscount(params, mDiscountPriceCallback);
        addRequest(request);
    }

    @Override
    public void returnDishes(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().returnDishes(params, mReturnDisheCallback);
        addRequest(request);
    }

    NetRequestCallBack<String> mDiscountPriceCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onDiscountResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<String> mRePriceCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onChargePriceResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<String> mReturnDisheCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String listData) {
            mView.dismissLoadingDiaog();
            mView.onReturnDishesResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

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

}