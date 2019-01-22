package com.shigoo.cashregister.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.NumberInputActivity;
import com.shigoo.cashregister.activitys.PayMemberLoginActivity;
import com.shigoo.cashregister.adapters.DemolitionDishesListAdapter;
import com.shigoo.cashregister.adapters.FavorableTypeListAdapter;
import com.shigoo.cashregister.adapters.MenuDishesListAdapter;
import com.shigoo.cashregister.adapters.PayFavorableAdapter;
import com.shigoo.cashregister.adapters.PayMoneyTypeListAdapter;
import com.shigoo.cashregister.adapters.PaymentTypeListAdapter;
import com.shigoo.cashregister.mvp.contacts.SettalContact;
import com.shigoo.cashregister.mvp.presenter.SettalPresenter;
import com.shigoo.cashregister.utils.DishesUtils;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.AddFavorablebean;
import com.xgsb.datafactory.bean.AddPayment;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.Paymentbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.enu.DiscountType;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.MvpFragment;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettalFragment extends MvpFragment<SettalPresenter> implements SettalContact.view, MenuDishesListAdapter.onItemSelected {
    @BindView(R.id.favourable_type_recyclerView)
    RecyclerView mFravourableTypeList;
    @BindView(R.id.pay_type_recyclerView)
    RecyclerView mPayTypeListView;
    @BindView(R.id.favourable_list_recyclerView)
    RecyclerView mFavorableListView;
    @BindView(R.id.pay_money_listview)
    RecyclerView mPayMoneyListView;
    @BindView(R.id.demolition_layout)
    FrameLayout mDemolitionLayout;
    @BindView(R.id.demolition_all_dishes_layout)
    RecyclerView mDemolitionListView;
    @BindView(R.id.sure_demolition_tv)
    TextView mSureTv;
    @BindView(R.id.pay_total_tv)
    TextView mPatToataltv;
    @BindView(R.id.pay_need_tv)
    TextView mFinallyPriceTv;
    @BindView(R.id.pay_rest_tv)
    TextView mRestPriceTv;
    @BindView(R.id.pay_bottom_sure_tv)
    TextView mPaymentTv;
    @BindView(R.id.pay_rest_notice)
    TextView mPayNoticetv;
    @BindView(R.id.add_all_dishes)
    TextView mAllDishesTv;
    private List<Float> floats;
    @BindView(R.id.goods_num_tv)
    TextView mGoodsNumTv;


    private Favorablebean mFavorablebean;
    private FavorableTypeListAdapter mFavorableTypeAdapter;
    private PaymentTypeListAdapter mPaymentTypeAdapter;
    private DemolitionDishesListAdapter mDemolitionAdapter;
    private PayFavorableAdapter mPayFavorableAdapter;
    private List<Favorablebean> mFavorableList = new ArrayList<>();
    private List<PayTypebean> mPayTypeList = new ArrayList<>();
    private List<Dishesbean> dishesbeans = new ArrayList<>();
    private List<Dishesbean> selectedList = new ArrayList<>();
    private SettalOrderbean mOrderbean;
    private List<AddFavorablebean> mPayFavorableList = new ArrayList<>();
    private Paymentbean mAddPayment;
    private PayMoneyTypeListAdapter mPayMoneyAdapter;
    private List<AddPayment> mPayMoneyData = new ArrayList<>();
    AddPayment mCurrentPayment;


    public static SettalFragment newInstance() {
        SettalFragment fragment = new SettalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onFavourableListResult(List<Favorablebean> cardbean) {
        Favorablebean favorablebean = new Favorablebean();
        favorablebean.setFavorable_name("会员优惠");
        cardbean.add(0, favorablebean);
        mFavorableTypeAdapter.setNewData(cardbean);
    }

    @Override
    public void onPayTypeResult(List<PayTypebean> list) {
        mPaymentTypeAdapter.setNewData(list);
    }

    @Override
    public void onPayResult(String msg) {
        EventBus.getDefault().post(new EventRouter(EventBusAction.PAY_SUC));
        mPayMoneyData.clear();
        mPayMoneyAdapter.setNewData(mPayMoneyData);
        showToast(msg);
    }

    @Override
    protected SettalPresenter onCtreatPresenter() {
        return new SettalPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.payment_fragment_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        //_________________________________________________________
        mFavorableTypeAdapter = new FavorableTypeListAdapter(R.layout.favourable_item_layout, mFavorableList);
        mFravourableTypeList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mFravourableTypeList.setAdapter(mFavorableTypeAdapter);
        mFavorableTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mFavorablebean = mFavorableTypeAdapter.getItem(position);
                boolean isDiscount = mFavorableTypeAdapter.onClickPosition(position);
                if (isDiscount) {
                    if (position == 0) {
                        Intent intent = new Intent(getContext(), PayMemberLoginActivity.class);
                        intent.putExtra(Param.Keys.BILL_CODE, mOrderbean);
                        startActivityForResult(intent, Param.Keys.NUMBER_RESULT);
                        return;
                    } else {
                        removemember();
                    }
                    switch (mFavorablebean.getCategory()) {
                        case "1":
                            //全单折扣
                            EventBus.getDefault().post(new EventRouter(EventBusAction.DISCOUNT, DiscountType.ALL_TAG));
                            break;
                        case "5":
                            //整单折扣
                            EventBus.getDefault().post(new EventRouter(EventBusAction.DISCOUNT, DiscountType.WHOLE_TAG));
                            break;
                    }
                } else {
                    if (position == 0) {
                        removemember();
                    }
                    mFavorablebean = null;
                    EventBus.getDefault().post(new EventRouter(EventBusAction.DISCOUNT, DiscountType.NULL));
                }
            }
        });
        //________________________________________________________________________
        mPaymentTypeAdapter = new PaymentTypeListAdapter(R.layout.pay_type_item_layout, mPayTypeList);
        mPayTypeListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mPayTypeListView.setAdapter(mPaymentTypeAdapter);
        mPaymentTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                float restPrice = mPayMoneyAdapter.getRestPrice();
                if (restPrice <= mOrderbean.getRestPrice()) {
                    PayTypebean payTypebean = mPaymentTypeAdapter.getData().get(position);
                    AddPayment addPayment = new AddPayment();
                    addPayment.setPay_amount(mOrderbean.getRestPrice() - restPrice);
                    addPayment.setPay_name(payTypebean.getPay_name());
                    addPayment.setPay_way(payTypebean.getId() + "");
                    if (!mPayMoneyData.contains(addPayment) && addPayment.getPay_amount() != 0) {
                        mPayMoneyData.add(addPayment);
                        mPayMoneyAdapter.setNewData(mPayMoneyData);
                        mPayMoneyAdapter.setStorageValue(mPayMoneyData.size() - 1, "1".equals(payTypebean.getActual_tag()));
                    }
                }
            }
        });
        //__________________________________________________________________________
        mDemolitionListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDemolitionAdapter = new DemolitionDishesListAdapter(R.layout.demolition_dishes_item_layout, dishesbeans);
        mDemolitionListView.setAdapter(mDemolitionAdapter);
        mDemolitionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDemolitionAdapter.setSelected(position);
            }
        });
        mDemolitionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.demolition_add_tv:
                        break;
                    case R.id.demolition_decrice_tv:
                        break;
                    case R.id.demolition_checkbox:
                        if (view instanceof CheckBox) {
                            boolean isChecked = ((CheckBox) view).isChecked();
                            Dishesbean dishesbean = mDemolitionAdapter.getData().get(position);
                            if (isChecked) {
                                selectedList.add(dishesbean);
                            } else {
                                selectedList.remove(dishesbean);
                            }
                            mGoodsNumTv.setText("商品数量："+selectedList.size());
                            EventBus.getDefault().post(new EventRouter(EventBusAction.DEMOLITION_ADD_DISHES, selectedList));
                        }
                        break;
                }
            }
        });
        //__________________________________________
        mFavorableListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPayFavorableAdapter = new PayFavorableAdapter(R.layout.pay_favorable_item_layout, mPayFavorableList);
        mFavorableListView.setAdapter(mPayFavorableAdapter);
        //-------------------------------------------------------------------
        mPayMoneyListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPayMoneyAdapter = new PayMoneyTypeListAdapter(R.layout.pay_money_item_layout, mPayMoneyData);
        mPayMoneyListView.setAdapter(mPayMoneyAdapter);
        mPayMoneyAdapter.setLisenter(this);
        mPayMoneyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCurrentPayment = mPayMoneyAdapter.getData().get(position);
                Intent intent = new Intent(getContext(), NumberInputActivity.class);
                Numberbean numberbean = new Numberbean("修改金额", mCurrentPayment.getPay_amount() + "", "", 10203);
                intent.putExtra(Param.Keys.NUMBER, numberbean);
                getContext().startActivity(intent);
            }
        });

        mPayMoneyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.payment_return_tv:
                        mPayMoneyAdapter.remove(position);
                        mPayMoneyAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        singleClickOnMinutes(mSureTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedList != null && selectedList.size() > 0) {
                    mDemolitionLayout.setVisibility(View.GONE);
                    newPayPrice(selectedList);
                    EventBus.getDefault().post(new EventRouter(EventBusAction.SURE_DEMOLITION));
                } else {
                    showToast("请选择菜品");
                }
            }
        });
        singleClickOnMinutes(mPaymentTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddPayment.setNo_storage_money(mPayMoneyAdapter.getNoStorageMoney());
                mAddPayment.setSale(mPayFavorableAdapter.getData());
                mAddPayment.setPayments(mPayMoneyAdapter.getData());
                mAddPayment.setToken(getToken());
                if ((mOrderbean.getRestPrice() - mPayMoneyAdapter.getRestPrice()) == 0) {
                    mPresenter.payOrder(mAddPayment);
                } else {
                    showToast("还未支付完账单，不能结账");
                }
            }
        });
        singleClickOnMinutes(mAllDishesTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventRouter(EventBusAction.CANCEL_DEMOLITION));
            }
        });
        mGoodsNumTv.setText("商品数量："+selectedList.size());
    }

    private void removemember() {
        SPUtil.getInstance().put(mOrderbean.getBill_code() + "use", false);
        mPayFavorableAdapter.removeData("会员优惠");
    }


    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getFavourableList(Param.Keys.TOKEN, getToken());
        mPresenter.getPayTypeList(Param.Keys.TOKEN, getToken());
    }

    public void onDemolition(int isDemolition) {
        mDemolitionAdapter.setNewData(dishesbeans);
        newPayPrice(dishesbeans);
        mDemolitionLayout.setVisibility(isDemolition == 2 ? View.GONE : View.VISIBLE);
        selectedList.clear();
    }

    public void onSettale(SettalOrderbean data) {
        if (mOrderbean != null && !mOrderbean.getBill_code().equals(data.getBill_code())) {
            mPayMoneyData.clear();
            mPayMoneyAdapter.setNewData(mPayMoneyData);
        }
        mOrderbean = data;
        dishesbeans = data.getDishes();
        newPayPrice(dishesbeans);
    }

    private void newPayPrice(List<Dishesbean> dishesbeans) {
        floats = DishesUtils.calculaPrice(dishesbeans);
        mOrderbean.setSalePrice(floats.get(0));
        mOrderbean.setFinalyPrice(floats.get(1));
        mOrderbean.setRestPrice(floats.get(2));
        mDemolitionAdapter.setNewData(dishesbeans);
        mFavorableTypeAdapter.setOrderbean(mOrderbean);
        mPaymentTypeAdapter.setOrderbean(mOrderbean);
        setPayMsg(dishesbeans);
    }

    private void setPayMsg(List<Dishesbean> dishesbeans) {
        mAddPayment = new Paymentbean();
        mPayFavorableList.clear();
        float timeMoney = 0f;
        float spcialMoney = 0f;
        float memberMoney = 0f;
        List<Integer> idsList = new ArrayList<>();
        for (Dishesbean dishesbean : dishesbeans) {
            switch (dishesbean.getMinFavorable().getName()) {
                case "优惠-时段价":
                    timeMoney += AppUtil.getFloatFromString(dishesbean.getMaxFavorable().getMoney()).floatValue() - AppUtil.getFloatFromString(dishesbean.getMinFavorable().getMoney()).floatValue();
                    break;
                case "会员价优惠":
                    memberMoney += AppUtil.getFloatFromString(dishesbean.getMinFavorable().getMoney()).floatValue();
                    break;
                case "优惠-特价菜":
                    spcialMoney += AppUtil.getFloatFromString(dishesbean.getMinFavorable().getMoney()).floatValue();
                    break;
            }
            if (!dishesbean.isPayed()) {
                idsList.add(dishesbean.getId());
            }
        }
        if (mFavorablebean != null) {
            float currentMoney = 0f;
            for (Dishesbean dishesbean : dishesbeans) {
                if (mFavorablebean.getFavorable_name().contains(dishesbean.getMinFavorable().getName())) {
                    currentMoney += AppUtil.getFloatFromString(dishesbean.getMaxFavorable().getMoney()).floatValue() - AppUtil.getFloatFromString(dishesbean.getMinFavorable().getMoney()).floatValue();
                }
            }
            if (currentMoney != 0) {
                AddFavorablebean addFavorablebean = new AddFavorablebean();
                addFavorablebean.setName(mFavorablebean.getFavorable_name());
                addFavorablebean.setMoney(currentMoney + "");
                addFavorablebean.setDiscount("0");
                if (!"会员优惠".equals(mFavorablebean.getFavorable_name())) {
                    addFavorablebean.setParent_name("订单优惠");
                } else {
                    addFavorablebean.setParent_name("菜品优惠");
                }
                mPayFavorableList.add(addFavorablebean);
            }
        }
        if (timeMoney != 0) {
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("优惠-时段价");
            addFavorablebean.setMoney(timeMoney + "");
            addFavorablebean.setDiscount("0");
            addFavorablebean.setParent_name("菜品优惠");
            mPayFavorableList.add(addFavorablebean);
        }

        if (spcialMoney != 0) {
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("优惠-特价菜");
            addFavorablebean.setMoney(timeMoney + "");
            addFavorablebean.setDiscount("0");
            addFavorablebean.setParent_name("菜品优惠");
            mPayFavorableList.add(addFavorablebean);
        }

        if (memberMoney != 0) {
            AddFavorablebean addFavorablebean = new AddFavorablebean();
            addFavorablebean.setName("会员价");
            addFavorablebean.setMoney(memberMoney + "");
            addFavorablebean.setDiscount("0");
            addFavorablebean.setParent_name("会员优惠");
            mPayFavorableList.add(addFavorablebean);
        }
        mPayFavorableAdapter.setNewData(mPayFavorableList);
        mAddPayment.setBill_code(mOrderbean.getBill_code());
        mAddPayment.setOriginal_price(mOrderbean.getSalePrice() + "");
        mAddPayment.setReceivable(mOrderbean.getFinalyPrice());
        mAddPayment.setDishes(idsList);
        mAddPayment.setSelling_id(mOrderbean.getSale_id());
        mAddPayment.setCashier_id("123");
        mAddPayment.setPerson_in_charge_id("456");
//        if (getUser() != null) {
//            if (TextUtils.isEmpty(getUser().getCashier_id())) {
//                mAddPayment.setCashier_id(getUser().getCashier_id());
//            }
//            if (!TextUtils.isEmpty(getUser().getPerson_in_charge_id())) {
//                mAddPayment.setPerson_in_charge_id(getUser().getPerson_in_charge_id());
//            }
//        }
        mPatToataltv.setText(Param.Keys.RMB + mOrderbean.getSalePrice());
        mFinallyPriceTv.setText(Param.Keys.RMB + mOrderbean.getFinalyPrice());
        mRestPriceTv.setText(Param.Keys.RMB + (mOrderbean.getRestPrice() - mPayMoneyAdapter.getRestPrice()));
        //优惠数据
        addMemberFavorable(false);
        mPayMoneyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Param.Keys.NUMBER_RESULT) {
            mFavorableTypeAdapter.notifyDataSetChanged();
            mPaymentTypeAdapter.notifyDataSetChanged();
            addMemberFavorable(true);
            if (!SPUtil.getInstance().getBoolean(mOrderbean.getBill_code() + "use")) {
                mFavorableTypeAdapter.setSelected(-1);
            }
        }
    }

    private void addMemberFavorable(boolean isNotify) {
        if (SPUtil.getInstance().getBoolean(mOrderbean.getBill_code() + "use")) {
            String memberMsg = SPUtil.getInstance().getString(mOrderbean.getBill_code());
            Member member = (Member) JSONManager.getInstance().parseObject(memberMsg, Member.class);
            mAddPayment.setMember_id(member.getId() + "");
            mFavorableTypeAdapter.setSelected(0);
            if (isNotify) {
                EventBus.getDefault().post(new EventRouter(EventBusAction.DISCOUNT, DiscountType.MEMBER));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(final EventRouter eventRouter) {
        switch (eventRouter.getAction()) {
            case SET_NUMBER:
                Numberbean numberbean = (Numberbean) eventRouter.getData();
                if (numberbean.getRequstCode() == 10203) {
                    mCurrentPayment.setPay_amount(AppUtil.getFloatFromString(numberbean.getCurrentNum()).floatValue());
                    mPayMoneyAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onItemSelected(int position) {

    }

    @Override
    public void onNotifyItem() {
        float restPrice = mPayMoneyAdapter.getRestPrice();
        if (restPrice <= mOrderbean.getRestPrice()) {
            mPayNoticetv.setText("还差");
            mRestPriceTv.setText(Param.Keys.RMB + (mOrderbean.getRestPrice() - mPayMoneyAdapter.getRestPrice()));
        } else {
            mPayNoticetv.setText("找零");
            mRestPriceTv.setText(Param.Keys.RMB + (mPayMoneyAdapter.getRestPrice() - mOrderbean.getRestPrice()));
        }
        if (mPayFavorableAdapter.getData() == null || mPayFavorableAdapter.getData().size() == 0) {
            mFavorableListView.setVisibility(View.GONE);
        } else {
            mFavorableListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mDemolitionLayout.setVisibility(View.GONE);
        }
    }
}
