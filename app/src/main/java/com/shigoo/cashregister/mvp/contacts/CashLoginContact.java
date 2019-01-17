package com.shigoo.cashregister.mvp.contacts;


import com.xgsb.datafactory.bean.User;
import com.zx.api.api.mvp.BaseView;

/**
 * Name: CashLoginContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 14:29
 */
public class CashLoginContact {
   public interface presenter {
        void cashLogin(User user);
    }

   public interface view extends BaseView {
        void loginCallbak(User user);
    }

}
