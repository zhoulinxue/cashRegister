package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.AddGivebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class GiveReasonContact {
    public interface presenter {
        void getGiveReson(String... params);

        void addGive(AddGivebean addGivebean);

    }

    public interface view extends BaseView {
        void onResonResult(List<String> resons);

        void onAddResult(String msg);
    }
}
