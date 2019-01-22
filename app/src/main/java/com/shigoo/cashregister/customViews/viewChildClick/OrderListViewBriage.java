package com.shigoo.cashregister.customViews.viewChildClick;

import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.bean.Table;

public class OrderListViewBriage {
    public interface onOrderViewClick {
        public void onBackToTable();

        public void gotoTable();

        public void onPayBtn(SettalOrderbean orderbean);

        public void onDishesDetail(Dishesbean dishesbean);

        public void onOrderListResult(Dishesbean dishesbean);

        public void onCopyDishes(String billCode);

        void onBacktoMain();

        void gotoDishesList();

        void onPayDataChanage(SettalOrderbean payOrder);

        void updateDishesNum(Dishesbean number);

        void onClickRemarkBtn(Dishesbean current);

        void deleteItem(Dishesbean totalNum);

        void onCleanDemolition();

        void demolition();

        void cancelDemolition();
    }

    public interface onFragmentAction {
        public void onAddDisehes(Dishesbean dishesbean);

        public void onTableDetail(Table table);

        public void onUpdateDishes(Dishesbean dishesbean);

        public void onCopyResult();

    }
}
