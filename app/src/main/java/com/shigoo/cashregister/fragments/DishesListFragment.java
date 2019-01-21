package com.shigoo.cashregister.fragments;

import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.DishesFormatListAdapter;
import com.shigoo.cashregister.adapters.DishesListAdapter;
import com.shigoo.cashregister.adapters.DishesTypeListAdapter;
import com.shigoo.cashregister.adapters.RemarkListAdapter;
import com.shigoo.cashregister.customViews.FormatView;
import com.shigoo.cashregister.customViews.SetMealView;
import com.shigoo.cashregister.mvp.contacts.DishesListContact;
import com.shigoo.cashregister.mvp.presenter.DishesListPresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.bean.Specifications;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Name: DishesListFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-14 21:35
 */
public class DishesListFragment extends MvpFragment<DishesListPresenter> implements DishesListContact.view, FormatView.onButtonClick {
    @BindView(R.id.ordersheet_dishes_list_view)
    RecyclerView mDishesListView;
    @BindView(R.id.ordersheet_dishes_type_listview)
    RecyclerView mDishesTypeList;
    @BindView(R.id.ordersheet_dishes_search_edite)
    EditText mSearchEdite;
    @BindView(R.id.ordersheet_format_listview)
    RecyclerView mFormateListView;
    @BindView(R.id.ordersheet_format_layout)
    LinearLayout mFormatLayout;
    @BindView(R.id.ordersheet_ramark_layout)
    LinearLayout mRemarkLayout;
    @BindView(R.id.ordersheet_ramark_listview)
    RecyclerView mRemarkListView;
    @BindColor(R.color.ordersheet_colorAccent)
    int selectedColor;
    @BindColor(R.color.ordersheet_table_des_color)
    int nomalColor;
    @BindView(R.id.ordersheet_sigle_remark_tv)
    TextView mSigletv;
    @BindView(R.id.ordersheet_sigle_remark_line)
    View mSigleLine;
    @BindView(R.id.ordersheet_order_remark_tv)
    TextView mOrdertv;
    @BindView(R.id.ordersheet_order_remark_line)
    View mOrderLine;
    @BindView(R.id.ordersheet_set_meal_detail_layout)
    FrameLayout mSetMealDetailLayout;
    List<Remarkbean> orderRemarkList = new ArrayList<>();
    SetMealView mMealView;


    private DishesTypeListAdapter mDishesTypeAdapter;
    private DishesListAdapter mAdapter;
    private DishesFormatListAdapter mFormatListAdapter;
    private List<Specifications> mFormateList = new ArrayList<>();
    private List<Dishesbean> mDishesbeans = new ArrayList<>();
    private List<DishesTypebean> mDishesTypebean = new ArrayList<>();
    private List<Remarkbean> mRemarkList = new ArrayList<>();
    private Dishesbean mCdishesbean;
    private int mCurrentPosition;
    private RemarkListAdapter mRemarkListAdapter;
    private String mRemarkType = "1";


    public static DishesListFragment newInstance() {
        DishesListFragment fragment = new DishesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_dish_list_activity;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mMealView = new SetMealView(getContext(), mSetMealDetailLayout);
        mFormatListAdapter = new DishesFormatListAdapter(R.layout.ordersheet_dishes_format_item_layout, mFormateList);
        mFormateListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mFormateListView.setAdapter(mFormatListAdapter);

        mRemarkListAdapter = new RemarkListAdapter(R.layout.ordersheet_remark_item_layout, mRemarkList);
        mRemarkListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mRemarkListAdapter.setSelected(position);
                if ("1".equals(mRemarkType) && mCdishesbean != null) {
                    List<Remarkbean> list = mCdishesbean.getRemarkbeans();
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    if (!list.contains(mRemarkListAdapter.getItem(position))) {
                        list.add(mRemarkListAdapter.getItem(position));
                    } else {
                        list.remove(mRemarkListAdapter.getItem(position));
                    }
                    mCdishesbean.setRemarkbeans(list);
                    EventBus.getDefault().post(new EventRouter(EventBusAction.DISHES_UPDATE_REMAR, mCdishesbean));
                } else if ("2".equals(mRemarkType)) {
                    if (orderRemarkList.contains(mRemarkListAdapter.getItem(position))) {
                        orderRemarkList.remove(mRemarkListAdapter.getItem(position));
                    } else {
                        orderRemarkList.add(mRemarkListAdapter.getItem(position));
                    }
                    EventBus.getDefault().post(new EventRouter(EventBusAction.ORDER_REMARK, orderRemarkList));
                }
            }
        });
        mRemarkListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRemarkListView.setAdapter(mRemarkListAdapter);
        mFormatListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCdishesbean.setCurrentSp(mFormatListAdapter.getItem(position));
                mFormatListAdapter.setSelected(position);
                mFormatLayout.setVisibility(View.GONE);
                EventBus.getDefault().post(new EventRouter(EventBusAction.DISHES_UPDATE_FORMAT, mCdishesbean));
            }
        });
        mFormatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFormatLayout.setVisibility(View.GONE);
                EventBus.getDefault().post(new EventRouter(EventBusAction.DISHES_UPDATE_FORMAT, mCdishesbean));
            }
        });
        mRemarkLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRemarkLayout.setVisibility(View.GONE);
                EventBus.getDefault().post(new EventRouter(EventBusAction.DISHES_UPDATE_REMAR, mCdishesbean));
            }
        });
        mAdapter = new DishesListAdapter(R.layout.ordersheet_dish_list_item_layout, mDishesbeans);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSetMealDetailLayout.getVisibility() == View.VISIBLE || mFormatLayout.getVisibility() == View.VISIBLE) {
                    return;
                }
                mCdishesbean = mAdapter.getData().get(position);
                mCurrentPosition = position;
                if (mCdishesbean.isMultDishes()) {
                    select(mCdishesbean);
                    mFormatListAdapter.setSelected(0);
                }
                if ("2".equals(mCdishesbean.getDish_tag())) {
                    EventBus.getDefault().post(new EventRouter(EventBusAction.ADD_DISHES, mCdishesbean));
                } else {
                    mSetMealDetailLayout.setVisibility(View.VISIBLE);
                    mMealView.setSetMealbean(mCdishesbean, false);
                }

            }
        });
        mDishesListView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mDishesListView.setAdapter(mAdapter);
        mDishesTypeAdapter = new DishesTypeListAdapter(R.layout.ordersheet_table_dishes_item_layout, mDishesTypebean);
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
        mDishesTypeList.setLayoutManager(new LinearLayoutManager(getContext()));
        mDishesTypeList.setAdapter(mDishesTypeAdapter);
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
        mSigletv.setTextColor(selectedColor);
        mSigleLine.setBackgroundColor(selectedColor);
    }

    private void select(Dishesbean dishesbean) {
        mFormatLayout.setVisibility(View.VISIBLE);
        mRemarkLayout.setVisibility(View.GONE);
        mFormateList = dishesbean.getDishes();
        mFormatListAdapter.setNewData(mFormateList);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getDishesTypeList(Param.Keys.TOKEN, getToken());
        getRemarkList(mRemarkType);
        getDisheList("", "");
    }

    private void getRemarkList(String type) {
        this.mRemarkType = type;
        mPresenter.getRemarkList(Param.Keys.YUAN, getToken(), Param.Keys.TYPE, type);
    }

    private void getDisheList(String type, String keyword) {
        mPresenter.getDishesList(Param.Keys.TOKEN, getToken(), Param.Keys.CATEGRAY, type, Param.Keys.LIKE_NAME, keyword);
    }

    @Override
    protected DishesListPresenter onCtreatPresenter() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return new DishesListPresenter(this);
    }

    @Override
    public void onDishesListResult(List<Dishesbean> dishesbeans) {
        mAdapter.setNewData(dishesbeans);
    }

    @Override
    public void onDishesTypeListResult(List<DishesTypebean> dishesTypebeans) {
        dishesTypebeans.add(0, new DishesTypebean("全部", -1));
        dishesTypebeans.add(1, new DishesTypebean("套餐", 0));
        mDishesTypeAdapter.setNewData(dishesTypebeans);
        mDishesTypeAdapter.setSelected(0);
    }

    @Override
    public void onRemarkListResult(List<Remarkbean> remarkbeans) {
        mRemarkList = remarkbeans;
        mRemarkListAdapter.setNewData(remarkbeans);
        if ("1".equals(mRemarkType) && mCdishesbean != null && mCdishesbean.getRemarkbeans() != null) {
            mRemarkListAdapter.setSelected(mCdishesbean.getRemarkbeans());
        } else if ("2".equals(mRemarkType) && orderRemarkList != null) {
            mRemarkListAdapter.setSelected(orderRemarkList);
        }

    }

    /**
     * @param detail
     */
    public void dishbeanDetail(Dishesbean detail) {
        mCdishesbean = detail;
        if (mCdishesbean.isMultDishes()) {
            select(mCdishesbean);
        } else {
            mFormatLayout.setVisibility(View.GONE);
        }
        if (mRemarkListAdapter != null) {
            mRemarkListAdapter.setSelected(detail.getRemarkbeans());
        }
        mSetMealDetailLayout.setVisibility(View.GONE);
    }

    private void showTCUi() {
        mFormatLayout.setVisibility(View.GONE);
        mRemarkLayout.setVisibility(View.GONE);
        mSetMealDetailLayout.setVisibility(View.VISIBLE);
        mDishesListView.setVisibility(View.GONE);
    }

    @Override
    public void onFormatClick(Dishesbean current) {
        select(current);
    }

    @Override
    public void onRemarkClick(Dishesbean current) {
        mRemarkLayout.setVisibility(View.VISIBLE);
        mFormatLayout.setVisibility(View.GONE);
        mRemarkLayout.bringToFront();
        if (mRemarkListAdapter != null) {
            mRemarkListAdapter.setSelected(current.getRemarkbeans());
        }
    }


    @Override
    public void onNumChanage(int i) {

    }

    @Override
    public void onStatusClick(Dishesbean current) {

    }

    @Override
    public boolean delete(Dishesbean current) {
        if ("1".equals(current.getDish_tag())) {
            mSetMealDetailLayout.setVisibility(View.GONE);
        }
        boolean isEmpty = true;
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            Dishesbean dishesbean = mAdapter.getData().get(i);
            if (dishesbean.getDish_tag().equals(current.getDish_tag())) {
                if ("2".equals(dishesbean.getDish_tag())) {
                    if (dishesbean.getId() == current.getId()) {
                        dishesbean.setTotal_local_num(current.getTotal_local_num());
                        mAdapter.notifyItemChanged(i);
                    }
                } else {
                    if (dishesbean.getCombo_id().equals(current.getCombo_id())) {
                        dishesbean.setTotal_local_num(current.getTotal_local_num());
                        mAdapter.notifyItemChanged(i);
                    }
                }
                if (dishesbean.getTotal_local_num() != 0) {
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) {
            mRemarkLayout.setVisibility(View.GONE);
            mFormatLayout.setVisibility(View.GONE);
        }
        return isEmpty;
    }

    @Override
    public void onCopy() {

    }

    @OnClick({R.id.ordersheet_sigle_layout, R.id.ordersheet_order_layout})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_sigle_layout:
                mSigletv.setTextColor(selectedColor);
                mSigleLine.setBackgroundColor(selectedColor);
                mOrdertv.setTextColor(nomalColor);
                mOrderLine.setBackgroundColor(nomalColor);
                getRemarkList("1");
                break;
            case R.id.ordersheet_order_layout:
                mOrdertv.setTextColor(selectedColor);
                mOrderLine.setBackgroundColor(selectedColor);
                mSigletv.setTextColor(nomalColor);
                mSigleLine.setBackgroundColor(nomalColor);
                getRemarkList("2");
                break;
        }
    }

    @Override
    public void onSetMealDishesResult(List<Dishesbean> dishesbeans) {
        if (dishesbeans != null && dishesbeans.size() != 0) {
            SetMealGroupbean setMealGroupbean = new SetMealGroupbean();
            setMealGroupbean.setGroup_dishes(dishesbeans);
            setMealGroupbean.setDishesbean(mCdishesbean);
            EventBus.getDefault().post(new EventRouter(EventBusAction.CHILD, setMealGroupbean));
        }
    }

    public void childDishbeanDetail(SetMealGroupbean childdetail) {
        mCdishesbean = childdetail.getDishesbean();
        mMealView.setSetMealGroupbean(childdetail);
        showTCUi();
    }

    /**
     * @param add
     */
    public void addDishbean(Dishesbean add) {
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            Dishesbean dishesbean = mAdapter.getItem(i);
            if ("2".equals(dishesbean.getDish_tag())) {
                if (dishesbean.getId() == add.getId()) {
                    dishesbean.setTotal_local_num(add.getTotal_local_num());
                    mAdapter.notifyItemChanged(i);
                }
            } else {
                if (dishesbean.getCombo_id().equals(add.getCombo_id())) {
                    dishesbean.setTotal_local_num(add.getTotal_local_num());
                    mAdapter.notifyItemChanged(i);
                }
            }
        }
    }


    /**
     * 修改总数
     *
     * @param data
     */
    public void updateTotalNum(Dishesbean data) {
        for (int a = 0; a < mAdapter.getData().size(); a++) {
            Dishesbean dishesbean = mAdapter.getData().get(a);
            if (("2".equals(dishesbean.getDish_tag()) && dishesbean.getId() == data.getId())
                    || ("1".equals(dishesbean.getDish_tag()) && dishesbean.getCombo_id().equals(data.getCombo_id()))) {
                dishesbean.setTotal_local_num(data.getTotal_local_num());
                mAdapter.notifyItemChanged(a);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mFormatLayout.setVisibility(View.GONE);
            mSetMealDetailLayout.setVisibility(View.GONE);
            mDishesListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getDisheList("", "");
    }
}
