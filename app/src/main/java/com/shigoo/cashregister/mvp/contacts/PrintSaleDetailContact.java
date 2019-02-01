package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.SaleDetailbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class PrintSaleDetailContact {
    public interface printPresenter {
        public void getSaleDetailList(String... params);
    }

    public interface view extends BaseView {
        public void onSaleDetailList(List<SaleDetailbean> list);
    }
}
