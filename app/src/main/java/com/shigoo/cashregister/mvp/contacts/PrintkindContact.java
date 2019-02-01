package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.KindRecivebean;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class PrintkindContact {
    public interface printPresenter {
        public void getKindReceveList(String... params);
    }
    public interface view extends BaseView{
        public void onKindList(List<KindRecivebean> list);

        public void  onKindCount(String percent,String money);

    }
}
