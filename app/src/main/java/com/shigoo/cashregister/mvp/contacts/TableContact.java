package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

/**
 * Name: TableContact
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-11 13:18
 */
public class TableContact {
    public interface presenter {
        void getTableList(String... params);

        void getAreaList(String... params);

        void copyDishes(String... params);
    }

    public interface view extends BaseView {
        void onTableResult(List<Table> tables);

        void onAreaListResult(List<TableArea> areas);

        void onCopySuc(SettalOrderResultbean listData);

    }
}
