package com.shigoo.cashregister.mvp.contacts;

import com.xgsb.datafactory.bean.Departmentbean;
import com.xgsb.datafactory.bean.DishesKind;
import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.PrintSaleListbean;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.SaleCountbean;
import com.xgsb.datafactory.bean.TableCosumbean;
import com.xgsb.datafactory.bean.TimeData;
import com.zx.api.api.mvp.BaseView;

import java.util.List;

public class PrintSaleListContact {
    public interface printPresenter {
        public void getSaleList(String... params);

        public  void getTimeList(String...params);

        public void getDepartment(String...params);

        public void getDishKind(String...params);
    }

    public interface view extends BaseView {
        public void onSaleResult(List<PrintSaleListbean> list);

        public void onTimeListResult(List<TimeData> list);

        public void onDepartmentList(List<Departmentbean> list);

        public void onDishKind(List<DishesKind> list);
    }
}
