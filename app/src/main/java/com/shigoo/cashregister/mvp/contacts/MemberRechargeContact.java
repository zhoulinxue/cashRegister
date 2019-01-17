package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.ReChargeListData;
import com.zx.api.api.mvp.BaseView;

/**
 * Name: MemberManageContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-23 14:05
 */
public class MemberRechargeContact {
    public interface presenter {
        void getReChargeList(String... params);

        void cancelation(String... params);
    }

    public interface view extends BaseView {
        void onGetReChargeListCallback(ReChargeListData list);

        void onCancelLationResult(String msg);
    }
}
