package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.MemberLevelsAdapter;
import com.shigoo.cashregister.mvp.contacts.AddCardContact;
import com.shigoo.cashregister.mvp.contacts.MemberMsgContact;
import com.shigoo.cashregister.mvp.presenter.AddCardPresenter;
import com.shigoo.cashregister.mvp.presenter.MemberMsgPresenter;
import com.xgsb.datafactory.bean.Cardbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.MemberLevel;
import com.xgsb.datafactory.bean.Sellerbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name: AddAndUpdateMemberActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-06 14:42
 */
public class AddAndUpdateMemberActivity extends MvpActivity<MemberMsgPresenter> implements MemberMsgContact.view, AddCardContact.view {
    @BindView(R.id.member_mobile_tv)
    TextView mMobileTv;
    @BindView(R.id.entity_card_num_edite)
    TextView mEntityCardTv;
    @BindView(R.id.entity_card_tv_true)
    TextView mEntityCardTrue;
    @BindView(R.id.entity_card_tv_false)
    TextView entityCardFlase;
    @BindView(R.id.card_num_layout)
    LinearLayout cardNumberlayout;
    @BindView(R.id.input_edite)
    EditText mInputEdite;
    @BindView(R.id.input_layout)
    RelativeLayout mInputLayout;
    @BindView(R.id.input_card_layout)
    RelativeLayout mCardInputLayout;
    @BindView(R.id.levels_recyclerView)
    RecyclerView mLevelsRecyclerView;
    @BindView(R.id.trans_layout)
    RelativeLayout mTransLayout;
    @BindView(R.id.address_edite)
    TextView mAddressEdite;
    @BindView(R.id.zhiye_edite)
    TextView mZyEdite;
    @BindView(R.id.id_card_edite)
    TextView mIDCardEdite;
    @BindView(R.id.youxiang_edite)
    TextView mPostEdite;
    @BindView(R.id.birth_day_tv)
    TextView mBirthDayTv;
    @BindView(R.id.name_edite)
    EditText mNameEdite;
    @BindView(R.id.man_tv)
    TextView mManTv;
    @BindView(R.id.lady_tv)
    TextView mLadyTv;
    @BindView(R.id.seller_tv)
    TextView mSellerTv;

    private int mCardType = 1;
    private AddCardPresenter mCardPresenter;
    private MemberLevelsAdapter mMemberLevelsAdapter;
    private List<MemberLevel> mLevels;
    private int mSexType = 1;
    private Sellerbean mSellerbean;
    private TimePickerView mTimePicker;
    String mDafultTime = DateUtil.format(DateUtil.getStartTime(), DateUtil.YEAR_MONTH_DAY_PATTERN);
    private Member mMember;


    @Override
    public void onAddSuc(String msg) {
        showToast(msg);
        org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_MSG));
        finish();
    }

    @Override
    public void onUpdateSuc(String msg) {
        showToast(msg);
        org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_MSG));
        finish();
    }

    @Override
    public void onLevelList(List<MemberLevel> levels) {
        mLevels = levels;
        mMemberLevelsAdapter.setNewData(levels);
        if (mMember != null) {
            int positon = mMemberLevelsAdapter.setSelected(mMember.getGrade_discount());
            mLevelsRecyclerView.scrollToPosition(positon);
        }
    }

    @Override
    protected MemberMsgPresenter onCtreatPresenter() {
        return new MemberMsgPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.new_member_card_diaolog_layout;
    }

    @Override
    protected boolean isHideInput() {
        return false;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
        mCardPresenter = new AddCardPresenter(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (AppUtil.getScreenHeight(this) * 0.65), RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTransLayout.setLayoutParams(layoutParams);
        mInputEdite.setInputType(InputType.TYPE_NULL);
        mInputEdite.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    mCardPresenter.getCardMsg(Param.Keys.INFO, mInputEdite.getText().toString());
                    return true;
                }
                return false;
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLevelsRecyclerView.setLayoutManager(manager);
        mMemberLevelsAdapter = new MemberLevelsAdapter(R.layout.member_level_item_layout, mLevels);
        mLevelsRecyclerView.setAdapter(mMemberLevelsAdapter);
        setSex(mSexType);
        initBirthDayPicker();
    }

    private void initBirthDayPicker() {
        mTimePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDafultTime = DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN);
                mBirthDayTv.setText(DateUtil.format(date, DateUtil.YEAR_MONTH_DAY_PATTERN));
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).isDialog(true).build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(500, 400,
                Gravity.CENTER_VERTICAL);
        params.leftMargin = 0;
        params.rightMargin = 0;
        mTimePicker.getDialogContainerLayout().setLayoutParams(params);
        mTimePicker.setTitleText("选择时间");
        mTimePicker.setDate(getDateFromString(mDafultTime));
    }

    private Calendar getDateFromString(String dafultTime) {
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(DateUtil.parse(dafultTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
        } catch (Exception e) {
            e.printStackTrace();
            date.setTime(DateUtil.parse(this.mDafultTime, DateUtil.YEAR_MONTH_DAY_PATTERN));
        }
        return date;
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getMemberLevelList(Param.Keys.TOKEN, getToken());
        if (getIntent() != null && getIntent().hasExtra(Param.Keys.MEMBER_DATA)) {
            mMember = getIntent().getParcelableExtra(Param.Keys.MEMBER_DATA);
            initMember(mMember);
        }
    }

    private void initMember(Member member) {
        mTimePicker.setDate(getDateFromString(mMember.getBirthday()));
        mMobileTv.setText(member.getMobile());
        int positon = mMemberLevelsAdapter.setSelected(member.getGrade_discount());
        mLevelsRecyclerView.scrollToPosition(positon);
        if (!TextUtils.isEmpty(member.getCoding_card())) {
            setCardType(1);
            mEntityCardTv.setText(member.getCoding_card());
        } else {
            setCardType(2);
        }
        mSellerbean = member.getSales();
        if (mSellerbean != null) {
            mSellerTv.setText(mSellerbean.getStaff_name());
        }
        mNameEdite.setText(member.getName());
        if (!TextUtils.isEmpty(member.getSex())) {
            setSex(Integer.valueOf(member.getSex()));
        }
        mDafultTime = member.getBirthday();
        mBirthDayTv.setText(member.getBirthday());
        mPostEdite.setText(member.getMail());
        mIDCardEdite.setText(member.getId_card());
        mZyEdite.setText(member.getOccupation());
        mAddressEdite.setText(member.getAddress());
    }

    @OnClick({R.id.entity_card_tv_true, R.id.entity_card_tv_false, R.id.entity_card_num_edite, R.id.bg_layout, R.id.dialog_right_tv, R.id.dialog_left_tv, R.id.birth_day_tv, R.id.seller_tv, R.id.man_tv, R.id.lady_tv})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.entity_card_tv_false:
                setCardType(2);
                break;
            case R.id.entity_card_tv_true:
                setCardType(1);
                break;
            case R.id.man_tv:
                setSex(1);
                break;
            case R.id.lady_tv:
                setSex(2);
                break;
            case R.id.entity_card_num_edite:
                if (mMember == null) {
                    mCardInputLayout.setVisibility(View.VISIBLE);
                    mInputLayout.setVisibility(View.GONE);
                }
                hideInputMethod();
                break;
            case R.id.dialog_right_tv:
                String sellerId = mSellerbean == null ? "" : mSellerbean.getId() + "";
                if (mMember == null) {
                    mPresenter.addMember(Param.Keys.TOKEN, getToken(),
                            Param.Keys.CARD_TYPE, mCardType + "",
                            Param.Keys.MOBILE, mMobileTv.getText().toString(),
                            Param.Keys.GRADE_ID, mMemberLevelsAdapter.getCurrentLevel(),
                            Param.Keys.ENTITY_CARD_NUM, mEntityCardTv.getText().toString(),
                            Param.Keys.BIRTHDAY, mBirthDayTv.getText().toString(),
                            Param.Keys.MAILS, mPostEdite.getText().toString(),
                            Param.Keys.SEX, mSexType + "",
                            Param.Keys.NAME, mNameEdite.getText().toString(),
                            Param.Keys.ID_CARD, mIDCardEdite.getText().toString(),
                            Param.Keys.ZHIYE, mZyEdite.getText().toString(),
                            Param.Keys.ADDRESS, mAddressEdite.getText().toString(), Param.Keys.SELLING_ID, sellerId);
                } else {
                    sellerId = mSellerbean == null ? mMember.getSeller_Id() : mSellerbean.getId() + "";
                    mPresenter.updateMember(Param.Keys.TOKEN, getToken(), Param.Keys.id, mMember.getId() + "",
                            Param.Keys.CARD_TYPE, mCardType + "",
                            Param.Keys.NAME, mNameEdite.getText().toString(),
                            Param.Keys.MOBILE, mMobileTv.getText().toString(),
                            Param.Keys.GRADE_ID, mMemberLevelsAdapter.getCurrentLevel(),
                            Param.Keys.ENTITY_CARD_NUM, mEntityCardTv.getText().toString(),
                            Param.Keys.BIRTHDAY, mBirthDayTv.getText().toString(),
                            Param.Keys.MAILS, mPostEdite.getText().toString(),
                            Param.Keys.SEX, mSexType + "",
                            Param.Keys.ID_CARD, mIDCardEdite.getText().toString(),
                            Param.Keys.ZHIYE, mZyEdite.getText().toString(),
                            Param.Keys.ADDRESS, mAddressEdite.getText().toString(), Param.Keys.SELLING_ID, sellerId);
                }
                break;
            case R.id.dialog_left_tv:
                finish();
                break;
            case R.id.birth_day_tv:
                mTimePicker.setDate(getDateFromString(mDafultTime));
                mTimePicker.show();
                break;
            case R.id.seller_tv:
                startActivity(new Intent(this, SellerSearchActivity.class));
                break;
            case R.id.bg_layout:
                hideInputMethod();
                break;
        }
    }

    private void setCardType(int i) {
        mCardType = i;
        if (i == 1) {
            entityCardFlase.setTextColor(getResources().getColor(R.color.menber_num_decript_color));
            entityCardFlase.setBackgroundResource(R.drawable.card_level_bg);
            mEntityCardTrue.setTextColor(getResources().getColor(R.color.colorAccent));
            mEntityCardTrue.setBackgroundResource(R.drawable.card_level_bg_selected);
            cardNumberlayout.setVisibility(View.VISIBLE);
        } else {
            mEntityCardTrue.setTextColor(getResources().getColor(R.color.menber_num_decript_color));
            mEntityCardTrue.setBackgroundResource(R.drawable.card_level_bg);
            entityCardFlase.setTextColor(getResources().getColor(R.color.colorAccent));
            entityCardFlase.setBackgroundResource(R.drawable.card_level_bg_selected);
            cardNumberlayout.setVisibility(View.GONE);
        }
    }

    private void setSex(int i) {
        mSexType = i;
        if (i == 1) {
            mLadyTv.setTextColor(getResources().getColor(R.color.menber_num_decript_color));
            mLadyTv.setBackgroundResource(R.drawable.card_level_bg);
            mManTv.setTextColor(getResources().getColor(R.color.colorAccent));
            mManTv.setBackgroundResource(R.drawable.card_level_bg_selected);
        } else {
            mManTv.setTextColor(getResources().getColor(R.color.menber_num_decript_color));
            mManTv.setBackgroundResource(R.drawable.card_level_bg);
            mLadyTv.setTextColor(getResources().getColor(R.color.colorAccent));
            mLadyTv.setBackgroundResource(R.drawable.card_level_bg_selected);
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

    }

    @Override
    public void onCodeCardError(String msg) {

    }

    @Override
    public void onCardMsg(Cardbean cardbean) {
        mCardInputLayout.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mMobileTv.clearFocus();
        mEntityCardTv.setText(cardbean.getCoding_card());
        mInputEdite.setText("");
        hideInputMethod();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(EventRouter event) {
        switch (event.getAction()) {
            case SELLER_INFO:
                this.mSellerbean = (Sellerbean) event.getData();
                mSellerTv.setText(mSellerbean.getStaff_name());
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (mCardInputLayout.getVisibility() == View.VISIBLE) {
            mCardInputLayout.setVisibility(View.GONE);
            mInputLayout.setVisibility(View.VISIBLE);
            return;
        }
        super.onBackPressed();
    }
}
