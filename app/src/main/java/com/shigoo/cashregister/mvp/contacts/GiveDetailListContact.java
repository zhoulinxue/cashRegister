package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.GiveDetailListbean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class GiveDetailListContact {
    public interface presenter {
        void getGiveDetailList(String... params);

    }

    public interface view extends BaseView {
        void onGiveDetailListResult(List<GiveDetailListbean> giveDetailListbeans);
    }
}
