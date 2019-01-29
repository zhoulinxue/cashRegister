package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.OrderPerformanceDetailbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformanceDetailbean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class SellerPerformaceContact {
    public interface presenter {

        void getSellerPerformanceList(String... params);

        void getSellerPerformanceDetail(String... params);
    }

    public interface view extends BaseView {
        void onSellerPerformanceListResult(List<SalePerformancebean> dishesbeans);

        void onSellerCount(String number, String money);

        void onSellerPerformanceDetail(SalePerformanceDetailbean detailbeanList);
    }
}
