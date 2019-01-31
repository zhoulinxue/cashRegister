package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.GiveReasonActivity;
import com.shigoo.cashregister.adapters.GiveDishesListAdapter;
import com.shigoo.cashregister.adapters.GiveDishesTypeListAdapter;
import com.shigoo.cashregister.adapters.GiveTableListAdapter;
import com.shigoo.cashregister.adapters.SetMealListAdapter;
import com.shigoo.cashregister.adapters.TableAreaListAdapter;
import com.shigoo.cashregister.mvp.contacts.GiveAsGiftContact;
import com.shigoo.cashregister.mvp.presenter.GiveAsGiftPresenter;
import com.shigoo.cashregister.utils.TablesUtil;
import com.xgsb.datafactory.bean.GiveDishesTypebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveAsGiftFragment extends MvpFragment<GiveAsGiftPresenter> implements GiveAsGiftContact.view, SetMealListAdapter.onNumChanage {
    @BindView(R.id.ordersheet_table_area_recyclerview)
    RecyclerView mAreaListView;
    @BindView(R.id.ordersheet_table_recyclerview)
    RecyclerView mListView;
    @BindView(R.id.ordersheet_dishes_type_recyclerview)
    RecyclerView mGiveTypeList;
    @BindView(R.id.ordersheet_dishes_recyclerview)
    RecyclerView mDishesList;
    GiveDishesTypeListAdapter mGiveTypeAdapter;
    GiveDishesListAdapter mGiveDishesAdapter;
    @BindView(R.id.ordersheet_dishes_bootom_count_tv)
    TextView mCountTv;
    @BindView(R.id.ordersheet_dishes_already_gived)
    TextView mAlreadyGived;
    @BindView(R.id.ordersheet_dishes_rest_give)
    TextView mRestGive;
    @BindView(R.id.ordersheet_dishes_bottom_sure)
    TextView mSureBtn;
    private Table mTable;

    GiveTableListAdapter mAdapter;
    TableAreaListAdapter mAreaAdapter;
    private List<TableArea> mAreaList = new ArrayList<>();
    private List<Table> mList = new ArrayList<>();
    private List<GiveDishesTypebean> mGiveTypeData = new ArrayList<>();
    private List<GiveDishesbean> mGiveDishesData = new ArrayList<>();

    public static GiveAsGiftFragment newInstance() {
        GiveAsGiftFragment fragment = new GiveAsGiftFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onTableResult(List<Table> tables) {
       mList=TablesUtil.fillList(tables);
        mAdapter.setNewData(mList);
    }

    @Override
    public void onAreaListResult(List<TableArea> areas) {
        mAreaList.clear();
        mAreaList.add(new TableArea(0, "全部区域"));
        mAreaList.addAll(areas);
        mAreaAdapter.setNewData(mAreaList);
        mAreaAdapter.setSelected(0);
    }

    @Override
    public void onGiveCountResult(List<GiveDishesTypebean> giveDishesbeans) {
        mGiveTypeAdapter.setNewData(giveDishesbeans);
        mGiveTypeAdapter.setSelected(0);
        if (giveDishesbeans != null && giveDishesbeans.size() > 0) {
            getMenuList(giveDishesbeans.get(0).getGiving_id());
            onSelected(giveDishesbeans.get(0));
        }

    }

    private void onSelected(GiveDishesTypebean dishesbean) {
        int rest_money = 0;
        int giving_money = 0;
        String notice = "";
        switch (dishesbean.getCycle()) {
            case "1":
                notice = "本单赠送 ";
                break;
            case "2":
                notice = "本日赠送 ";
                break;
            case "3":
                notice = "本周赠送 ";
                break;
            case "4":
                notice = "本月赠送 ";
                break;
        }
        mAlreadyGived.setText(notice + Param.Keys.RMB + (Integer.valueOf(dishesbean.getGiving_money()) - Integer.valueOf(dishesbean.getRest_money())));
        mRestGive.setText("可赠送商品剩余总额:" + Param.Keys.RMB + dishesbean.getRest_money());
    }

    @Override
    public void onGiveDishesResult(ListData<GiveDishesbean> giveDishesbeans) {
        mGiveDishesAdapter.setNewData(giveDishesbeans.getData());
    }

    @Override
    protected GiveAsGiftPresenter onCtreatPresenter() {
        return new GiveAsGiftPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_give_as_gift_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        mAreaAdapter = new TableAreaListAdapter(R.layout.ordersheet_table_area_item_layout, mAreaList);
        mAreaAdapter = new TableAreaListAdapter(R.layout.ordersheet_table_area_item_layout, mAreaList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAreaListView.setLayoutManager(manager);
        mAreaListView.setAdapter(mAreaAdapter);
        mAdapter = new GiveTableListAdapter(R.layout.ordersheet_give_table_item_layout, mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTable = mAdapter.getData().get(position);
                mAdapter.setSelect(position);
            }
        });
        mListView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mListView.setAdapter(mAdapter);
        mAreaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.getFilter().filter(mAreaAdapter.getData().get(position).getRegion_name());
                mAreaAdapter.setSelected(position);
            }
        });
        //---------------------------------------------
        LinearLayoutManager dishtypeManager = new LinearLayoutManager(getContext());
        dishtypeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mGiveTypeList.setLayoutManager(dishtypeManager);
        mGiveTypeAdapter = new GiveDishesTypeListAdapter(R.layout.ordersheet_table_area_item_layout, mGiveTypeData);
        mGiveTypeList.setAdapter(mGiveTypeAdapter);
        mGiveTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mGiveTypeAdapter.setSelected(position);
                getMenuList(mGiveTypeAdapter.getData().get(position).getGiving_id());
                onSelected(mGiveTypeAdapter.getData().get(position));
            }
        });
        //-----------------------------------------------------------
        mDishesList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mGiveDishesAdapter = new GiveDishesListAdapter(R.layout.ordersheet_give_item_layout, mGiveDishesData);
        mGiveDishesAdapter.setLisenter(this);
        mDishesList.setAdapter(mGiveDishesAdapter);
        mGiveDishesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GiveDishesbean dishesbean = mGiveDishesAdapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.ordersheet_decrice_tv:
                        if (dishesbean.getNum() > 0) {
                            dishesbean.setNum(dishesbean.getNum() - 1);
                        }
                        break;
                    case R.id.ordersheet_add_tv:
                        if (dishesbean.getGiving_income_number() == 0) {
                            showToast("您没有剩余赠送份数");
                            return;
                        }
                        if (dishesbean.getNum() < Integer.valueOf(dishesbean.getGiving_income_number())) {
                            dishesbean.setNum(dishesbean.getNum() + 1);
                        } else {
                            showToast("不能再增加该商品");
                        }
                        break;

                }
                mGiveDishesAdapter.notifyDataSetChanged();
            }
        });
        singleClickOnMinutes(mSureBtn, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTable == null) {
                    showToast("请先选择桌台");
                    return;
                }
                Intent intent = new Intent(getContext(), GiveReasonActivity.class);
                intent.putParcelableArrayListExtra(Param.Keys.GIVE, mGiveDishesAdapter.getSelected());
                intent.putExtra(Param.Keys.TABLE, mTable);
                startActivity(intent);
            }
        });
    }

    private void getMenuList(String giving_id) {
        mPresenter.getMenuList(Param.Keys.TOKEN, getToken(), Param.Keys.STAFF_ID, getUser().getStaff_id(), Param.Keys.GIVING_ID, giving_id);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getAreaList(Param.Keys.TOKEN, getToken());
        mPresenter.getTableList(Param.Keys.TOKEN, getToken());
        mPresenter.getGiveCount(Param.Keys.TOKEN, getToken(), Param.Keys.STAFF_ID, getUser().getStaff_id());
    }

    @Override
    public void onNumChanage() {
        List<GiveDishesbean> selectedItem = mGiveDishesAdapter.getSelected();
        float money = 0;
        if (selectedItem != null) {
            for (GiveDishesbean dishesbean : selectedItem) {
                if (!TextUtils.isEmpty(dishesbean.getSale_price()) && !"0".equals(dishesbean.getSale_price())) {
                    money += dishesbean.getNum() * AppUtil.getFloatFromString(dishesbean.getSale_price()).intValue();
                }
            }
        }
        mCountTv.setText(String.format("已选择 %s 项，共计￥ %s", selectedItem.size(), money));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(final EventBusAction action) {
        switch (action) {
            case GIVE_SUC:
                mAdapter.setSelect(-1);
                mTable = null;
                getMenuList(mGiveTypeAdapter.getSelectedItem().getGiving_id());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
