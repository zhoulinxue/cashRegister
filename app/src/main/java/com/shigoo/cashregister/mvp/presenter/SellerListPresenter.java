package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.SellerSearchContact;
import com.xgsb.datafactory.bean.Sellerbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

/**
 * Name: SellerListPresenter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 10:49
 */
public class SellerListPresenter extends BasePresenterImpl<SellerSearchContact.view> implements SellerSearchContact.presenter {

    public SellerListPresenter(SellerSearchContact.view view) {
        super(view);
    }

    NetRequestCallBack<List<Sellerbean>> mSellerListCallback = new NetRequestCallBack<List<Sellerbean>>() {
        @Override
        public void onSuccess(List<Sellerbean> sellerbeans) {
            mView.onSellerListResult(sellerbeans);
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.onError(msg);
        }
    };

    @Override
    public void getSellerList(String... params) {
        NetRequest request = ApiManager.getInstance().getSellerList(params, mSellerListCallback);
        addRequest(request);
    }
}
