package com.shigoo.cashregister.adapters.viewHolder;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.LeftSetMealChildAdapter;
import com.xgsb.datafactory.bean.AddFavorablebean;
import com.xgsb.datafactory.bean.ComboData;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.enu.DiscountType;
import com.zx.api.api.utils.AppUtil;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_dishes_old_tv)
    TextView mOldDishesPriTv;
    @BindView(R.id.ordersheet_dishes_price_tv)
    TextView mSalePricetv;
    @BindView(R.id.ordersheet_dishes_num_tv)
    TextView mNumTv;
    @BindView(R.id.ordersheet_dishes_name_tv)
    TextView mDishNametv;
    @BindView(R.id.ordersheet_dishes_item_remark_tv)
    TextView mRemarktv;
    @BindView(R.id.ordersheet_dishes_child_dishes_listview)
    RecyclerView mChildRecyclerView;
    @BindView(R.id.ordersheet_dishes_w_tv)
    TextView mWdTv;
    @BindView(R.id.ordersheet_dishes_d_tv)
    TextView mDJTv;
    @BindView(R.id.ordersheet_dishes_tj_tv)
    TextView mTjTv;
    @BindView(R.id.ordersheet_dishes_tui_tv)
    TextView mTuiTv;
    @BindView(R.id.order_menu_item_layout)
    RelativeLayout mItemBg;
    @BindView(R.id.ordersheet_dishes_cd_tv)
    TextView mCdTv;
    private String mBillCode;
    private Member member;
    @BindColor(R.color.ordersheet_remark_color)
    int payedColor;
    @BindColor(R.color.ordersheet_table_des_color)
    int nomalNameColor;
    @BindView(R.id.ordersheet_dishes_z_tv)
    TextView mZheKouTv;


    public MenuListHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
        mOldDishesPriTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void setItem(final Dishesbean item, DiscountType discountType, int position) {
        mSalePricetv.setText(Param.Keys.RMB + showPriceUi(item));
        if (item.getLocal_num() > 1) {
            mNumTv.setVisibility(View.VISIBLE);
            mNumTv.setText("X " + item.getLocal_num());
        } else {
            mNumTv.setVisibility(View.GONE);
        }
        mDishNametv.setText(TextUtils.isEmpty(item.getDish_name()) ? item.getDishes_name() : item.getDish_name());
        if (item.getRemarkbeans() != null && item.getRemarkbeans().size() != 0) {
            mRemarktv.setVisibility(View.VISIBLE);
            StringBuffer buffer = new StringBuffer();
            for (Remarkbean remarkbean : item.getRemarkbeans()) {
                buffer.append(remarkbean.getRemarks_name());
                buffer.append("、");
            }
            mRemarktv.setText("备注：" + buffer.toString());
        } else if (!TextUtils.isEmpty(item.getRemark())) {
            mRemarktv.setVisibility(View.VISIBLE);
            mRemarktv.setText("备注：" + item.getRemark());
        } else {
            mRemarktv.setVisibility(View.GONE);
        }
        if (item.getOut_tag() == 1) {
            mWdTv.setVisibility(View.VISIBLE);
        } else {
            mWdTv.setVisibility(View.GONE);
        }

        if ("4".equals(item.getFinally_tag())) {
            mZheKouTv.setVisibility(View.VISIBLE);
        } else {
            mZheKouTv.setVisibility(View.GONE);
        }
        if ("10".equals(item.getFinally_tag())) {
            mCdTv.setVisibility(View.VISIBLE);
        } else {
            mCdTv.setVisibility(View.GONE);
        }
        if (item.getWait_tag() == 1) {
            mDJTv.setVisibility(View.VISIBLE);
        } else {
            mDJTv.setVisibility(View.GONE);
        }
        if ("3".equals(item.getPay_tag())) {
            mTuiTv.setVisibility(View.VISIBLE);
        } else {
            mTuiTv.setVisibility(View.GONE);
        }
        if ("优惠-特价菜".equals(item.getMinFavorable().getName())) {
            mTjTv.setVisibility(View.VISIBLE);
        } else {
            mTjTv.setVisibility(View.GONE);
        }
        if ("1".equals(item.getDish_tag())) {
            mChildRecyclerView.setVisibility(View.VISIBLE);
            List<Dishesbean> list = new ArrayList<>();
            if (item.getMealGroupbean() != null) {
                list = item.getMealGroupbean().getGroup_dishes();
            } else {
                list = getDishesList(item.getCombo_data());
                SetMealGroupbean groupbean = new SetMealGroupbean();
                groupbean.setGroup_dishes(list);
                groupbean.setDishesbean(item);
                item.setMealGroupbean(groupbean);
            }
            if (list != null && list.size() != 0) {
                mChildRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                final LeftSetMealChildAdapter adapter = new LeftSetMealChildAdapter(R.layout.ordersheet_left_setmeal_item_layout, list);
                mChildRecyclerView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return itemView.onTouchEvent(motionEvent);
                    }
                });
                mChildRecyclerView.setAdapter(adapter);
            }
        } else {
            mChildRecyclerView.setVisibility(View.GONE);
        }
        if (getLayoutPosition() == position) {
            itemView.setBackgroundColor(Color.parseColor("#d2eaff"));
        } else {
            if ("1".equals(item.getPay_tag())) {
                itemView.setBackgroundColor(payedColor);
            } else {
                itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
    }

    private String showPriceUi(Dishesbean item) {
        AddFavorablebean max = item.getMaxFavorable();
        AddFavorablebean min = item.getMinFavorable();
        if (max == null || min == null) {
            return "数据错误";
        }
        String showPrice = min.getMoney();
        String salePrice = max.getMoney();
        if (("4".equals(item.getFinally_tag()) || "3".equals(item.getFinally_tag()))
                && !TextUtils.isEmpty(item.getFinally_price()) || "1".equals(item.getPay_tag())) {
            showPrice = item.getFinally_price();
        }
        //是否显示 原价
        if (AppUtil.getFloatFromString(showPrice).floatValue() != AppUtil.getFloatFromString(salePrice).floatValue()) {
            mOldDishesPriTv.setVisibility(View.VISIBLE);
            mOldDishesPriTv.setText("原价： " + Param.Keys.RMB + salePrice);
        } else {
            mOldDishesPriTv.setVisibility(View.GONE);
        }
        item.setShowPrice(showPrice);
        return showPrice;
    }

    private List<Dishesbean> getDishesList(List<ComboData> combo_data) {
        List<Dishesbean> list = new ArrayList<>();
        for (ComboData data : combo_data) {
            Dishesbean dishesbean = new Dishesbean();
            dishesbean.setNum(Integer.valueOf(data.getNum()));
            dishesbean.setDish_name(data.getDishes_name());
            dishesbean.setDishes_specification(data.getDishes_specification());
            dishesbean.setSpecification_id(data.getId() + "");
            list.add(dishesbean);
        }
        return list;
    }

    public void setBillCode(String billCode) {
        this.mBillCode = billCode;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
