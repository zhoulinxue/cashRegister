package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.ConsumeContact;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.MemberMoney;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

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
            mView.onGetConsumeListCallback(reChargebeanListData);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    @Override
    public void getConsumeList(String... params) {
        NetRequest request = ApiManager.getInstance().getConsumeList(params, reChargeCallback);
        addRequest(request);
    }

    @Override
    public void getMemberMoneyDiatailList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getMemberMoneyDetail(params, mMoneyListCallback);
        addRequest(request);
    }

    NetRequestCallBack<ListData<MemberMoney>> mMoneyListCallback = new NetRequestCallBack<ListData<MemberMoney>>() {
        @Override
        public void onSuccess(ListData<MemberMoney> reChargebeanListData) {
            mView.dismissLoadingDiaog();
            mView.onMemberMoneyList(reChargebeanListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

}
