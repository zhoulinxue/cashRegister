package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.SaleCountbean;
import com.xgsb.datafactory.bean.TableCosumbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class PrintFragmentContact {
    public interface printPresenter {
        public void getSaleList(String... params);

        void getKindRecive(String... params);

        void getSaleCountList(String... params);

        void getTableCosumList(String... params);
    }

    public interface view extends BaseView {
        public void onSaleResult(List<Printbean> list);

        public void onKindResult(List<KindRecivebean> list);

        public void onSaleCountList(List<SaleCountbean> list);

        public void onTableConsumList(List<TableCosumbean> list);
    }
}
