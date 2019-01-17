package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SaleOutMainListContact;
import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

/**
 * Name: TablePresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-11 13:18
 */
public class SaleOutMainListPresenter extends BasePresenterImpl<SaleOutMainListContact.view> implements SaleOutMainListContact.presenter {

    NetRequestCallBack<List<Dishesbean>> mMenuDishesListCallback = new NetRequestCallBack<List<Dishesbean>>() {
        @Override
        public void onSuccess(List<Dishesbean> listData) {
            mView.onDishesListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<List<DishesTypebean>> mTypeListCallBack = new NetRequestCallBack<List<DishesTypebean>>() {
        @Override
        public void onSuccess(List<DishesTypebean> listData) {
            mView.onDishesTypeListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    public SaleOutMainListPresenter(SaleOutMainListContact.view view) {
        super(view);
    }

    NetRequestCallBack<Integer> mSaleOutCallBack = new NetRequestCallBack<Integer>() {
        @Override
        public void onSuccess(Integer msg) {
            mView.dismissLoadingDiaog();
            mView.onSaleOutResult(msg);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getDishesList(String... params) {
        NetRequest request = ApiManager.getInstance().getDishesList(params, mMenuDishesListCallback);
        addRequest(request);
    }

    @Override
    public void getDishesTypeList(String... params) {
        NetRequest request = ApiManager.getInstance().getDishesTypeList(params, mTypeListCallBack);
        addRequest(request);
    }

    @Override
    public void saleOut(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().saleOut(params, mSaleOutCallBack);
        addRequest(request);
    }

}
