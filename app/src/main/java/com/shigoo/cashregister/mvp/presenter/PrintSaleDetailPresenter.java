package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintSaleDetailContact;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.xgsb.datafactory.bean.SaleDetailbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class PrintSaleDetailPresenter extends BasePresenterImpl<PrintSaleDetailContact.view> implements PrintSaleDetailContact.printPresenter {
    public PrintSaleDetailPresenter(PrintSaleDetailContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<SaleDetailbean>> mSaleListCallBack = new NetRequestCallBack<List<SaleDetailbean>>() {
        @Override
        public void onSuccess(List<SaleDetailbean> reChargebeanListData) {
            mView.dismissLoadingDiaog();
            mView.onSaleDetailList(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode, msg);
        }
    };

    @Override
    public void getSaleDetailList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getSaleDetailList(params, mSaleListCallBack);
        addRequest(request);
    }
}
