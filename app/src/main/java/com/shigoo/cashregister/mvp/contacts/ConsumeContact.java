package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.ConsumeListData;
import com.zx.api.api.mvp.BaseView;

/**
 * Name: MemberManageContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-23 14:05
 */
public class ConsumeContact {
    public interface presenter {
        void getConsumeList(String... params);

        void getMemberMoneyDiatailList(String...params);
    }

    public interface view extends BaseView {
        void onGetConsumeListCallback(ConsumeListData list);

        void onMemberMoneyList(String msg);
    }
}
