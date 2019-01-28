package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.MemberManageContact;
import com.xgsb.datafactory.bean.Countbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.MemberLevel;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

/**
 * Name: MemberManagePresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-23 14:06
 */
public class MemberManagePresenter extends BasePresenterImpl<MemberManageContact.view> implements MemberManageContact.presenter {
    public MemberManagePresenter(MemberManageContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<Member>> mCashMemberCallback = new NetRequestCallBack<ListData<Member>>() {
        @Override
        public void onSuccess(ListData<Member> memberListData) {
            mView.onGetMenbersCallback(memberListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
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

    NetRequestCallBack<Countbean> countCallback = new NetRequestCallBack<Countbean>() {
        @Override
        public void onSuccess(Countbean countbean) {
            mView.onResult(countbean);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<ListData<MemberLevel>> mLevelsCallback = new NetRequestCallBack<ListData<MemberLevel>>() {
        @Override
        public void onSuccess(ListData<MemberLevel> memberLevelListData) {
            mView.onMemberList(memberLevelListData.getData());
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };
    NetRequestCallBack<Member> mMemberCallback = new NetRequestCallBack<Member>() {
        @Override
        public void onSuccess(Member member) {
            mView.dismissLoadingDiaog();
            mView.onMemberResult(member);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(msg);
        }
    };

    @Override
    public void getMemberList(String... params) {
        NetRequest request = ApiManager.getInstance().getMemberList(params, mCashMemberCallback);
        addRequest(request);
    }

    @Override
    public void searchMembers(String... params) {
        getMemberData(params);
    }

    @Override
    public void getCountData(String... params) {
        NetRequest request = ApiManager.getInstance().getCountData(params, countCallback);
        addRequest(request);
    }

    @Override
    public void addmember(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().addMember(params, mAddmemberCallback);
        addRequest(request);
    }

    @Override
    public void getMemberLevelList(String... params) {
        NetRequest request = ApiManager.getInstance().getMemberLevelList(params, mLevelsCallback);
        addRequest(request);
    }

    @Override
    public void getMemberData(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getMemberMsgByCard(params, mMemberCallback);
        addRequest(request);
    }

}
