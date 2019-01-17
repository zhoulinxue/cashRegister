package com.shigoo.cashregister.adapters.viewHolder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentTypeHoldView extends BaseViewHolder {
    @BindView(R.id.payment_name_tv)
    TextView mNameTv;
    @BindView(R.id.payment_root_layout)
    RelativeLayout mRootBg;
    @BindColor(R.color.ordersheet_colorAccent)
    int mSelectedColor;
    @BindColor(R.color.ordersheet_area_color)
    int mNomalColor;
    @BindView(R.id.member_msg_tv)
    TextView mMemberTv;
    @BindView(R.id.payment_type_head)
    ImageView mHeadImg;
    private int[] src = {R.mipmap.icon_pay_1, R.mipmap.icon_discount_2, R.mipmap.icon_pay_3, R.mipmap.icon_pay_4, R.mipmap.icon_pay_5};


    public PaymentTypeHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setDishesTypeName(String region_name, SettalOrderbean orderbean) {
        mNameTv.setText(region_name);
        mHeadImg.setImageResource(src[(getLayoutPosition() + src.length) % src.length]);
        try {
            if (orderbean != null) {
                String memberMsg = SPUtil.getInstance().getString(orderbean.getBill_code());
                if (!TextUtils.isEmpty(memberMsg) && "会员储值".equals(region_name)) {
                    Member member = (Member) JSONManager.getInstance().parseObject(memberMsg, Member.class);
                    mMemberTv.setVisibility(View.VISIBLE);
                    mMemberTv.setText(TextUtils.isEmpty(member.getMobile()) ? member.getName() : AppUtil.mixPhone(member.getMobile()));
                } else {
                    mMemberTv.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            SPUtil.getInstance().putString(orderbean.getBill_code(), "");
            mMemberTv.setVisibility(View.GONE);
        }
    }

    public void setSelected(boolean isSelected) {
        if (isSelected) {
            mNameTv.setTextColor(mSelectedColor);
            mRootBg.setBackgroundResource(R.drawable.ordersheet_table_area_item_selected_bg);
        } else {
            mNameTv.setTextColor(mNomalColor);
            mRootBg.setBackgroundResource(R.drawable.member_search_edit_bg);
        }
    }
}
