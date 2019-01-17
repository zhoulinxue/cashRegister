package com.shigoo.cashregister.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.CardListAdapter;
import com.shigoo.cashregister.mvp.contacts.AddCardContact;
import com.shigoo.cashregister.mvp.presenter.AddCardPresenter;
import com.xgsb.datafactory.bean.Cardbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.wedgit.ClearableEditTextWithIcon;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: AddCardFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-30 13:35
 */
public class AddCardFragment extends MvpFragment<AddCardPresenter> implements AddCardContact.view, View.OnClickListener, TextView.OnEditorActionListener {
    @BindView(R.id.card_num_text)
    ClearableEditTextWithIcon mNumEdite;
    @BindView(R.id.card_recyclerview)
    RecyclerView mRecyclerView;
    private List<Cardbean> mList = new ArrayList<>();
    private CardListAdapter mAdapter;
    @BindView(R.id.card_num_radio)
    RadioButton mSingleBtn;
    @BindView(R.id.card_range_radio)
    RadioButton mRangeBtn;
    @BindView(R.id.radioGroup_sex_id)
    RadioGroup mGroup;
    @BindView(R.id.make_card)
    TextView mMakeCard;
    @BindView(R.id.card_num_edite)
    TextView mCardNumEdite;
    @BindView(R.id.add_card_back_layout)
    LinearLayout mBackLayout;
    @BindView(R.id.mult_make_cards)
    LinearLayout mMultLayout;

    LinearLayout mCardlayout, mCardSucLayout;
    private Dialog mCardDialog;
    private TextView mNumTv;
    private TextView mEntityEdite;
    private String mSearchKey = "";
    private TextView mNoticeTv, endTv, cancelTv, mJumpTv;
    private int mDeletePis, mCurrentPis;
    private boolean isExcute = false;


    public static AddCardFragment newInstance() {
        AddCardFragment fragment = new AddCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected AddCardPresenter onCtreatPresenter() {
        return new AddCardPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.add_card_fragment_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CardListAdapter(R.layout.card_list_item_layout, mList);
        View mHeader = LayoutInflater.from(getContext()).inflate(R.layout.card_list_item_header_layout, null);
        mAdapter.addHeaderView(mHeader);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL));
        mNumEdite.setOnEditorActionListener(this);
        mCardNumEdite.setOnEditorActionListener(this);
        mBackLayout.setOnClickListener(this);
        mNumEdite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    mList.clear();
                    mAdapter.setNewData(mList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.delete_card:
                        mDeletePis = position;
                        mPresenter.deleteCard(Param.Keys.TOKEN, getToken(), Param.Keys.id, mAdapter.getData().get(position).getId() + "");
                        break;
                }
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        if (!mRangeBtn.isChecked()) {
            mSingleBtn.setChecked(true);
        }
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.card_range_radio:
                        mMultLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.card_num_radio:
                        mMultLayout.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
        singleClickOnMinutes(mMakeCard, this);
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
    }

    @Override
    public void onResult(List<Cardbean> cardbean) {
        mAdapter.setNewData(cardbean);
        hideInputMethod();
    }

    @Override
    public void onAddresult(String msg) {
        showToast(msg);
        mCardlayout.setVisibility(View.GONE);
        mCardSucLayout.setVisibility(View.VISIBLE);
        mNoticeTv.setText(mAdapter.getData().get(mCurrentPis).getCoding_card());
        mAdapter.getData().get(mCurrentPis).setStatus("2");
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDeleteResult(String msg) {
        showToast(msg);
        mAdapter.remove(mDeletePis);
    }

    @Override
    public void onCodeCardError(String msg) {
        showToast(msg);
        if (!TextUtils.isEmpty(msg) && msg.contains("已存在")) {
            mCardSucLayout.setVisibility(View.VISIBLE);
            mCardlayout.setVisibility(View.GONE);
            mNoticeTv.setText("卡号: " + mAdapter.getData().get(mCurrentPis).getCoding_card() + "  已存在");
        } else {
            mNoticeTv.setText("卡号: " + mAdapter.getData().get(mCurrentPis).getCoding_card() + "  制卡失败");
        }
    }

    @Override
    public void onCardMsg(Cardbean cardbean) {

    }

    private void next() {
        mCurrentPis++;
        if (mCurrentPis < mAdapter.getData().size()) {
            if ("1".equals(mAdapter.getData().get(mCurrentPis).getStatus())) {
                mNumTv.setText(mAdapter.getData().get(mCurrentPis).getCoding_card() + "");
                mEntityEdite.setText("");
            } else {
                mCardSucLayout.setVisibility(View.VISIBLE);
                mCardlayout.setVisibility(View.GONE);
                mNoticeTv.setText("卡号：" + mAdapter.getData().get(mCurrentPis).getCoding_card() + "  已被绑定");
            }
        } else {
            if (mCardDialog != null && mCardDialog.isShowing()) {
                mCardDialog.dismiss();
                showToast("已完成制卡");
                mCurrentPis = 0;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.end_make_card:
            case R.id.cancel_make_card:
                if (mCardDialog != null && mCardDialog.isShowing()) {
                    mCardDialog.dismiss();
                }
                mNumEdite.setText("");
                mCardNumEdite.setText("");
                mList.clear();
                mAdapter.setNewData(mList);
                break;
            case R.id.make_card_con:
                mCardlayout.setVisibility(View.VISIBLE);
                mCardSucLayout.setVisibility(View.GONE);
                mEntityEdite.setText("");
                isExcute = false;
                next();
                break;
            case R.id.make_card:
                if (mAdapter != null && mAdapter.getData().size() != 0) {
                    mCurrentPis = -1;
                    mCardDialog = cardDialog(getContext(), this);
                    next();
                } else {
                    showToast("请输入有效卡号");
                }
                break;
            case R.id.jump_card_num:
                isExcute = false;
                next();
                break;
            case R.id.add_card_back_layout:
                EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_DETAIL_BACK));
                break;
        }
    }

    private Dialog cardDialog(Context context, View.OnClickListener listener) {
        LayoutInflater factory = LayoutInflater.from(context);//提示框
        final View view = factory.inflate(R.layout.add_cards_dialog, null);//这里必须是final的
        mEntityEdite = view.findViewById(R.id.editText);//获得输入框对象
        view.findViewById(R.id.cancel_make_card).setOnClickListener(listener);
        view.findViewById(R.id.end_make_card).setOnClickListener(listener);
        view.findViewById(R.id.make_card_con).setOnClickListener(listener);
        view.findViewById(R.id.jump_card_num).setOnClickListener(listener);
        mCardlayout = view.findViewById(R.id.make_card_layout);
        mCardSucLayout = view.findViewById(R.id.suc_layout);
        mEntityEdite.setInputType(InputType.TYPE_NULL);
        mNumTv = view.findViewById(R.id.num_tv);
        mNoticeTv = view.findViewById(R.id.num_notice_tv);
        mEntityEdite.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    mPresenter.addCardCodingNum(Param.Keys.TOKEN, getToken(), Param.Keys.id, mAdapter.getData().get(mCurrentPis).getId() + "", Param.Keys.ENTITY_CARD_NUM, mEntityEdite.getText().toString());
                    return true;
                }
                return false;
            }
        });
        Dialog dialog = new AlertDialog.Builder(context).setView(view).create();
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            mPresenter.getCardMessage(Param.Keys.CODEING_CARD_NUM, mNumEdite.getText().toString(), Param.Keys.NUMBER, mCardNumEdite.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    public boolean onBackPress() {
        if(mCardDialog!=null&&mCardDialog.isShowing()){
            mCardDialog.dismiss();
            return true;
        }
        return super.onBackPress();
    }
}
