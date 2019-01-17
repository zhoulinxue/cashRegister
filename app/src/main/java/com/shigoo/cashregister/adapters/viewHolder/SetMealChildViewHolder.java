package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.SetMealChildListAdapter;
import com.shigoo.cashregister.adapters.SetMealListAdapter;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetMealChildViewHolder extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_name_tv)
    TextView mDishesName;
    @BindView(R.id.ordersheet_dishes_num_tv)
    TextView mDishesNum;
    @BindView(R.id.ordersheet_dishes_last_num)
    TextView mDisheslastNum;
    @BindView(R.id.ordersheet_setmeal_item_bottom_layout)
    LinearLayout mBottomlayout;
    private Dishesbean mItem;
    private int currentNum;
    private SetMealChildListAdapter mealChildListAdapter;
    private SetMealGroupbean setMealGroupbean;
    private SetMealListAdapter.onNumChanage mLisenter;

    public SetMealChildViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(Dishesbean item) {
        mItem = item;
        mDishesName.setText(item.getDishes_name());
        if (item.getNum() != 0) {
            mDishesNum.setVisibility(View.VISIBLE);
            mDishesNum.setText(item.getNum() + "");
        } else {
            mDishesNum.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(item.getGroup_id())) {
            mBottomlayout.setVisibility(View.GONE);
        } else {
            mBottomlayout.setVisibility(View.VISIBLE);
        }
        addOnClickListener(R.id.ordersheet_decrice_tv);
        addOnClickListener(R.id.ordersheet_add_tv);

    }

    public void setSetMealGroupbean(SetMealGroupbean setMealGroupbean) {
        this.setMealGroupbean = setMealGroupbean;
    }

//    @OnClick({R.id.ordersheet_decrice_tv, R.id.ordersheet_add_tv})
//    public void onViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.ordersheet_decrice_tv:
//                mItem.setNum(mItem.getNum() == 0 ? 0 : mItem.getNum() - 1);
//                mealChildListAdapter.notifyItemChanged(getLayoutPosition());
//                break;
//            case R.id.ordersheet_add_tv:
//                if (setMealGroupbean != null) {
//                    if (("0".equals(setMealGroupbean.getIs_reply()) && mItem.getNum() == 1) || !setMealGroupbean.isMore()) {
//                        Toast.makeText(itemView.getContext(), "不能再点该菜品", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//                mItem.setNum(mItem.getNum() + 1);
//                mealChildListAdapter.notifyItemChanged(getLayoutPosition());
//                break;
//        }
//    }

    public void setMealChildListAdapter(SetMealChildListAdapter mealChildListAdapter) {
        this.mealChildListAdapter = mealChildListAdapter;
    }

    public void setonNumChanage(SetMealListAdapter.onNumChanage lisenter) {
        this.mLisenter = lisenter;
    }
}
