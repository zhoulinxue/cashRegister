package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SalesPerformanceContact {
    public interface presenter {
        void getSalePerformanceList(String... params);
        void getOrderPerformanceList(String... params);
    }

    public interface view extends BaseView {
        void onSalePerformanceListResult(List<SalePerformancebean> dishesbeans);

        void onOrderPerformanceListResult(List<Dishesbean> dishesbeans);
    }
}
