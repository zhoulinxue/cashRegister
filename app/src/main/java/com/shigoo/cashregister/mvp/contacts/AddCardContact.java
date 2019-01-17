package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Cardbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

/**
 * Name: AddCardContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 13:36
 */
public class AddCardContact {
    public interface presenter {
        void getCardMessage(String... params);

        void addCardCodingNum(String... params);

        void deleteCard(String... params);

        void getCardMsg(String... params);
    }

    public interface view extends BaseView {
        void onResult(List<Cardbean> cardbean);

        void onAddresult(String msg);

        void onDeleteResult(String msg);

        void onCodeCardError(String msg);

        void onCardMsg(Cardbean cardbean);
    }
}
