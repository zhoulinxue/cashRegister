package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.AddCardContact;
import com.xgsb.datafactory.bean.Cardbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

/**
 * Name: AddCardPresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 13:35
 */
public class AddCardPresenter extends BasePresenterImpl<AddCardContact.view> implements AddCardContact.presenter {

    public AddCardPresenter(AddCardContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<Cardbean>> mCardsCallback = new NetRequestCallBack<List<Cardbean>>() {
        @Override
        public void onSuccess(List<Cardbean> cardbean) {
            mView.onResult(cardbean);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };

    NetRequestCallBack<Cardbean> mCardCallback = new NetRequestCallBack<Cardbean>() {
        @Override
        public void onSuccess(Cardbean cardbean) {
            mView.onCardMsg(cardbean);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };
    NetRequestCallBack<String> mAddCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String s) {
            mView.onAddresult("制卡成功");
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onCodeCardError(msg);
        }
    };
    NetRequestCallBack<String> mDeleteCallback = new NetRequestCallBack<String>() {
        @Override
        public void onSuccess(String s) {
            mView.onDeleteResult(s);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(responseCode,msg);
        }
    };

    @Override
    public void getCardMessage(String... params) {
        NetRequest request = ApiManager.getInstance().getCardInfo(params, mCardsCallback);
        addRequest(request);
    }

    @Override
    public void addCardCodingNum(String... params) {
        NetRequest request = ApiManager.getInstance().addCardEntityNum(params, mAddCallback);
        addRequest(request);
    }

    @Override
    public void deleteCard(String... params) {
        NetRequest request = ApiManager.getInstance().deleteCardEntityNum(params, mDeleteCallback);
        addRequest(request);
    }

    @Override
    public void getCardMsg(String... params) {
        NetRequest request = ApiManager.getInstance().getCardMsg(params, mCardCallback);
        addRequest(request);
    }
}
