package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.CashLoginContact;
import com.xgsb.datafactory.bean.User;
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
public class CashLoginPresenter extends BasePresenterImpl<CashLoginContact.view> implements CashLoginContact.presenter {

    public CashLoginPresenter(CashLoginContact.view view) {
        super(view);
    }

    NetRequestCallBack<User> mCashLoginCallback = new NetRequestCallBack<User>() {
        @Override
        public void onSuccess(User user) {
            mView.dismissLoadingDiaog();
            mView.loginCallbak(user);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void cashLogin(User user) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().cashLogin(mCashLoginCallback, user);
        addRequest(request);
    }
}
