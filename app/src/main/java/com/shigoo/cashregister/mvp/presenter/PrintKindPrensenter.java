package com.shigoo.cashregister.mvp.presenter;

import com.shigoo.cashregister.mvp.contacts.PrintkindContact;
import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.mvplibrary.presenter.BasePresenterImpl;
import com.zx.network.OKHttp.ApiManager;

import java.util.List;

public class PrintKindPrensenter extends BasePresenterImpl<PrintkindContact.view> implements PrintkindContact.printPresenter {

    public PrintKindPrensenter(PrintkindContact.view view) {
        super(view);
    }

    NetRequestCallBack<ListData<KindRecivebean>> mSaleListCallBack = new NetRequestCallBack<ListData<KindRecivebean>>() {
        @Override
        public void onSuccess(ListData<KindRecivebean> reChargebeanListData) {
            mView.dismissLoadingDiaog();
            mView.onKindList(reChargebeanListData.getData());
//            mView.onKindCount( reChargebeanListData.getCount_number()+"",reChargebeanListData.getCount_money() + "");
            mView.onKindCount( "100",reChargebeanListData.getCount_money() + "");
        }

        @Override
        public void onError(int responseCode, String msg) {
            mView.dismissLoadingDiaog();
            mView.onError(responseCode, msg);
        }
    };

    @Override
    public void getKindReceveList(String... params) {
        mView.showLoadingDialog();
        NetRequest request = ApiManager.getInstance().getKindReciveList(params, mSaleListCallBack);
        addRequest(request);
    }
}
