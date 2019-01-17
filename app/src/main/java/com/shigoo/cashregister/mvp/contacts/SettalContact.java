package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Paymentbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

/**
 * Name: AddCardContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 13:36
 */
public class SettalContact {
    public interface presenter {
        void getFavourableList(String... params);

        void getPayTypeList(String... params);

        void payOrder(Paymentbean mAddPayment);
    }

    public interface view extends BaseView {
        void onFavourableListResult(List<Favorablebean> cardbean);

        void onPayTypeResult(List<PayTypebean> list);

        void onPayResult(String msg);
    }
}
