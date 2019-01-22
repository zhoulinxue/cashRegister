package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.MenuDishesListContact;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

/**
 * Name: TablePresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-11 13:18
 */
public class OrderDishesPresenter extends BasePresenterImpl<MenuDishesListContact.view> implements MenuDishesListContact.presenter {

    NetRequestCallBack<SettalOrderResultbean> mMenuDishesListCallback = new NetRequestCallBack<SettalOrderResultbean>() {
        @Override
        public void onSuccess(SettalOrderResultbean listData) {
            mView.onOrderDishesListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mAddDishesCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String msg) {
            mView.onAddResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mDeleteDishesCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String msg) {
            mView.dismissLoadingDiaog();
            mView.onAddResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mSettleOrderDishesCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String msg) {
            mView.dismissLoadingDiaog();
            mView.onSettleResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mUpdateNumberCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String msg) {
            mView.dismissLoadingDiaog();
            mView.onUpdateNumResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<OrderPayStatusbean> mPaymentTypeCallback = new NetRequestCallBack<OrderPayStatusbean>() {

        @Override
        public void onSuccess(OrderPayStatusbean member) {
            mView.onPayResult(member);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };


    public OrderDishesPresenter(MenuDishesListContact.view view) {
        super(view);
    }

    @Override
    public void getOrderDishes(String... params) {
        NetRequest request = ApiManager.getInstance().getOrderDishesList(params, mMenuDishesListCallback);
        addRequest(request);
    }

    @Override
    public void deleteAllDishes(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().deleteDishes(params, mDeleteDishesCallback);
        addRequest(request);
    }

    @Override
    public void settleOrder(SettalOrderbean settleOrder) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().setSettleOrder(settleOrder, mSettleOrderDishesCallback);
        addRequest(request);
    }

    @Override
    public void updateBillbeanNum(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().updateGuestNum(params, mUpdateNumberCallback);
        addRequest(request);
    }

    @Override
    public void getPayList(String... params) {
        NetRequest request = ApiManager.getInstance().getPayList(params, mPaymentTypeCallback);
        addRequest(request);
    }


    @Override
    public void OrderDishesAdd(String... params) {
        NetRequest request = ApiManager.getInstance().addOrderDishes(params, mAddDishesCallback);
        addRequest(request);
    }
}
