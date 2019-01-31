package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SellerPerformaceContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.SalePerformanceDetailbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

public class SellerPerformancePresenter extends BasePresenterImpl<SellerPerformaceContact.view> implements SellerPerformaceContact.presenter {
    public SellerPerformancePresenter(SellerPerformaceContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<SalePerformancebean>> mSellerPerformaceCallback = new NetRequestCallBack<ListData<SalePerformancebean>>() {
        @Override
        public void onSuccess(ListData<SalePerformancebean> sellerbeans) {
            mView.dismissLoadingDiaog();
            mView.onSellerPerformanceListResult(sellerbeans.getData());
            mView.onSellerCount(sellerbeans.getList_count() + "", sellerbeans.getSum_money() + "");
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<SalePerformanceDetailbean> mSellerDetailCallback = new NetRequestCallBack<SalePerformanceDetailbean>() {
        @Override
        public void onSuccess(SalePerformanceDetailbean sellerbeans) {
            mView.dismissLoadingDiaog();
            mView.onSellerPerformanceDetail(sellerbeans);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getSellerPerformanceList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getSellerPerformace(params, mSellerPerformaceCallback);
        addRequest(request);
    }

    @Override
    public void getSellerPerformanceDetail(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getSellerPerformaceDetail(params, mSellerDetailCallback);
        addRequest(request);
    }
}
