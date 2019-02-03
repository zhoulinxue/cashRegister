package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.PrintTableConsumebean;
import com.xgsb.datafactory.bean.TableArea;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class PrintTableConsumContact {
    public interface presenter {

        public void getTableAreaList(String... params);

        public void getTableConsumeList(String... params);
    }

    public interface view extends BaseView {
        public void onTableConsumeResult(List<PrintTableConsumebean> list);

        public void onAreaListResult(List<TableArea> list);
    }
}
