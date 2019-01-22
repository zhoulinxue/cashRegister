package com.shigoo.cashregister.customViews.viewChildClick;

import com.xgsb.datafactory.bean.Billbean;
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

    public interface onFormatChildClick {
        public void onReturnDishes(Dishesbean dishesbean);

        public void onChanagePrice(Dishesbean dishesbean);

        public void onDiscount(Dishesbean dishesbean);

        public void  onChedan(String billCode);

        public void onFormatClick(Dishesbean dishesbean);

        public void onCopy(Billbean mBillbean);

        public void onRemarkClick(Dishesbean mCurrent);
    }
}
