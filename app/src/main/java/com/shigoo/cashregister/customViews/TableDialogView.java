package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.ChedanAdapter;
import com.shigoo.cashregister.adapters.DazheAdapter;
import com.shigoo.cashregister.adapters.GaijiaAdapter;
import com.shigoo.cashregister.adapters.TuicaiAdapter;
import com.shigoo.cashregister.mvp.contacts.TableDialogContact;
import com.shigoo.cashregister.mvp.presenter.TableDialogPresenter;
import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.zx.mvplibrary.wedgit.MvpCustomView;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TableDialogView extends MvpCustomView<TableDialogPresenter> implements TableDialogContact.view {
    @BindView(R.id.ordersheet_cancel_order_layout)
    LinearLayout mCancelOrderLayout;
    @BindView(R.id.ordersheet_discount_layout)
    LinearLayout mDiscountLayout;
    @BindView(R.id.ordersheet_tuicai_layout)
    LinearLayout mTuiCaiLayout;
    @BindView(R.id.ordersheet_gaijia_layout)
    LinearLayout mGaiJiaLayout;
    @BindView(R.id.chedan_ordersheet_format_listview)
    RecyclerView mChedanRecyclerView;
    @BindView(R.id.tuicai_ordersheet_format_listview)
    RecyclerView mTuicaiRecyclerView;
    @BindView(R.id.gaijia_ordersheet_format_listview)
    RecyclerView mGaijiaRecyclerView;
    @BindView(R.id.dazhe_ordersheet_format_listview)
    RecyclerView mDazheRecyclerView;
    private List<String> chedanList = new ArrayList<>();
    private List<String> dazheList = new ArrayList<>();
    private List<String> tuicaiList = new ArrayList<>();
    private List<String> gaijiaList = new ArrayList<>();
    private ChedanAdapter mChedanAdapter;
    private DazheAdapter mDazheAdapter;
    private TuicaiAdapter mTuicaiAdapter;
    private GaijiaAdapter mGaijiaAdapter;
    private String mBillCode;


    public TableDialogView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }


    @Override
    protected TableDialogPresenter onCtreatPresenter() {
        return new TableDialogPresenter(this);
    }

    @Override
    protected void onInitData() {
        mPresenter.getCancelList(Param.Keys.TOKEN, getToken());
    }

    @Override
    protected void onInitView(Context context, View rootView) {
        EventBus.getDefault().register(this);
        mChedanRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mGaijiaRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mDazheRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mTuicaiRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mChedanAdapter = new ChedanAdapter(R.layout.ordersheet_remark_item_layout, chedanList);
        mChedanRecyclerView.setAdapter(mChedanAdapter);
        mGaijiaAdapter = new GaijiaAdapter(R.layout.ordersheet_remark_item_layout, gaijiaList);
        mGaijiaRecyclerView.setAdapter(mGaijiaAdapter);
        mDazheAdapter = new DazheAdapter(R.layout.ordersheet_remark_item_layout, dazheList);
        mDazheRecyclerView.setAdapter(mDazheAdapter);
        mTuicaiAdapter = new TuicaiAdapter(R.layout.ordersheet_remark_item_layout, tuicaiList);
        mTuicaiRecyclerView.setAdapter(mTuicaiAdapter);
    }

    @Override
    public int initLayout() {
        return R.layout.table_dialog_view_layout;
    }

    @Override
    public void onCancelOrderResult(String listData) {
        showToast(listData);
    }

    @Override
    public void onCancelResult(List<String> resonList) {
        chedanList = resonList;
        mChedanAdapter.setNewData(chedanList);
    }

    @OnClick({R.id.gaijia_ordersheet_close_img,
            R.id.tuicai_ordersheet_close_img,
            R.id.dazhe_ordersheet_close_img,
            R.id.chedan_ordersheet_close_img,
            R.id.chedan_ordersheet_number_result_btn})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.gaijia_ordersheet_close_img:
            case R.id.dazhe_ordersheet_close_img:
            case R.id.chedan_ordersheet_close_img:
            case R.id.tuicai_ordersheet_close_img:
                getView().setVisibility(View.GONE);
                break;
            case R.id.chedan_ordersheet_number_result_btn:
                mPresenter.cancelOrder(new Chedanbean(getToken(), mBillCode, mChedanAdapter.getReason()));
                break;
        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(final EventRouter eventRouter) {
        getView().setVisibility(View.VISIBLE);
        switch (eventRouter.getAction()) {
            case TUI_CAI:
                mTuiCaiLayout.setVisibility(View.VISIBLE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.GONE);
                break;
            case DA_ZHE:
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.VISIBLE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.GONE);
                break;
            case CHE_DAN:
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.VISIBLE);
                break;
            case GAI_JIA:
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.VISIBLE);
                mCancelOrderLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void onDestory() {
        EventBus.getDefault().unregister(this);
    }
}
