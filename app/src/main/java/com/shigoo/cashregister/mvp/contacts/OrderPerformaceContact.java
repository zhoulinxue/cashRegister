package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class OrderPerformaceContact {
    public interface presenter {

        void getOrderPerformanceList(String... params);
    }

    public interface view extends BaseView {
        void onOrderPerformanceListResult(List<OrderPerformancebean> dishesbeans);
    }
}
