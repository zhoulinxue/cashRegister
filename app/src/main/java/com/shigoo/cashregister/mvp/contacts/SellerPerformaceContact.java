package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.OrderPerformanceDetailbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformanceDetailbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SellerPerformaceContact {
    public interface presenter {

        void getOrderPerformanceList(String... params);

        void getOrderPerformanceDetail(String... params);
    }

    public interface view extends BaseView {
        void onOrderPerformanceListResult(List<SalePerformancebean> dishesbeans);

        void onOrderCount(String number, String money);

        void onOrderPerformanceDetail(List<SalePerformanceDetailbean> detailbeanList);
    }
}
