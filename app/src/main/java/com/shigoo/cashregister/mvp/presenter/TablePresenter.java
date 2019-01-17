package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.TableContact;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
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
public class TablePresenter extends BasePresenterImpl<TableContact.view> implements TableContact.presenter {
    public TablePresenter(TableContact.view view) {
        super(view);
    }

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
    public void copyDishes(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getOrderDishesList(params, mCopyCallback);
        addRequest(request);
    }


    NetRequestCallBack<SettalOrderResultbean> mCopyCallback = new NetRequestCallBack<SettalOrderResultbean>() {
        @Override
        public void onSuccess(SettalOrderResultbean listData) {
            mView.dismissLoadingDiaog();
            mView.onCopySuc(listData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
}
