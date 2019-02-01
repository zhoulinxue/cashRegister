package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintSaleListContact;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.xgsb.datafactory.bean.TimeData;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class PrintSaleListPresenter extends BasePresenterImpl<PrintSaleListContact.view> implements PrintSaleListContact.printPresenter {

    public PrintSaleListPresenter(PrintSaleListContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<TimeData>> mTimeDataCallBack = new NetRequestCallBack<ListData<TimeData>>() {
        @Override
        public void onSuccess(ListData<TimeData> reChargebeanListData) {
            mView.onTimeListResult(reChargebeanListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode, msg);
        }
    };
    NetRequestCallBack<List<Departmentbean>> mDepartmentCallBack = new NetRequestCallBack<List<Departmentbean>>() {
        @Override
        public void onSuccess(List<Departmentbean> reChargebeanListData) {
            mView.onDepartmentList(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode, msg);
        }
    };
    NetRequestCallBack<ListData<DishesKind>> mDishesKindCallBack = new NetRequestCallBack<ListData<DishesKind>>() {
        @Override
        public void onSuccess(ListData<DishesKind> reChargebeanListData) {
            mView.onDishKind(reChargebeanListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode, msg);
        }
    };

    NetRequestCallBack<List<PrintSaleListbean>> mSaleListCallBack = new NetRequestCallBack<List<PrintSaleListbean>>() {
        @Override
        public void onSuccess(List<PrintSaleListbean> reChargebeanListData) {
            mView.onSaleResult(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode, msg);
        }
    };

    @Override
    public void getSaleList(String... params) {
        NetRequest request = ApiManager.getInstance().getPrintSaleList(params, mSaleListCallBack);
        addRequest(request);
    }

    @Override
    public void getTimeList(String... params) {
        NetRequest request = ApiManager.getInstance().getTimeDataList(params, mTimeDataCallBack);
        addRequest(request);
    }

    @Override
    public void getDepartment(String... params) {
        NetRequest request = ApiManager.getInstance().getDepartment(params, mDepartmentCallBack);
        addRequest(request);
    }

    @Override
    public void getDishKind(String... params) {
        NetRequest request = ApiManager.getInstance().getDishesKindList(params, mDishesKindCallBack);
        addRequest(request);
    }
}
