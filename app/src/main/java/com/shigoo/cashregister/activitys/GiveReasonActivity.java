package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.GiveReasonAdapter;
import com.shigoo.cashregister.mvp.contacts.GiveReasonContact;
import com.shigoo.cashregister.mvp.presenter.GiveReasonPresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.AddGivebean;
import com.xgsb.datafactory.bean.BaseGivebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.MvpActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveReasonActivity extends MvpActivity<GiveReasonPresenter> implements GiveReasonContact.view {
    @BindView(R.id.ordersheet_dishes_give_reason_list)
    RecyclerView mReasonListView;
    @BindView(R.id.ordersheet_dishes_bootom_cancel_img)
    ImageView mBackImg;
    @BindView(R.id.ordersheet_dishes_bootom_cancel)
    TextView mCancelTv;
    @BindView(R.id.ordersheet_dishes_bottom_sure)
    TextView mSureTv;
    @BindView(R.id.ordersheet_dishes_reason_edite)
    EditText mReasonEdite;
    GiveReasonAdapter mAdapter;
    List<String> mReasonList = new ArrayList<>();
    List<GiveDishesbean> mSettals = new ArrayList<>();
    private StringBuffer mReason;
    private Table mTable;

    @Override
    public void onResonResult(List<String> resons) {
        mAdapter.setNewData(resons);
    }

    @Override
    public void onAddResult(String msg) {
        showToast(msg);
        EventBus.getDefault().post(EventBusAction.GIVE_SUC);
        finish();
    }

    @Override
    protected GiveReasonPresenter onCtreatPresenter() {
        return new GiveReasonPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_give_reason_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        mSettals = getIntent().getParcelableArrayListExtra(Param.Keys.GIVE);
        mTable = getIntent().getParcelableExtra(Param.Keys.TABLE);
        mReasonListView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new GiveReasonAdapter(R.layout.ordersheet_give_reason_item_layout, mReasonList);
        mReasonListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setSelected(position);
            }
        });
        singleClickOnMinutes(mBackImg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        singleClickOnMinutes(mCancelTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        singleClickOnMinutes(mSureTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReason = mAdapter.getSelected();
                if (!TextUtils.isEmpty(mReasonEdite.getText())) {
                    mReason.append(mReasonEdite.getText().toString());
                }
                AddGivebean addGivebean = new AddGivebean();
                addGivebean.setToken(getToken());
                List<BaseGivebean> baseGivebeans = new ArrayList<>();
                for (GiveDishesbean dishesbean : mSettals) {
                    dishesbean.setGift_reason(mReason.toString());
                    dishesbean.setBill_code(mTable.getCurrentBill("1").getBill_code());
                    dishesbean.setBill_date(mTable.getCurrentBill("1").getBill_date());
                    dishesbean.setDish_qty(dishesbean.getNum());
                    dishesbean.setTable_number(mTable.getTable_number());
                    baseGivebeans.add(dishesbean.toBase());
                }
                addGivebean.setData(baseGivebeans);
                mPresenter.addGive(addGivebean);
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getGiveReson(Param.Keys.TOKEN, getToken());
    }
}
