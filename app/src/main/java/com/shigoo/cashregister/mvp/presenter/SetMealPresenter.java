package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SetMealContact;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class SetMealPresenter extends BasePresenterImpl<SetMealContact.view> implements SetMealContact.presenter {
    public SetMealPresenter(SetMealContact.view view) {
        super(view);
    }
    NetRequestCallBack<List<SetMealGroupbean>> mSetMealGroupCallback = new NetRequestCallBack<List<SetMealGroupbean>>() {
        @Override
        public void onSuccess(List<SetMealGroupbean> setMealGroupbeans) {
            mView.onGroupDishesList(setMealGroupbeans);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<List<Dishesbean>> mSetMealDishesCallback = new NetRequestCallBack<List<Dishesbean>>() {
        @Override
        public void onSuccess(List<Dishesbean> setMealGroupbeans) {
            mView.onSetMealDishesResult(setMealGroupbeans);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void getSetMealGroup(String... params) {
        NetRequest request = ApiManager.getInstance().getSetMealGroup(params, mSetMealGroupCallback);
        addRequest(request);
    }

    @Override
    public void getSetMealDishes(String... params) {
        NetRequest request = ApiManager.getInstance().getSetMealDishes(params, mSetMealDishesCallback);
        addRequest(request);
    }

}
