package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.RechargeContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.MemberReChargeGive;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

/**
 * Name: RechargePresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-04 16:35
 */
public class RechargePresenter extends BasePresenterImpl<RechargeContact.view> implements RechargeContact.presenter {
    public RechargePresenter(RechargeContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<MemberReChargeGive>> mReChargeGivesCallback = new NetRequestCallBack<ListData<MemberReChargeGive>>() {
        @Override
        public void onSuccess(ListData<MemberReChargeGive> memberLevelListData) {
            mView.onReChareList(memberLevelListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    NetRequestCallBack<String> mReChargeCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String str) {
            mView.dismissLoadingDiaog();
            mView.onRechargeResult(str);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<Double> mGiveCallback = new NetRequestCallBack<Double>() {
        @Override
        public void onSuccess(Double give) {
            mView.onGiveResult(give);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };


    @Override
    public void getReChareList(String... params) {
        NetRequest request = ApiManager.getInstance().getReChargeGive(params, mReChargeGivesCallback);
        addRequest(request);
    }

    @Override
    public void recharge(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().recharge(params, mReChargeCallback);
        addRequest(request);
    }

    @Override
    public void getGives(String... params) {
        NetRequest request = ApiManager.getInstance().getGive(params, mGiveCallback);
        addRequest(request);
    }
}
