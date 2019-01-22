package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.NumberInputActivity;
import com.shigoo.cashregister.adapters.MenuDishesListAdapter;
import com.shigoo.cashregister.adapters.OrderPayListAdapter;
import com.shigoo.cashregister.customViews.viewChildClick.OrderListViewBriage;
import com.shigoo.cashregister.mvp.contacts.MenuDishesListContact;
import com.shigoo.cashregister.mvp.presenter.OrderDishesPresenter;
import com.shigoo.cashregister.utils.DishesUtils;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.ComboData;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.bean.SettalItem;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.enu.DiscountType;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.wedgit.MvpCustomView;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Name: OrderDishMenuListView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-11 12:57
 */
public class OrderDishMenuListView extends MvpCustomView<OrderDishesPresenter> implements MenuDishesListContact.view, FormatView.onButtonClick, MenuDishesListAdapter.onItemSelected {
    @BindView(R.id.ordersheet_menu_recyclerview)
    RecyclerView mMenuList;
    @BindView(R.id.ordersheet_table_title_layout)
    LinearLayout mTitleLayout;
    @BindView(R.id.ordersheet_billbean_name_tv)
    TextView mBillName;
    @BindView(R.id.ordersheet_person_num_tv)
    TextView mPersonNum;
    @BindView(R.id.ordersheet_mult_left_layout)
    FrameLayout mLeftFormatLayout;
    @BindView(R.id.ordersheet_dishesmenu_bottom_layout)
    LinearLayout mBottomlayout;
    @BindView(R.id.ordersheet_empty_menu_tv)
    TextView mEmptyTv;
    @BindView(R.id.ordersheet_dishes_total_num_tv)
    TextView mTotalNumTv;
    @BindView(R.id.ordersheet_billbean_number_tv)
    TextView mBillNumberTv;
    @BindView(R.id.ordersheet_billbean_number_layout)
    LinearLayout mBillNumberLayout;
    @BindView(R.id.ordersheet_order_remark_tv)
    TextView mOrderRemarkTv;
    @BindView(R.id.ordersheet_table_left_delete_img)
    ImageView mDeleteImg;
    @BindView(R.id.ordersheet_settle_order_btn)
    TextView mSettleTv;
    @BindView(R.id.ordersheet_dishes_price_tv)
    TextView mAlreadOrderPriceTv;
    @BindView(R.id.ordersheet_dishes_old_tv)
    TextView mOrderOldPriceTv;
    @BindView(R.id.ordersheet_settle_accounts_btn)
    TextView mSelltalAccountTv;
    @BindView(R.id.cashregister_goods_tv)
    TextView mGoodsTv;
    @BindView(R.id.cashregister_list_tv)
    TextView mListTv;
    @BindColor(R.color.ordersheet_table_des_color)
    int nomalColor;
    @BindColor(R.color.white)
    int selectedColor;
    @BindView(R.id.ordersheet_dishes_finnaly_tv)
    TextView OrderPriceTv;
    @BindView(R.id.cashregister_layout)
    LinearLayout mCasherLayout;
    MenuDishesListAdapter mMenuListAdapter;
    OrderPayListAdapter mPayListAdapter;
    private List<Dishesbean> mDishList = new ArrayList<>();
    private List<Paybean> mPayList = new ArrayList<>();
    private Billbean mBillbean;
    private FormatView mFormateView;
    private Dishesbean mCurrent;
    private int mCurrentPosition;
    private boolean isUpdate = false;
    private Table mTable;
    //1： 下单 2：加菜 3：点菜 4：拆单支付 5:支付页面
    private String settalType = "1";
    private int mPNum;
    private DiscountType mDiscountType = DiscountType.NULL;
    float finalyPrice = 0f;
    float salePrice = 0f;
    private boolean isFjz = false;
    private OrderListViewBriage.onOrderViewClick mClickLister;


    public OrderDishMenuListView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }


    @Override
    public int initLayout() {
        return R.layout.ordersheet_table_left_layout;
    }


    @OnClick({R.id.ordersheet_table_left_back_img,
            R.id.ordersheet_settle_accounts_btn,
            R.id.ordersheet_table_left_delete_img,
            R.id.ordersheet_settle_order_btn,
            R.id.ordersheet_table_title_layout,
            R.id.cashregister_list_tv,
            R.id.cashregister_goods_tv})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.cashregister_goods_tv:
                mGoodsTv.setBackgroundResource(R.drawable.ordersheet_goods_btn_selected_bg);
                mListTv.setBackgroundResource(R.drawable.ordersheet_list_btn_bg);
                mGoodsTv.setTextColor(selectedColor);
                mListTv.setTextColor(nomalColor);
                mMenuList.setAdapter(mMenuListAdapter);
                break;
            case R.id.cashregister_list_tv:
                if (mBillbean != null) {
                    mPresenter.getPayList(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, mBillbean.getBill_code());
                }
                mGoodsTv.setBackgroundResource(R.drawable.ordersheet_goods_btn_bg);
                mListTv.setBackgroundResource(R.drawable.ordersheet_list_btn_selected_bg);
                mListTv.setTextColor(selectedColor);
                mGoodsTv.setTextColor(nomalColor);
                mMenuList.setAdapter(mPayListAdapter);
                break;
            case R.id.ordersheet_table_left_back_img:
                if (mTable == null) {
                    mClickLister.onBacktoMain();
                } else {
                    switch (settalType) {
                        case "1":
                        case "3":
                            mClickLister.onBackToTable();
                            break;
                        case "2":
                        case "4":
                        case "5":
                            setTable(mTable, isFjz);
                            mClickLister.gotoTable();
                            break;
                    }
                }
                break;
            case R.id.ordersheet_settle_order_btn:
                if ("加菜".equals(mSettleTv.getText().toString())) {
                    mCasherLayout.setVisibility(View.GONE);
                    settalType = "2";
                    mSettleTv.setText("下单");
                    mDishList.clear();
                    mFormateView.setLocalStatus("加菜");
                    mMenuListAdapter.setNewData(mDishList);
                    mSelltalAccountTv.setVisibility(View.GONE);
                    setDiscountType(DiscountType.NULL);
                    gotoDishesList();
                } else if ("下单".equals(mSettleTv.getText().toString())) {
                    SettalOrderbean settalOrderbean = new SettalOrderbean();
                    settalOrderbean.setBill_code(mBillbean.getBill_code());
                    settalOrderbean.setSale_id(mBillbean.getSale_id());
                    settalOrderbean.setData(getSettalItem(mMenuListAdapter.getData()));
                    settalOrderbean.setRemark(mOrderRemarkTv.getText().toString().replace("整单备注：", ""));
                    settalOrderbean.setBill_type_info(settalType);
                    settalOrderbean.setOrder_source(AppUtil.getPhoneId(getContext()));
                    mPresenter.settleOrder(settalOrderbean);
                } else {
                    List<Dishesbean> dishesbeans = new ArrayList<>();
                    mMenuListAdapter.setNewData(dishesbeans);
                    mClickLister.onCleanDemolition();
                }
                break;
            case R.id.ordersheet_table_left_delete_img:
                mDishList.clear();
                mMenuListAdapter.setNewData(mDishList);
                break;
            case R.id.ordersheet_table_title_layout:
                Intent intent = new Intent(getContext(), NumberInputActivity.class);
                Numberbean numberbean = new Numberbean("设置包间人数", mBillbean.getGuest_qty(), "人", 10201);
                intent.putExtra(Param.Keys.NUMBER, numberbean);
                getContext().startActivity(intent);
                break;
            case R.id.ordersheet_settle_accounts_btn:
                if ("去结账".equals(mSelltalAccountTv.getText().toString())) {
                    gotopayFragmnet();
                } else if ("已结账".equals(mSelltalAccountTv.getText().toString())) {
                    showToast("已结账");
                    gotopayFragmnet();
                } else if ("拆单支付".equals(mSelltalAccountTv.getText().toString())) {
                    settalType = "4";
                    List<Dishesbean> dishesbeans = new ArrayList<>();
                    mMenuListAdapter.setNewData(dishesbeans);
                    mLeftFormatLayout.setVisibility(View.GONE);
                    mSelltalAccountTv.setText("取消拆单支付");
                    mSettleTv.setVisibility(View.VISIBLE);
                    mSettleTv.setText("清空拆单支付");
                    mClickLister.demolition();
                } else {
                    mClickLister.cancelDemolition();
                }
                break;
        }

    }

    /**
     * 显示 菜单列表界面
     */
    private void gotoDishesList() {
        mClickLister.gotoDishesList();
        for(Dishesbean dishesbean:mMenuListAdapter.getData()){
            mClickLister.updateDishesNum(dishesbean);
        }
    }

    /**
     * 显示支付页面
     */
    private void gotopayFragmnet() {
        settalType = "5";
        List<Dishesbean> noPayeds = mMenuListAdapter.gotoPayedList();
        mMenuListAdapter.setNewData(noPayeds);
        mSelltalAccountTv.setText("拆单支付");
        mSettleTv.setVisibility(View.GONE);
        mClickLister.onPayBtn(getPayOrder());
    }

    private SettalOrderbean getPayOrder() {
        mDishList = mMenuListAdapter.getData();
        SettalOrderbean orderbean = new SettalOrderbean();
        orderbean.setDishes(mDishList);
        orderbean.setBill_code(mTable.getBillbean().getBill_code());
        orderbean.setSalePrice(salePrice);
        orderbean.setFinalyPrice(finalyPrice);
        orderbean.setDiscountType(mDiscountType);
        orderbean.setRestPrice(mMenuListAdapter.getRestPrice());
        return orderbean;
    }

    private List<SettalItem> getSettalItem(List<Dishesbean> data) {
        List<SettalItem> itemCommit = new ArrayList<>();
        for (Dishesbean dishesbean : data) {
            SettalItem item = new SettalItem();
            item.setDish_qty(dishesbean.getLocal_num() + "");
            item.setDish_tag(dishesbean.getDish_tag());
            item.setWait_tag(dishesbean.getWait_tag() + "");
            item.setOut_tag(dishesbean.getOut_tag() + "");
            item.setWhole_flag(dishesbean.getWhole_flag());
            item.setWhole_discount(dishesbean.getWhole_discount());
            item.setWhole_discount_id(dishesbean.getWhole_discount_id());
            item.setSale_id(mBillbean.getSale_id());
            item.setFinally_price(dishesbean.getMinFavorable().getMoney());
            item.setTable_gift_tag(dishesbean.getTable_gift_tag());
            if (dishesbean.getRemarkbeans() != null) {
                item.setRemark_data(setOrderRemark(dishesbean.getRemarkbeans()));
            }
            if ("2".equals(dishesbean.getDish_tag())) {
                item.setTime_price(dishesbean.getCurrentSp().getTime_price());
                item.setDishes_id(dishesbean.getId() + "");
                item.setSpecification_id(dishesbean.getCurrentSp().getId());
                item.setAll_flag(dishesbean.getCurrentSp().getAll_flag());
                item.setAll_discount(dishesbean.getCurrentSp().getAll_discount());
                item.setAll_discount_id(dishesbean.getCurrentSp().getAll_discount_id());
                item.setTag(dishesbean.getCurrentSp().getTag());
                item.setDiscount(dishesbean.getCurrentSp().getDiscount());
                item.setDiscount_type(dishesbean.getCurrentSp().getDiscount_type());
            } else {
                item.setCombo_id(dishesbean.getCombo_id() + "");
                item.setTime_price(dishesbean.getCombo_time_price());
                List<ComboData> idList = new ArrayList<>();
                SetMealGroupbean setMealGroupbean = dishesbean.getMealGroupbean();
                for (Dishesbean dis : setMealGroupbean.getGroup_dishes()) {
                    ComboData childbean = new ComboData();
                    childbean.setId(dis.getId());
                    childbean.setGroup_id("-1".equals(dis.getGroup_id()) ? "1" : "2");
                    childbean.setNum(dis.getNum() + "");
                    idList.add(childbean);
                }
                item.setCombo_data(idList);
            }
            if (!"1".equals(item.getTable_gift_tag())) {
                itemCommit.add(item);
            }
        }
        return itemCommit;
    }

    @Override
    protected OrderDishesPresenter onCtreatPresenter() {
        return new OrderDishesPresenter(this);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onInitView(Context context, View rootView) {
        mMenuListAdapter = new MenuDishesListAdapter(R.layout.ordersheet_menu_list_itme_layout, mDishList);
        mPayListAdapter = new OrderPayListAdapter(R.layout.order_pay_list_item_layout, mPayList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mMenuList.setLayoutManager(manager);
        mMenuList.setAdapter(mMenuListAdapter);
        mFormateView = new FormatView(getContext(), mLeftFormatLayout);
        mFormateView.setClickLisenter(this);
        mMenuListAdapter.setOnItemSelected(this);
        mMenuListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mMenuListAdapter.setCurrentPositon(position);
                mCurrentPosition = position;
                mCurrent = mMenuListAdapter.getData().get(position);
                mClickLister.onDishesDetail(mCurrent);
            }
        });
        mOrderOldPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        showBottomUi();
    }

    private void showBottomUi() {
        if (mDishList == null || mDishList.size() == 0) {
            mMenuList.setVisibility(View.GONE);
            mEmptyTv.setVisibility(View.VISIBLE);
            mBottomlayout.setVisibility(View.GONE);
            mLeftFormatLayout.setVisibility(View.GONE);
            mBottomlayout.setVisibility(View.GONE);
            mDeleteImg.setVisibility(View.GONE);
        } else {
            mLeftFormatLayout.setVisibility(View.VISIBLE);
            mMenuList.setVisibility(View.VISIBLE);
            mEmptyTv.setVisibility(View.GONE);
            mBottomlayout.setVisibility(View.VISIBLE);
            mTotalNumTv.setText("共 " + mDishList.size() + " 项");
            mBottomlayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onOrderDishesListResult(SettalOrderResultbean dishesbeans) {
        if (dishesbeans == null || dishesbeans.getData() == null || dishesbeans.getData().size() == 0) {
            if ("已开台".equals(mTable.getLocal_status())) {
                settalType = "3";
                gotoDishesList();
                mEmptyTv.setVisibility(View.VISIBLE);
            }
            mDishList.clear();
            mMenuListAdapter.setNewData(mDishList);
        } else {
            if (AppUtil.isOrderDishes(getContext())) {
                mCasherLayout.setVisibility(View.GONE);
            } else {
                mCasherLayout.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(dishesbeans.getRemarks_info())) {
                mOrderRemarkTv.setVisibility(View.VISIBLE);
                mOrderRemarkTv.setText("整单备注：" + dishesbeans.getRemarks_info());
            } else {
                mOrderRemarkTv.setVisibility(View.GONE);
            }
            mDishList.clear();
            mMenuListAdapter.setNewData(mDishList);
            for (SettalItem item : dishesbeans.getData()) {
                Dishesbean dishesbean = item.toDishesbean();
                add(dishesbean);
            }
            Collections.sort(mDishList);
            mMenuListAdapter.notifyDataSetChanged();
        }
        showBottomUi();
        mLeftFormatLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddResult(String msg) {
        showToast(msg);
        mDishList.clear();
        mMenuListAdapter.setNewData(mDishList);
    }

    @Override
    public void onDeleteResult(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        mDishList.clear();
        mMenuListAdapter.setNewData(mDishList);
    }

    @Override
    public void onSettleResult(String msg) {
        mSettleTv.setText("加菜");
        mSelltalAccountTv.setText("去结账");
        mSelltalAccountTv.setVisibility(View.VISIBLE);
        mTable.setLocal_status("已下单");
        setTable(mTable, false);
        mDeleteImg.setVisibility(View.GONE);
        mClickLister.gotoTable();
        showToast(msg);
    }

    @Override
    public void onUpdateNumResult(String msg) {
        mPersonNum.setText(mPNum + " 人");
        mTable.getBillbean().setGuest_qty(mPNum + "");
    }

    @Override
    public void onPayResult(OrderPayStatusbean paybeans) {
        mPayListAdapter.setNewData(paybeans.getList());
    }


    /**
     * @param billbean
     */
    private void setBillCode(Billbean billbean) {
        this.mBillbean = billbean;
        if (billbean != null) {
            mMenuListAdapter.setBillCode(mBillbean.getBill_code());
            mBillNumberLayout.setVisibility(View.VISIBLE);
            mTitleLayout.setVisibility(View.VISIBLE);
            mBillName.setText(billbean.getRegion_name() + " " + billbean.getTable_number());
            mPersonNum.setText(billbean.getGuest_qty() + " 人");
            mPNum = TextUtils.isEmpty(billbean.getGuest_qty()) ? 0 : Integer.valueOf(billbean.getGuest_qty());
            mBillNumberTv.setVisibility(View.VISIBLE);
            mBillNumberTv.setText("订单编号：" + billbean.getBill_code());
            mEmptyTv.setVisibility(View.GONE);
            mMenuList.setVisibility(View.VISIBLE);
            mPresenter.getOrderDishes(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, mTable.getBillbean().getBill_code());
            if (AppUtil.isOrderDishes(getContext())) {
                mCasherLayout.setVisibility(View.GONE);
            } else {
                mCasherLayout.setVisibility(View.VISIBLE);
            }
        } else {
            mCasherLayout.setVisibility(View.GONE);
            mDishList.clear();
            mEmptyTv.setText("选中桌台,开始点菜");
            mCasherLayout.setVisibility(View.GONE);
            mBillNumberLayout.setVisibility(View.GONE);
            mTitleLayout.setVisibility(View.GONE);
            mBillName.setText("");
            mPersonNum.setText("");
            mBillNumberTv.setText("");
            mBillNumberTv.setVisibility(View.GONE);
            mEmptyTv.setVisibility(View.VISIBLE);
            mMenuList.setVisibility(View.GONE);
            mBottomlayout.setVisibility(View.GONE);
            mLeftFormatLayout.setVisibility(View.GONE);
        }
        mFormateView.setCurrentBill(mBillbean);
    }


    @Override
    public void onFormatClick(Dishesbean current) {
        mClickLister.onDishesDetail(current);
    }

    @Override
    public void onRemarkClick(Dishesbean current) {
        mClickLister.onClickRemarkBtn(current);
    }

    @Override
    public void onNumChanage(int i) {
        if (mCurrentPosition == -1) {
            showToast("请先选中菜品");
            return;
        }
        if (mCurrent == null) {
            mCurrent = mMenuListAdapter.getCurrentItem();
        }
        mCurrent.setLocal_num(i);
        mMenuListAdapter.notifyItemChanged(mCurrentPosition);
        mClickLister.updateDishesNum(mMenuListAdapter.getTotalNum(mCurrentPosition));
    }

    @Override
    public void onStatusClick(Dishesbean current) {
        mMenuListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean delete(Dishesbean current) {
        boolean isEmpty = false;
        if (mCurrentPosition != -1 && mCurrent != null) {
            mMenuListAdapter.remove(mCurrentPosition);
            mMenuListAdapter.setCurrentPositon(-1);
            mDishList = mMenuListAdapter.getData();
            mCurrent = null;
            mCurrentPosition = -1;
            if (mDishList.size() == 0) {
                isEmpty = true;
                clearnLeft();
            }
            mClickLister.deleteItem(mMenuListAdapter.getTotalNum(current));
        } else {
            showToast("未选中要删除的项目");
        }
        return isEmpty;
    }

    @Override
    public void onCopy() {
        if (mBillbean != null && !TextUtils.isEmpty(mBillbean.getBill_code())) {
            mClickLister.onCopyDishes( mBillbean.getBill_code());
        } else {
            showToast("没有订单号");
        }
    }

    private void clearnLeft() {
        mBottomlayout.setVisibility(View.GONE);
        mEmptyTv.setVisibility(View.VISIBLE);
        mEmptyTv.setText("开始点菜");
        mCurrent = null;
        mLeftFormatLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(final int position) {
        mCurrent = mMenuListAdapter.getItem(position);
        mCurrentPosition = position;
        mFormateView.setDishes(mCurrent, isUpdate);
    }

    @Override
    public void onNotifyItem() {
        List<Float> prices = DishesUtils.calculaPrice(mMenuListAdapter.getData());
        finalyPrice = prices.get(1);
        salePrice = prices.get(0);
        mAlreadOrderPriceTv.setText("已收：" + Param.Keys.RMB + prices.get(3));
        mOrderOldPriceTv.setText("原价：" + Param.Keys.RMB + salePrice);
        OrderPriceTv.setText("应收：" + Param.Keys.RMB + finalyPrice);
        if (finalyPrice == salePrice) {
            mOrderOldPriceTv.setVisibility(View.GONE);
        } else {
            mOrderOldPriceTv.setVisibility(View.VISIBLE);
            mAlreadOrderPriceTv.setVisibility(View.VISIBLE);
        }
        mTotalNumTv.setText("共 " + (mMenuListAdapter.getData() == null ? 0 : mMenuListAdapter.getData().size()) + " 项");
    }

    public String setOrderRemark(List<Remarkbean> orderRemark) {
        if (orderRemark != null && orderRemark.size() != 0) {
            mOrderRemarkTv.setVisibility(View.VISIBLE);
            StringBuffer buffer = new StringBuffer();
            for (Remarkbean remarkbean : orderRemark) {
                buffer.append(remarkbean.getRemarks_name());
                buffer.append("、");
            }
            mOrderRemarkTv.setText("整单备注:" + buffer.toString());
            return buffer.toString();
        } else {
            mOrderRemarkTv.setVisibility(View.GONE);
        }
        return "";
    }

    public Dishesbean setChild(SetMealGroupbean data) {
        data.getDishesbean().setMealGroupbean(data);
        return add(data.getDishesbean());
    }

    /**
     * @param data
     * @return
     */
    public Dishesbean add(final Dishesbean data) {
        isUpdate = false;
        try {
            Dishesbean dishesbean = (Dishesbean) data.clone();
            DishesUtils.fullFavorableList(dishesbean, mDiscountType, mBillbean.getBill_code());
            mMenuListAdapter.addData(0, dishesbean);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        mMenuListAdapter.setCurrentPositon(0);
        mDishList = mMenuListAdapter.getData();
        showBottomUi();
        return mMenuListAdapter.getTotalNum(data);
    }

    public void updateFormat(Dishesbean data) {
        mCurrent.setCurrentSp(data.getCurrentSp());
        DishesUtils.fullFavorableList(mCurrent, mDiscountType, mBillbean.getBill_code());
        mMenuListAdapter.notifyDataSetChanged();
    }

    public void updateRemark(Dishesbean data) {
        if (mCurrent != null) {
            mCurrent.setRemarkbeans(data.getRemarkbeans());
            mMenuListAdapter.notifyItemChanged(mCurrentPosition);
        }
    }

    public void updateChild(SetMealGroupbean data) {
        if (mCurrent != null) {
            mCurrent.setMealGroupbean(data);
            mMenuListAdapter.notifyDataSetChanged();
        }
    }

    public void setUpdateNumbers(String currentNum) {
        mPNum = Integer.valueOf(currentNum);
        if (!TextUtils.isEmpty(currentNum) && Integer.valueOf(currentNum) != 0) {
            mPresenter.updateBillbeanNum(Param.Keys.TOKEN, getToken(), Param.Keys.BILL_CODE, mBillbean.getBill_code(), Param.Keys.GUEST_QTY, currentNum);
        } else {
            showToast("包间人数不能为0");
        }
    }

    public void setTable(Table table, boolean isFjz) {
        this.isFjz = isFjz;
        this.mTable = table;
        if (mTable != null) {
            setBillCode(table.getBillbean());
            mFormateView.setLocalStatus(table.getLocal_status());
            switch (mTable.getLocal_status()) {
                case "已下单":
                    mSettleTv.setVisibility(View.VISIBLE);
                    mSettleTv.setText("加菜");
                    mDeleteImg.setVisibility(View.GONE);
                    mSelltalAccountTv.setText("去结账");
                    mSelltalAccountTv.setVisibility(AppUtil.isOrderDishes(getContext()) ? View.GONE : View.VISIBLE);
                    if (AppUtil.isOrderDishes(getContext())) {
                        mLeftFormatLayout.setVisibility(View.GONE);
                    }
                    break;
                case "已结账":
                    mSettleTv.setText("加菜");
                    mSettleTv.setVisibility(View.VISIBLE);
                    mSelltalAccountTv.setText(mTable.getLocal_status());
                    settalType = "2";
                    mDeleteImg.setVisibility(View.GONE);
                    mSelltalAccountTv.setVisibility(AppUtil.isOrderDishes(getContext()) ? View.GONE : View.VISIBLE);
                    if (AppUtil.isOrderDishes(getContext())) {
                        mLeftFormatLayout.setVisibility(View.GONE);
                    }
                    break;
                case "已开台":
                    mSelltalAccountTv.setVisibility(View.GONE);
                    mSettleTv.setVisibility(View.VISIBLE);
                    mSettleTv.setText("下单");
                    mDeleteImg.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            setBillCode(null);
            mMenuListAdapter.notifyDataSetChanged();
            mCasherLayout.setVisibility(View.GONE);
        }
    }

    public void setDemolition(List<Dishesbean> data) {
        mMenuListAdapter.setNewData(data);
    }

    public void setDiscountType(DiscountType data) {
        mDiscountType = data;
        for (Dishesbean dishesbean : mMenuListAdapter.getData()) {
            DishesUtils.fullFavorableList(dishesbean, mDiscountType, mBillbean.getBill_code());
        }
        mMenuListAdapter.notifyDataSetChanged();
        mClickLister.onPayDataChanage(getPayOrder());
    }

    public void cancelDemolition() {
        mMenuListAdapter.setNewData(mDishList);
        mSelltalAccountTv.setText("拆单支付");
        mSettleTv.setVisibility(View.GONE);
        mSettleTv.setText("加菜");
    }

    public void fanjiezhang(Table table) {
        setTable(table, true);
        gotopayFragmnet();
    }

    public void refresh() {
        setTable(mTable, isFjz);
    }

    public void sureDemolition() {
        mSettleTv.setVisibility(View.GONE);
        mSelltalAccountTv.setText("拆单支付");
        mMenuListAdapter.notifyDataSetChanged();
    }

    public void setClickLister(OrderListViewBriage.onOrderViewClick clickLister) {
        this.mClickLister = clickLister;
    }
}
