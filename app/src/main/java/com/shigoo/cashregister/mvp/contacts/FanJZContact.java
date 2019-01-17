package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.FanJZbean;
import com.xgsb.datafactory.bean.GiveDishesTypebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class FanJZContact {
    public interface presenter {
        void FJZOrder(FanJZbean fanJZbean);

        void getFJZReasonList(String... params);

        void getPayStatus(String... params);
    }

    public interface view extends BaseView {
        void onOrderDishesListResult(SettalOrderResultbean resultbean);

        void onFJZOrderResult(String listData);

        void onPayResult(OrderPayStatusbean paybeans);

        void onReasonlResult(List<Remarkbean> resonList);
    }
}
