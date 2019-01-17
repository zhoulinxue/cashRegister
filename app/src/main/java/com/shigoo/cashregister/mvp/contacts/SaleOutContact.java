package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.SaleOutbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SaleOutContact {
    public interface presenter {
        void getSaleOutList(String... params);

        void updateSaleOut(String... params);

        void deleteSaleOut(String... params);

        void deleteAllSaleOut(String... params);
    }

    public interface view extends BaseView {
        void onSaleDishesList(List<SaleOutbean> dishesbeans);

        void onUpdateResult(String msg);

        void onDeleteResult(String msg);

        void onClean(String msg);
    }
}
