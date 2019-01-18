package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.ConsumeContact;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

/**
 * Name: CashLoginPresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 14:27
 */
public class ConsumePresenter extends BasePresenterImpl<ConsumeContact.view> implements ConsumeContact.presenter {

    public ConsumePresenter(ConsumeContact.view view) {
        super(view);
    }

    NetRequestCallBack<ConsumeListData> reChargeCallback = new NetRequestCallBack<ConsumeListData>() {
        @Override
        public void onSuccess(ConsumeListData reChargebeanListData) {
            mView.dismissLoadingDiaog();
            mView.onGetConsumeListCallback(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getConsumeList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getConsumeList(params, reChargeCallback);
        addRequest(request);
    }

    @Override
    public void getMemberMoneyDiatailList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getMemberMoneyDetail(params, mMoneyListCallback);
        addRequest(request);
    }

    NetRequestCallBack<String> mMoneyListCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String reChargebeanListData) {
            mView.dismissLoadingDiaog();
            mView.onMemberMoneyList(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

}
