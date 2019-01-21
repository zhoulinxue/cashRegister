package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.ChedanAdapter;
import com.shigoo.cashregister.adapters.DazheAdapter;
import com.shigoo.cashregister.adapters.GaijiaAdapter;
import com.shigoo.cashregister.adapters.TuicaiAdapter;
import com.shigoo.cashregister.mvp.contacts.TableDialogContact;
import com.shigoo.cashregister.mvp.presenter.TableDialogPresenter;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.wedgit.MvpCustomView;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
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
    @BindView(R.id.ordersheet_dishes_gaijia_tv)
    EditText mGaijiaEdite;
    @BindView(R.id.ordersheet_dishes_zhekou_tv)
    EditText mZheKouEdite;
    @BindView(R.id.discount_price)
    TextView mDiscountPriceTv;
    @BindView(R.id.tuicai_num_tv)
    TextView mTuicaiNumTv;
    private List<Remarkbean> chedanList = new ArrayList<>();
    private List<Remarkbean> dazheList = new ArrayList<>();
    private List<Remarkbean> tuicaiList = new ArrayList<>();
    private List<Remarkbean> gaijiaList = new ArrayList<>();
    private ChedanAdapter mChedanAdapter;
    private DazheAdapter mDazheAdapter;
    private TuicaiAdapter mTuicaiAdapter;
    private GaijiaAdapter mGaijiaAdapter;
    private String mBillCode;
    //备注类型1表示单品备注，2表示整单备注，3退菜备注，4改价备注，5打折备注，6撤单备注，8反结账原因
    private int mReasonType;
    private Dishesbean mCurrentDishes;
    @BindView(R.id.gaijia_dishes_name_tv)
    TextView mGaijiaNameTv;
    @BindView(R.id.dazhe_dishes_name_tv)
    TextView mDazheNameTv;
    @BindView(R.id.tuicai_dishes_name_tv)
    TextView mTuicaiNameTv;
    @BindView(R.id.orginal_price)
    TextView mOriginalTv;


    public TableDialogView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }


    @Override
    protected TableDialogPresenter onCtreatPresenter() {
        return new TableDialogPresenter(this);
    }

    @Override
    protected void onInitData() {
    }

    @Override
    protected void onInitView(Context context, View rootView) {
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        mChedanRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mGaijiaRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mDazheRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mTuicaiRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        mChedanAdapter = new ChedanAdapter(R.layout.ordersheet_remark_item_layout, chedanList);
        mChedanRecyclerView.setAdapter(mChedanAdapter);
        mChedanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mChedanAdapter.setSelected(position);
            }
        });
        mGaijiaAdapter = new GaijiaAdapter(R.layout.ordersheet_remark_item_layout, gaijiaList);
        mGaijiaRecyclerView.setAdapter(mGaijiaAdapter);
        mGaijiaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mGaijiaAdapter.setSelected(position);
            }
        });
        mDazheAdapter = new DazheAdapter(R.layout.ordersheet_remark_item_layout, dazheList);
        mDazheRecyclerView.setAdapter(mDazheAdapter);
        mDazheAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDazheAdapter.setSelected(position);
            }
        });
        mTuicaiAdapter = new TuicaiAdapter(R.layout.ordersheet_remark_item_layout, tuicaiList);
        mTuicaiRecyclerView.setAdapter(mTuicaiAdapter);
        mTuicaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTuicaiAdapter.setSelected(position);
            }
        });
        mZheKouEdite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)) {
                    float discount = Float.valueOf(charSequence.toString()) / 100;
                    if (discount > 1) {
                        showToast("折扣不能大于100");
                        mZheKouEdite.setText(100 + "");
                    }
                    mDiscountPriceTv.setText(Param.Keys.RMB + (AppUtil.getFloatFromString(mCurrentDishes.getShowPrice()).floatValue() * discount));
                } else {
                    mDiscountPriceTv.setText(Param.Keys.RMB + (AppUtil.getFloatFromString(mCurrentDishes.getShowPrice()).floatValue()));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.table_dialog_view_layout;
    }

    @Override
    public void onCancelOrderResult(String listData) {
        showToast(listData);
        getView().setVisibility(View.GONE);
        EventBus.getDefault().post(new EventRouter(EventBusAction.TABLE_BACK));
    }

    @Override
    public void onReasonResult(List<Remarkbean> reasonList) {
        switch (mReasonType) {
            case 3:
                mTuicaiAdapter.setNewData(reasonList);
                break;
            case 4:
                mGaijiaAdapter.setNewData(reasonList);
                break;
            case 5:
                mDazheAdapter.setNewData(reasonList);
                break;
            case 6:
                mChedanAdapter.setNewData(reasonList);
                break;
        }
    }

    @Override
    public void onChargePriceResult(String msg) {
        showToast(msg);
        getView().setVisibility(View.GONE);
        EventBus.getDefault().post(new EventRouter(EventBusAction.BILL_REFRESH));
    }

    @Override
    public void onDiscountResult(String msg) {
        showToast(msg);
        getView().setVisibility(View.GONE);
        EventBus.getDefault().post(new EventRouter(EventBusAction.BILL_REFRESH));
    }

    @Override
    public void onReturnDishesResult(String msg) {
        showToast(msg);
        getView().setVisibility(View.GONE);
        EventBus.getDefault().post(new EventRouter(EventBusAction.BILL_REFRESH));
    }

    @OnClick({R.id.gaijia_ordersheet_close_img,
            R.id.tuicai_ordersheet_close_img,
            R.id.dazhe_ordersheet_close_img,
            R.id.chedan_ordersheet_close_img,
            R.id.chedan_ordersheet_number_result_btn,
            R.id.zhekou_sure_btn,
            R.id.tuicai_sure_btn,
            R.id.gaijia_sure_btn,
            R.id.ordersheet_dishes_tuicai_jj_tv,
            R.id.ordersheet_dishes_tuicai_add_tv})
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
            case R.id.gaijia_sure_btn:
                if (!TextUtils.isEmpty(mGaijiaEdite.getText().toString())) {
                    mPresenter.chargePrice(Param.Keys.TOKEN, getToken()
                            , Param.Keys.id, mCurrentDishes.getId() + ""
                            , Param.Keys.REPRICER_ID, getUser().getCashier_id(),
                            Param.Keys.REPRICE_REASON, mGaijiaAdapter.getReason(),
                            Param.Keys.FINALLY_PRICE, mGaijiaEdite.getText().toString(),Param.Keys.FINALLY_TAG,"3");
                } else {
                    showToast("请输入价格");
                }
                break;
            case R.id.zhekou_sure_btn:
                if (!TextUtils.isEmpty(mZheKouEdite.getText().toString())) {
                    mPresenter.discountDishes(Param.Keys.TOKEN, getToken(),
                            Param.Keys.id, mCurrentDishes.getId() + "",
                            Param.Keys.REDISCOUNT_ID, getUser().getCashier_id(),
                            Param.Keys.RE_DISCOUNT, mZheKouEdite.getText().toString(),
                            Param.Keys.RE_DISCOUNT_REASON, mDazheAdapter.getReason(),Param.Keys.FINALLY_TAG,"4",
                            Param.Keys.FINALLY_PRICE, mDiscountPriceTv.getText().toString().replace(Param.Keys.RMB, ""));
                } else {
                    showToast("请输入折扣");
                }
                break;
            case R.id.tuicai_sure_btn:
                mPresenter.returnDishes(Param.Keys.TOKEN, getToken(),
                        Param.Keys.id, mCurrentDishes.getId() + "",
                        Param.Keys.BACK_ID, getUser().getCashier_id(),
                        Param.Keys.NUMBER, mTuicaiNumTv.getText().toString(), Param.Keys.BACK_REASON, mTuicaiAdapter.getReason());
                break;
            case R.id.ordersheet_dishes_tuicai_jj_tv:
                int num = Integer.valueOf(mTuicaiNumTv.getText().toString());
                if (num > 1) {
                    mTuicaiNumTv.setText((num - 1) + "");
                } else {
                    showToast("退菜数量不能小于1");
                }
                break;
            case R.id.ordersheet_dishes_tuicai_add_tv:
                int addnum = Integer.valueOf(mTuicaiNumTv.getText().toString());
                if (addnum < mCurrentDishes.getLocal_num()) {
                    mTuicaiNumTv.setText((addnum + 1) + "");
                } else {
                    showToast("已经是最大数量了");
                }
                break;
        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(final EventRouter eventRouter) {
        //备注类型1表示单品备注，2表示整单备注，3退菜备注，4改价备注，5打折备注，6撤单备注，8反结账原因
        switch (eventRouter.getAction()) {
            case TUI_CAI:
                mReasonType = 3;
                mCurrentDishes = (Dishesbean) eventRouter.getData();
                mTuiCaiLayout.setVisibility(View.VISIBLE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.GONE);
                mTuicaiNumTv.setText(mCurrentDishes.getLocal_num() + "");
                getView().setVisibility(View.VISIBLE);
                mTuicaiNameTv.setText("退菜-" + mCurrentDishes.getNotNullName());
                mPresenter.getMultReasonList(Param.Keys.TOKEN, getToken(), Param.Keys.TYPE, mReasonType + "");
                break;
            case DA_ZHE:
                mReasonType = 5;
                mCurrentDishes = (Dishesbean) eventRouter.getData();
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.VISIBLE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.GONE);
                getView().setVisibility(View.VISIBLE);
                mDazheNameTv.setText("打折-" + mCurrentDishes.getNotNullName());
                mDiscountPriceTv.setText(Param.Keys.RMB + mCurrentDishes.getShowPrice());
                mPresenter.getMultReasonList(Param.Keys.TOKEN, getToken(), Param.Keys.TYPE, mReasonType + "");
                break;
            case CHE_DAN:
                mReasonType = 6;
                mBillCode = ((Billbean) eventRouter.getData()).getBill_code();
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.GONE);
                mCancelOrderLayout.setVisibility(View.VISIBLE);
                getView().setVisibility(View.VISIBLE);
                mPresenter.getMultReasonList(Param.Keys.TOKEN, getToken(), Param.Keys.TYPE, mReasonType + "");
                break;
            case GAI_JIA:
                mCurrentDishes = (Dishesbean) eventRouter.getData();
                mGaijiaEdite.setText(mCurrentDishes.getShowPrice());
                mReasonType = 4;
                mTuiCaiLayout.setVisibility(View.GONE);
                mDiscountLayout.setVisibility(View.GONE);
                mGaiJiaLayout.setVisibility(View.VISIBLE);
                mCancelOrderLayout.setVisibility(View.GONE);
                getView().setVisibility(View.VISIBLE);
                mGaijiaNameTv.setText("改价-" + mCurrentDishes.getNotNullName());
                mPresenter.getMultReasonList(Param.Keys.TOKEN, getToken(), Param.Keys.TYPE, mReasonType + "");
                if (mCurrentDishes.isSelMeal()) {
                    mOriginalTv.setText(Param.Keys.RMB + mCurrentDishes.getCombo_price());
                } else {
                    mOriginalTv.setText(Param.Keys.RMB + mCurrentDishes.getCurrentSp().getSale_price());
                }
                break;
            case CHILD_DETAIL:
            case DISHES_DETAIL:
                getView().setVisibility(View.GONE);
                break;
        }
    }

    public void onDestory() {
        EventBus.getDefault().unregister(this);
    }
}
