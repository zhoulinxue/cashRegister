package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.App;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.CouponAdapter;
import com.shigoo.cashregister.adapters.MenuDishesListAdapter;
import com.shigoo.cashregister.customViews.InputNumberView;
import com.shigoo.cashregister.mvp.contacts.MemberDetailContact;
import com.shigoo.cashregister.mvp.presenter.MemberDetailPresenter;
import com.shigoo.cashregister.utils.DishesUtils;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Couponbean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.Paymentbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.BaseActivity;
import com.zx.mvplibrary.MvpActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayMemberLoginActivity extends MvpActivity<MemberDetailPresenter> implements MemberDetailContact.view, InputNumberView.NumberViewResult, MenuDishesListAdapter.onItemSelected {
    @BindView(R.id.ordersheet_nuber_view_container)
    FrameLayout mContainer;
    @BindView(R.id.back_btn)
    TextView mBackTv;
    @BindView(R.id.member_detail_mobile_num)
    TextView mMemberPhone;
    @BindView(R.id.card_num)
    TextView mCardNum;
    @BindView(R.id.card_num_layout)
    View mCardNumLayout;
    @BindView(R.id.stored_value_balance)
    TextView mStoredValue;
    @BindView(R.id.stored_value_balance_else)
    TextView mElseStoredValue;

    @BindView(R.id.integral_balance_value)
    TextView mIntegralBalance;
    @BindView(R.id.coupon_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.member_detail_layout)
    RelativeLayout mMemberLayout;
    @BindView(R.id.member_cancel_tv)
    TextView mCancelTv;
    @BindView(R.id.sure_demolition_tv)
    TextView mSureTv;
    @BindView(R.id.member_discount_tv)
    TextView mMemberDisCountTv;
    @BindView(R.id.member_jf_tv)
    TextView mMemberJFTv;
    @BindView(R.id.member_discount_layout)
    LinearLayout mDiscountLayout;
    @BindView(R.id.coupon_tv)
    TextView mCouponTv;
    @BindView(R.id.member_price_checkbox)
    CheckBox mPriceCheckBox;
    @BindView(R.id.member_discount_checkbox)
    CheckBox mDiscountCheckBox;

    CouponAdapter mAdapter;
    InputNumberView mInputNumberView;
    private List<Couponbean> mList;
    private SettalOrderbean mOrderbean;
    private Member mMember;

    @Override
    protected int initLayout() {
        return R.layout.pay_member_detail_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        mInputNumberView = new InputNumberView(this, mContainer);
        mOrderbean = getIntent().getParcelableExtra(Param.Keys.BILL_CODE);
        mInputNumberView.setResult(this);
        mInputNumberView.setTitleTv("会员验证");
        mInputNumberView.hideUnitTv(true);
        mInputNumberView.setHint("请刷卡、扫码或输入卡号/手机号码");
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(ms);
        mAdapter = new CouponAdapter(R.layout.coupon_layout, mList);
        mAdapter.setLisenter(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setSelected(position);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.check_box:
                        mAdapter.setSelected(position);
                        break;
                }
            }
        });
        mAdapter.setSelectModle(true);
        mRecyclerView.setAdapter(mAdapter);
        singleClickOnMinutes(mBackTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.getInstance().putString(mOrderbean.getBill_code(), "");
                setResult(Param.Keys.NUMBER_RESULT, new Intent());
                finish();
            }
        });
        singleClickOnMinutes(mCancelTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.getInstance().put(mOrderbean.getBill_code() + "use", false);
                setResult(Param.Keys.NUMBER_RESULT, new Intent());
                finish();
            }
        });
        singleClickOnMinutes(mSureTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.getInstance().put(mOrderbean.getBill_code() + "use", true);
                SPUtil.getInstance().putString(mOrderbean.getBill_code() + "discount", getFinalDiscountMoney());
                SPUtil.getInstance().putString(mOrderbean.getBill_code() + "discount_type", mMember.getGrade_discount());
                setResult(Param.Keys.NUMBER_RESULT, new Intent());
                finish();
            }
        });

    }

    private String getFinalDiscountMoney() {
        float finalMoney = 0f;
        if (mDiscountCheckBox.isChecked()) {
            switch (mMember.getGrade_discount()) {
                case "1":
                case "2":
                    finalMoney += DishesUtils.getDiscountMoney(mMember, mOrderbean.getDishes());
                    break;
                case "3":
                    finalMoney += DishesUtils.getVipPrice(mOrderbean.getSalePrice(), mOrderbean.getDishes());
                    break;
            }
        }
        if (mPriceCheckBox.isChecked()) {
            finalMoney += getJfMonoey(mMember);
        }

        if (!"0".equals(mAdapter.getVoucher_money()) && !"null".equals(mAdapter.getVoucher_money()) && !TextUtils.isEmpty(mAdapter.getVoucher_money())) {
            finalMoney += AppUtil.getFloatFromString(mAdapter.getVoucher_money()).floatValue();
        }
        AppLog.print(finalMoney + "!!!!");
        return finalMoney + "";
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        String msg = SPUtil.getInstance().getString(mOrderbean.getBill_code());
        if (!TextUtils.isEmpty(msg)) {
            mMemberLayout.setVisibility(View.VISIBLE);
            Member member = (Member) JSONManager.getInstance().parseObject(msg, Member.class);
            init(member);
        } else {
            mMemberLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSure(String number) {
        mPresenter.searchMember(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, number + "");
    }

    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void onResult(Member member) {
        SPUtil.getInstance().put(mOrderbean.getBill_code(), JSONManager.getInstance().toJson(member));
        init(member);
    }

    private void init(Member member) {
        this.mMember = member;
        mContainer.setVisibility(View.GONE);
        mMemberLayout.setVisibility(View.VISIBLE);
        if (member != null) {
            mMemberPhone.setText(member.getMobile());
            if (TextUtils.isEmpty(member.getCard_number()) || "0".equals(member.getCard_number())) {
                mCardNumLayout.setVisibility(View.GONE);
            } else {
                mCardNum.setText(member.getCard_number());
            }
            mStoredValue.setText(Param.Keys.RMB + member.getMoney());
            mElseStoredValue.setText(
                    "  (  本金 " + member.getMoney_principal() +
                            "   赠送 :" + member.getMoney_give() + ")");
            mIntegralBalance.setText(member.getIntegral() + "");
        }
        switch (member.getGrade_discount()) {
            case "1":
                mDiscountLayout.setVisibility(View.GONE);
//                break;
            case "2":
                mDiscountLayout.setVisibility(View.VISIBLE);
                mMemberDisCountTv.setText(String.format("使用会员折扣 %s 折，优惠￥%s", member.getGrade_discount_than(), DishesUtils.getDiscountMoney(member, mOrderbean.getDishes())));
                break;
            case "3":
                mDiscountLayout.setVisibility(View.VISIBLE);
                mMemberDisCountTv.setText(String.format("菜品会员价，优惠￥%s", DishesUtils.getVipPrice(mOrderbean.getSalePrice(), mOrderbean.getDishes())));
                break;
        }

        int maxJfMoney = getJfMonoey(member);
        int finalJf = maxJfMoney * AppUtil.getFloatFromString(member.getIntegral_offset()).intValue();
        mMemberJFTv.setText(String.format("会员有 %s 积分，本次可使用    %s  积分，可抵扣￥ %s 元", member.getIntegral(), finalJf, maxJfMoney));
        if (member.getVoucher() != null && member.getVoucher().size() != 0) {
            mAdapter.setNewData(member.getVoucher());
            mCouponTv.setText(String.format("使用代金券，减￥%s", member.getVoucher().get(0).getVoucher_money()));
        }
    }

    private int getJfMonoey(Member member) {
        //最多抵扣 多少钱
        int maxOrderMoney = (int) (mOrderbean.getSalePrice() * AppUtil.getFloatFromString(member.getIntegral_offset_than()).intValue() / 100);
        // 积分最多则算 的钱数目
        int maxJfMoney = AppUtil.getFloatFromString(member.getIntegral()).intValue() / AppUtil.getFloatFromString(member.getIntegral_offset()).intValue();
        if (maxJfMoney > maxOrderMoney) {
            maxJfMoney = maxOrderMoney;
        }
        return maxJfMoney;
    }


    @Override
    public void onExChanageResult(String msg) {

    }

    @Override
    public void onRefundResult(String msg) {

    }

    @Override
    public void onDeleteResult(String msg) {

    }

    @Override
    public void onFrozenResult(String msg) {

    }

    @Override
    public void onGetMenbersCallback(List<Member> list) {

    }

    @Override
    protected MemberDetailPresenter onCtreatPresenter() {
        return new MemberDetailPresenter(this);
    }

    @Override
    public void onItemSelected(int position) {

    }

    @Override
    public void onNotifyItem() {
        mCouponTv.setText(String.format("使用代金券，减￥%s", mAdapter.getVoucher_money()));
    }
}
