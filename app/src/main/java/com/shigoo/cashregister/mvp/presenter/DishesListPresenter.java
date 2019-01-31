package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.DishesListContact;
import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Remarkbean;
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
public class DishesListPresenter extends BasePresenterImpl<DishesListContact.view> implements DishesListContact.presenter {

    NetRequestCallBack<List<Dishesbean>> mMenuDishesListCallback = new NetRequestCallBack<List<Dishesbean>>() {
        @Override
        public void onSuccess(List<Dishesbean> listData) {
            mView.onDishesListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<List<DishesTypebean>> mTypeListCallBack = new NetRequestCallBack<List<DishesTypebean>>() {
        @Override
        public void onSuccess(List<DishesTypebean> listData) {
            mView.onDishesTypeListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<List<Remarkbean>> mRmarkCallBack = new NetRequestCallBack<List<Remarkbean>>() {
        @Override
        public void onSuccess(List<Remarkbean> listData) {
            mView.onRemarkListResult(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };

    public DishesListPresenter(DishesListContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<Dishesbean>> mSetMealDishesCallback = new NetRequestCallBack<List<Dishesbean>>() {
        @Override
        public void onSuccess(List<Dishesbean> setMealGroupbeans) {
            mView.dismissLoadingDiaog();
            mView.onSetMealDishesResult(setMealGroupbeans);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
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
    public void getRemarkList(String... params) {
        NetRequest request = ApiManager.getInstance().getRemarkList(params, mRmarkCallBack);
        addRequest(request);
    }

    @Override
    public void getSetMealDishes(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getSetMealDishes(params, mSetMealDishesCallback);
        addRequest(request);
    }
}
