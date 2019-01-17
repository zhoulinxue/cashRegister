package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.GiveDishesTypebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class GiveAsGiftContact {
    public interface presenter {
        void getTableList(String... params);

        void getAreaList(String... params);

        void getGiveCount(String... params);

        void getMenuList(String... params);
    }

    public interface view extends BaseView {

        void onTableResult(List<Table> tables);

        void onAreaListResult(List<TableArea> areas);

        void onGiveCountResult(List<GiveDishesTypebean> giveDishesbeans);

        void onGiveDishesResult(ListData<GiveDishesbean> giveDishesbeans);

    }
}
