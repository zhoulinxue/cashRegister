package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.MemberMsgContact;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.MemberLevel;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

/**
 * Name: MemberMsgPresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 14:45
 */
public class MemberMsgPresenter extends BasePresenterImpl<MemberMsgContact.view> implements MemberMsgContact.presenter {
    public MemberMsgPresenter(MemberMsgContact.view view) {
        super(view);
    }

    NetRequestCallBack<String> mAddmemberCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String s) {
            mView.dismissLoadingDiaog();
            mView.onAddSuc(s);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };
    NetRequestCallBack<String> mUpdatemberCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String s) {
            mView.dismissLoadingDiaog();
            mView.onUpdateSuc(s);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    NetRequestCallBack<ListData<MemberLevel>> mLevelsCallback = new NetRequestCallBack<ListData<MemberLevel>>() {
        @Override
        public void onSuccess(ListData<MemberLevel> memberLevelListData) {
            mView.onLevelList(memberLevelListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    @Override
    public void addMember(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().addMember(params, mAddmemberCallback);
        addRequest(request);
    }

    @Override
    public void updateMember(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().updateMember(params, mUpdatemberCallback);
        addRequest(request);
    }

    @Override
    public void getMemberLevelList(String... params) {
        NetRequest request = ApiManager.getInstance().getMemberLevelList(params, mLevelsCallback);
        addRequest(request);
    }
}
