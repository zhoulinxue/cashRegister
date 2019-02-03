package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintTableConsumContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.PrintTableConsumebean;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class PrintTableConsumePresenter extends BasePresenterImpl<PrintTableConsumContact.view> implements PrintTableConsumContact.presenter {
    public PrintTableConsumePresenter(PrintTableConsumContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<TableArea>> mAreaListCallback = new NetRequestCallBack<List<TableArea>>() {
        @Override
        public void onSuccess(List<TableArea> tableAreas) {

            mView.onAreaListResult(tableAreas);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode, msg);
        }
    };

    NetRequestCallBack<ListData<PrintTableConsumebean>> mTableConsumeListCallback = new NetRequestCallBack<ListData<PrintTableConsumebean>>() {
        @Override
        public void onSuccess(ListData<PrintTableConsumebean> tableAreas) {
            mView.dismissLoadingDiaog();
            mView.onTableConsumeResult(tableAreas.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode, msg);
        }
    };

    @Override
    public void getTableAreaList(String... params) {
        NetRequest request = ApiManager.getInstance().getTableAreaList(params, mAreaListCallback);
        addRequest(request);
    }

    @Override
    public void getTableConsumeList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getTableConsumeList(params, mTableConsumeListCallback);
        addRequest(request);
    }
}
