package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.NumberInputActivity;
import com.shigoo.cashregister.adapters.DishesTypeListAdapter;
import com.shigoo.cashregister.adapters.SaleOutMainAdapter;
import com.shigoo.cashregister.customViews.SaleOutLeftView;
import com.shigoo.cashregister.mvp.contacts.SaleOutMainListContact;
import com.shigoo.cashregister.mvp.presenter.SaleOutMainListPresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.SaleOutbean;
import com.xgsb.datafactory.bean.Specifications;
import com.zx.api.api.app.onChildViewClick;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SaleOutMainFragment extends MvpFragment<SaleOutMainListPresenter> implements SaleOutMainListContact.view, onChildViewClick, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.ordersheet_sale_left_container)
    FrameLayout mLeftLayout;
    @BindView(R.id.ordersheet_dishes_list_view)
    RecyclerView mDishesListView;
    @BindView(R.id.ordersheet_dishes_type_listview)
    RecyclerView mDishesTypeListView;
    @BindView(R.id.ordersheet_dishes_search_edite)
    EditText mSearchEdite;
    @BindView(R.id.ordersheet_swiprefresh_layout)
    SwipeRefreshLayout refreshLayout;
    private SaleOutMainAdapter mAdapter;
    SaleOutLeftView mSaleOutView;
    private List<Dishesbean> mDishesbeans = new ArrayList<>();
    private List<DishesTypebean> mDishesTypebean = new ArrayList<>();
    private DishesTypeListAdapter mDishesTypeAdapter;
    private Dishesbean mCurrentDishes;
    private SaleOutbean mCurrent;
    private String mCurrentNumber;

    public static SaleOutMainFragment newInstance() {
        SaleOutMainFragment fragment = new SaleOutMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SaleOutMainListPresenter onCtreatPresenter() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return new SaleOutMainListPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_sale_out_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        refreshLayout.setOnRefreshListener(this);
        EventBus.getDefault().register(this);
        mSaleOutView = new SaleOutLeftView(getContext(), mLeftLayout);
        mSaleOutView.setLisenter(this);
        mAdapter = new SaleOutMainAdapter(R.layout.ordersheet_dish_list_item_layout, mDishesbeans);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OnClick(null, position + "");
            }
        });
        mDishesListView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mDishesListView.setAdapter(mAdapter);
        mDishesTypeAdapter = new DishesTypeListAdapter(R.layout.ordersheet_table_dishes_item_layout, mDishesTypebean);
        mDishesTypeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDishesTypeListView.setAdapter(mDishesTypeAdapter);
        mDishesTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDishesTypeAdapter.setSelected(position);
                if (position == 0) {
                    getDisheList("", "");
                } else {
                    getDisheList(mDishesTypeAdapter.getItem(position).getCategory_id() + "", "");
                }
            }
        });
        mSearchEdite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    getDisheList("", charSequence.toString());
                    mDishesTypeAdapter.setSelected(-1);
                } else {
                    getDisheList("", "");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getDishesTypeList(Param.Keys.TOKEN, getToken());
        getDisheList("", "");
    }

    private void getDisheList(String type, String keyword) {
        mPresenter.getDishesList(Param.Keys.TOKEN, getToken(), Param.Keys.CATEGRAY, type, Param.Keys.LIKE_NAME, keyword);
    }

    @Override
    public void onDishesListResult(List<Dishesbean> dishesbeans) {
        refreshLayout.setRefreshing(false);
        List<Dishesbean> list = new ArrayList<>();
        for (Dishesbean dishesbean : dishesbeans) {
            if ("2".equals(dishesbean.getDish_tag())) {
                AppLog.print(JSONManager.getInstance().toJson(dishesbean));
                for (Specifications specifications : dishesbean.getDishes()) {
                    try {
                        Dishesbean newdishes = (Dishesbean) dishesbean.clone();
                        newdishes.setDishes(null);
                        newdishes.setCurrentSp(specifications);
                        list.add(newdishes);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                list.add(dishesbean);
            }
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void onDishesTypeListResult(List<DishesTypebean> dishesTypebeans) {
        dishesTypebeans.add(0, new DishesTypebean("全部", -1));
        dishesTypebeans.add(1, new DishesTypebean("套餐", 0));
        mDishesTypeAdapter.setNewData(dishesTypebeans);
        mDishesTypeAdapter.setSelected(0);
    }

    @Override
    public void onSaleOutResult(Integer msg) {
        if (mCurrentDishes.getCurrentSp() != null) {
            mCurrentDishes.getCurrentSp().setGuqing(mCurrentNumber);
        } else {
            mCurrentDishes.setCombo_guqing(mCurrentNumber);
        }
        mSaleOutView.addSaleOut(mCurrentDishes, msg);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        refreshLayout.setRefreshing(false);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(EventRouter eventRouter) {
        switch (eventRouter.getAction()) {
            case SET_NUMBER:
                final Numberbean numberbean = (Numberbean) eventRouter.getData();
                if (numberbean.getRequstCode() == 10202) {
                    mCurrentNumber = numberbean.getCurrentNum();
                    mCurrent.setNumber(numberbean.getCurrentNum());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.saleOut(Param.Keys.TYPE, mCurrent.getType(),
                                    Param.Keys.TOKEN, getToken(), Param.Keys.NUMBER, mCurrentNumber,
                                    Param.Keys.DISHES_NAME, mCurrent.getDishes_name(),
                                    Param.Keys.DISHE_ID, mCurrent.getDishes_id());
                        }
                    });
                } else if (numberbean.getRequstCode() == 10203) {
                    Numberbean updateBerbean = (Numberbean) eventRouter.getData();
                    mSaleOutView.updateCurent(updateBerbean.getCurrentNum());
                }
                break;
            case UPDATE_SALE_OUT:
                SaleOutbean saleOutbean = (SaleOutbean) eventRouter.getData();
                mAdapter.update(saleOutbean);
                break;
        }
    }

    @Override
    public void OnClick(String... params) {
        Dishesbean dishesbean = mAdapter.getData().get(Integer.valueOf(params[0]));
        mCurrentDishes = dishesbean;
        mCurrent = mCurrentDishes.toSaleOutbean();
        Intent intent = new Intent(getContext(), NumberInputActivity.class);
        Specifications billbean = dishesbean.getCurrentSp();
        String unit = "份";
        if (billbean != null) {
            unit = billbean.getSpecification();
        }
        Numberbean numberbean = new Numberbean("设置沽清值", mCurrent.getNumber(), unit, 10202);
        intent.putExtra(Param.Keys.NUMBER, numberbean);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mDishesTypeAdapter.setSelected(0);
        getDisheList("", "");
    }
}
