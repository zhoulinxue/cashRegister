package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.OrderPayDetailbean;
import com.xgsb.datafactory.bean.Printbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class OrderPayDetailContact {
    public interface presenter {
        public void getOrderPayDetal(String... params);
    }

    public interface view extends BaseView {
        public void onPayDetailResualt(OrderPayDetailbean orderPayDetailbean);
    }
}
