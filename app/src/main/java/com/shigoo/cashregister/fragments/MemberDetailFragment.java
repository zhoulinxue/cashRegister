package com.shigoo.cashregister.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.AddAndUpdateMemberActivity;
import com.shigoo.cashregister.adapters.CouponAdapter;
import com.shigoo.cashregister.customViews.RechargeView;
import com.shigoo.cashregister.mvp.contacts.AddCardContact;
import com.shigoo.cashregister.mvp.contacts.MemberDetailContact;
import com.shigoo.cashregister.mvp.presenter.AddCardPresenter;
import com.shigoo.cashregister.mvp.presenter.MemberDetailPresenter;
import com.shigoo.cashregister.utils.DialogUtil;
import com.xgsb.datafactory.bean.Cardbean;
import com.xgsb.datafactory.bean.Couponbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name: MemberDetailFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-27 12:57
 */
public class MemberDetailFragment extends MvpFragment<MemberDetailPresenter> implements MemberDetailContact.view, AddCardContact.view, RechargeView.RechargeLisenter, DialogInterface.OnDismissListener {
    private String mId;

    @BindView(R.id.member_detail_back_layout)
    View mBackView;
    @BindView(R.id.member_detail_mobile_num)
    TextView mPhoneNum;
    @BindView(R.id.card_type)
    TextView mCardType;
    @BindView(R.id.card_num)
    TextView mCardNum;
    @BindView(R.id.member_level)
    TextView mMemberLevelName;
    @BindView(R.id.growth_value)
    TextView mGrowthValue;
    @BindView(R.id.preferential)
    TextView mYHType;
    @BindView(R.id.integral_balance_value)
    TextView mIntegralBalance;
    @BindView(R.id.stored_value_balance)
    TextView mStoredValue;
    @BindView(R.id.membership_assignment)
    TextView mMembership;
    @BindView(R.id.membership_status)
    TextView mMenberStatus;
    @BindView(R.id.recharge_btn)
    Button mRechargeBtn;
    @BindView(R.id.member_name_tv)
    TextView mNameText;
    @BindView(R.id.member_birthday)
    TextView mBirthdayTv;
    @BindView(R.id.card_opening_time)
    TextView mCardOpTime;
    @BindView(R.id.consume_num)
    TextView mConsumeNum;
    @BindView(R.id.consumption_amount)
    TextView mAountedNum;
    @BindView(R.id.last_consume_time)
    TextView mLastConsumeTime;
    @BindView(R.id.card_num_layout)
    View mCardNumLayout;
    @BindView(R.id.coupon_available)
    TextView mCouponAvaliable;
    @BindView(R.id.coupon_recyclerview)
    RecyclerView mRecyclerView;
    CouponAdapter mAdapter;
    @BindView(R.id.freeze_tv)
    TextView mFroenTv;
    @BindView(R.id.member_detail_layout)
    LinearLayout mMainLayout;
    @BindView(R.id.input_card_layout)
    RelativeLayout mCardInputLayout;
    @BindView(R.id.input_edite)
    EditText mInputEdite;
    @BindView(R.id.member_detail_bottom_layout)
    LinearLayout mBottomLayout;
    private List<Couponbean> mList;
    private Member mMber;
    private RechargeView mRechargeView;
    private Dialog mChanageDialog, mTwoBtnDialog;
    private View mChanageLayout;
    private EditText mCardNumEditText;
    private TextView mNumTextview;
    private boolean isExcute = false;
    private AddCardPresenter mCardPresenter;
    private int mFroenType = 1;
    private String mCurrentBtn;
    boolean isQueryMember = false;


    public static MemberDetailFragment newInstance() {
        return newInstance("充值");
    }

    public static MemberDetailFragment newInstance(String action) {
        MemberDetailFragment fragment = new MemberDetailFragment();
        Bundle args = new Bundle();
        args.putString(Param.Keys.ACTION, action);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResult(Member member) {
        mMber = member;
        mRechargeView.setUserInfo(member);
        mLastConsumeTime.setText(member.getLast_consumption_time());
        mConsumeNum.setText(member.getConsumption_count());
        mAountedNum.setText(member.getConsumption_money_count());
        mCardOpTime.setText(member.getOpen_card_time());
        mBirthdayTv.setText(member.getBirthday());
        mNameText.setText(member.getName());
        if ("1".equals(member.getStatus_type())) {
            mMenberStatus.setText("挂失/注销");
            mFroenTv.setText("解冻");
            mFroenType = 2;
        } else {
            mMenberStatus.setText("正常");
        }
        mMembership.setText(member.getMembership() + "");
        mStoredValue.setText(Param.Keys.RMB + member.getMoney() +
                "  (  本金 " + member.getMoney_principal() +
                "   赠送 :" + member.getMoney_give() + ")");
        mIntegralBalance.setText(member.getIntegral() + "");
        mGrowthValue.setText(member.getGrowth_value() + "");
        mPhoneNum.setText(member.getMobile());
        if (member.getCard_number().equals("0") || TextUtils.isEmpty(member.getCard_number())) {
            mCardNumLayout.setVisibility(View.GONE);
        } else {
            mCardNum.setText(member.getCard_number());
        }
        String couponTv = String.format(getResources().getString(R.string.coupons_available), member.getVoucher().size() + "");
        mCouponAvaliable.setText(couponTv);
        mMemberLevelName.setText(member.getGrade_name());
        mYHType.setText(member.getGrade_discount_than_name());
        mAdapter.setNewData(member.getVoucher());
        mCardInputLayout.setVisibility(View.GONE);
        mMainLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onExChanageResult(String msg) {
        showToast(msg);
        onRefresh();
    }

    @Override
    public void onRefundResult(String msg) {
        showToast(msg);
        onRefresh();
    }

    @Override
    protected MemberDetailPresenter onCtreatPresenter() {
        return new MemberDetailPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.member_detail_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        mCardPresenter = new AddCardPresenter(this);
        mRechargeView = new RechargeView(getContext(), this);
        LinearLayoutManager ms = new LinearLayoutManager(view.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(ms);
        mAdapter = new CouponAdapter(R.layout.coupon_layout, mList);
        mRecyclerView.setAdapter(mAdapter);
        mChanageLayout = LayoutInflater.from(getContext()).inflate(R.layout.chanage_card_layout, null);
        mCardNumEditText = mChanageLayout.findViewById(R.id.input_edite);
        mCardNumEditText.setInputType(InputType.TYPE_NULL);
        mNumTextview = mChanageLayout.findViewById(R.id.card_codeing_num);
        mCardNumEditText.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                            mCardPresenter.getCardMsg(Param.Keys.INFO, mCardNumEditText.getText().toString());
                            return true;
                        }
                        return false;
                    }
                }
        );
        TextView cancelTv = mChanageLayout.findViewById(R.id.dialog_left_tv);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChanageDialog.dismiss();
            }
        });
        TextView surelTv = mChanageLayout.findViewById(R.id.dialog_right_tv);
        surelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.exChanageCard(Param.Keys.TOKEN, getToken(), Param.Keys.id, mMber.getId() + "", Param.Keys.ENTITY_CARD_NUM, mNumTextview.getText().toString());
            }
        });
        mInputEdite.setInputType(InputType.TYPE_NULL);
        mInputEdite.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    isQueryMember = true;
                    mCardPresenter.getCardMsg(Param.Keys.INFO, textView.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mBottomLayout.setVisibility(View.GONE);
        mCurrentBtn = getArguments().getString(Param.Keys.ACTION);
        mRechargeBtn.setText(mCurrentBtn);
        if (!"消费详情".equals(mCurrentBtn)) {
            mMainLayout.setVisibility(View.VISIBLE);
            mCardInputLayout.setVisibility(View.GONE);
            mBottomLayout.setVisibility(View.VISIBLE);
        } else {
            mMainLayout.setVisibility(View.GONE);
            mCardInputLayout.setVisibility(View.VISIBLE);
            mBottomLayout.setVisibility(View.GONE);
        }
    }

    public void updateId(String id) {
        this.mId = id;
        mCurrentBtn = "充值";
        mRechargeBtn.setText(mCurrentBtn);
        mMainLayout.setVisibility(View.VISIBLE);
        mCardInputLayout.setVisibility(View.GONE);
        mPresenter.getMemberDetail(Param.Keys.TOKEN, getToken(), Param.Keys.id, mId);
    }

    @OnClick({R.id.member_detail_back_layout, R.id.freeze_tv, R.id.refund_tv, R.id.cancellation_tv, R.id.recharge_btn, R.id.change_card, R.id.update_member_msg})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.member_detail_back_layout:
                if (!AppUtil.isOrderDishes(getContext())) {
                    org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_DETAIL_BACK));
                } else {
                    quitUser();
                    org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MAIN));
                }
                break;
            case R.id.freeze_tv:
                String notice = "";
                switch (mFroenType) {
                    case 1:
                        notice = "确认要冻结该会员吗?";
                        break;
                    case 2:
                        notice = "确认要解除冻结状态么?";
                        break;
                }
                mTwoBtnDialog = DialogUtil.showDialogTwoButton(getActivity(), "提示", notice, "取消", null, "继续", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.memberFrozen(Param.Keys.id, mMber.getId() + "", Param.Keys.STATUS_TYPE, mFroenType + "");
                    }
                });
                mTwoBtnDialog.setOnDismissListener(this);
                break;
            case R.id.refund_tv:
                mTwoBtnDialog = DialogUtil.showDialogTwoButton(getActivity(), "确认要退款吗?",
                        " 退款金额: " + Param.Keys.RMB + mMber.getMoney_principal() + "\n 积分扣除：" + mIntegralBalance.getText() + "\n 成长值：" + mGrowthValue.getText() + "\n" + "会员等级：" + mMemberLevelName.getText(), "取消", null, "确认退款", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mPresenter.refund(Param.Keys.TOKEN, getToken(), Param.Keys.id, mMber.getId() + "", Param.Keys.SELLING_ID, "", Param.Keys.PERSION_IN_CHARGE, "");
                            }
                        });
                mTwoBtnDialog.setOnDismissListener(this);
                break;
            case R.id.cancellation_tv:
                mTwoBtnDialog = DialogUtil.showDialogTwoButton(getActivity(), "确认要注销吗?", "退款金额:" + Param.Keys.RMB + mMber.getMoney_principal(), "取消", null, "确认注销", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.deleteMember(Param.Keys.id, mMber.getId() + "");
                    }
                });
                mTwoBtnDialog.setOnDismissListener(this);
                break;
            case R.id.recharge_btn:
                if ("充值".equals(mRechargeBtn.getText().toString())) {
                    mRechargeView.show();
                } else {
                    EventBus.getDefault().post(new EventRouter(EventBusAction.CONSUM_DETAIL, mMber.getId()));
                }
                break;
            case R.id.change_card:
                if (mChanageDialog == null) {
                    mChanageDialog = DialogUtil.contentDialog(getActivity(), mChanageLayout);
                } else {
                    mChanageDialog.show();
                }
                mChanageDialog.setOnDismissListener(this);
                break;
            case R.id.update_member_msg:
                Intent intent = new Intent(getActivity(), AddAndUpdateMemberActivity.class);
                intent.putExtra(Param.Keys.MEMBER_DATA, mMber);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResult(List<Cardbean> cardbean) {

    }

    @Override
    public void onAddresult(String msg) {

    }

    @Override
    public void onDeleteResult(String msg) {
        showToast(msg);
        org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_DETAIL_BACK));
    }

    @Override
    public void onFrozenResult(String msg) {
        showToast(msg);
        updateId(mId);
    }

    @Override
    public void onGetMenbersCallback(List<Member> list) {
        if (list != null && list.size() != 0) {
            updateId(list.get(0).getId() + "");
        } else {
            showToast("会员未找到");
        }
    }


    @Override
    public void onCodeCardError(String msg) {

    }

    @Override
    public void onCardMsg(Cardbean cardbean) {
        if (isQueryMember) {
            isQueryMember = false;
            mPresenter.searchMember(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, cardbean.getCoding_card() + "");
        } else {
            mNumTextview.setText(cardbean.getCoding_card());
            mCardNumEditText.setText("");
            isExcute = false;
            hideInputMethod();
        }

    }

    @Override
    public void onResultSuc() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (!TextUtils.isEmpty(mId)) {
            mPresenter.getMemberDetail(Param.Keys.TOKEN, getToken(), Param.Keys.id, mId);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(EventRouter event) {
        switch (event.getAction()) {
            case MEMBER_MSG:
                updateId(mId);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        hideInputMethod();
    }

    @Override
    public boolean onBackPress() {
        if (mTwoBtnDialog != null && mTwoBtnDialog.isShowing()) {
            mTwoBtnDialog.dismiss();
            return true;
        }
        if (mChanageDialog != null && mChanageDialog.isShowing()) {
            mChanageDialog.dismiss();
            return true;
        }
        if (mRechargeView != null && mRechargeView.isShowing()) {
            mRechargeView.dismiss();
            return true;
        }

        return super.onBackPress();
    }
}
