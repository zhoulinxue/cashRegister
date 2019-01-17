package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Chedanbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class TableDialogContact {
    public interface presenter {
        void cancelOrder(Chedanbean chedanbean);

        void getCancelList(String... params);
    }

    public interface view extends BaseView {

        void onCancelOrderResult(String listData);

        void onCancelResult(List<String> resonList);
    }
}
