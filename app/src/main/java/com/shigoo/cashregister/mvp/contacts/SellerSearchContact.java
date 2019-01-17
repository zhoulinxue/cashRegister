package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Sellerbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

/**
 * Name: SellerSearchContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 10:48
 */
public class SellerSearchContact {
    public interface presenter {
        void getSellerList(String... params);
    }

    public interface view extends BaseView {
        void onSellerListResult(List<Sellerbean> list);
    }
}
