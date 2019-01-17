package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.GiveAsGiftContact;
import com.xgsb.datafactory.bean.GiveDishesTypebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class GiveAsGiftPresenter extends BasePresenterImpl<GiveAsGiftContact.view> implements GiveAsGiftContact.presenter {
    NetRequestCallBack<List<Table>> mTableListCallback = new NetRequestCallBack<List<Table>>() {
        @Override
        public void onSuccess(List<Table> tables) {
            mView.onTableResult(tables);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<List<TableArea>> mAreaListCallback = new NetRequestCallBack<List<TableArea>>() {
        @Override
        public void onSuccess(List<TableArea> tableAreas) {

            mView.onAreaListResult(tableAreas);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    NetRequestCallBack<List<GiveDishesTypebean>> mGiveTypeListCallback = new NetRequestCallBack<List<GiveDishesTypebean>>() {
        @Override
        public void onSuccess(List<GiveDishesTypebean> tableAreas) {
            mView.onGiveCountResult(tableAreas);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    NetRequestCallBack<ListData<GiveDishesbean>> mGiveDishesListCallback = new NetRequestCallBack<ListData<GiveDishesbean>>() {
        @Override
        public void onSuccess(ListData<GiveDishesbean> tableAreas) {
            mView.onGiveDishesResult(tableAreas);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    public GiveAsGiftPresenter(GiveAsGiftContact.view view) {
        super(view);
    }

    @Override
    public void getTableList(String... params) {
        NetRequest request = ApiManager.getInstance().getTableList(params, mTableListCallback);
        addRequest(request);
    }

    @Override
    public void getAreaList(String... params) {
        NetRequest request = ApiManager.getInstance().getTableAreaList(params, mAreaListCallback);
        addRequest(request);
    }

    @Override
    public void getGiveCount(String... params) {
        NetRequest request = ApiManager.getInstance().getGiveDishesTypeList(params, mGiveTypeListCallback);
        addRequest(request);
    }

    @Override
    public void getMenuList(String... params) {
        NetRequest request = ApiManager.getInstance().getGiveDishesList(params, mGiveDishesListCallback);
        addRequest(request);
    }
}
