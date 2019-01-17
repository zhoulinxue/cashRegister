package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.SetMealListAdapter;
import com.shigoo.cashregister.mvp.contacts.SetMealContact;
import com.shigoo.cashregister.mvp.presenter.SetMealPresenter;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.wedgit.MvpCustomView;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class SetMealView extends MvpCustomView<SetMealPresenter> implements SetMealContact.view, SetMealListAdapter.onNumChanage {
    @BindView(R.id.ordersheet_set_meal_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.ordersheet_set_meal_name_tv)
    TextView mSetMealName;
    @BindView(R.id.ordersheet_set_meal_price_tv)
    TextView mPriceTv;
    @BindView(R.id.ordersheet_set_meal_dec_tv)
    TextView mDetailtv;
    private Dishesbean mSetMealbean;
    private SetMealListAdapter mAdapter;
    private List<SetMealGroupbean> mList = new ArrayList<>();
    @BindString(R.string.ordersheet_setmeal_dec)
    String mDecStr;
    SetMealGroupbean setMealGroupbean;
    List<Dishesbean> alDishesbeans;
    private boolean isUpdate = false;
    private List<Dishesbean> mCurrentList;
    private Map<String, Integer> selected = new HashMap<>();


    public SetMealView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    @Override
    protected SetMealPresenter onCtreatPresenter() {
        return new SetMealPresenter(this);
    }

    @Override
    protected void onInitData() {
        if (mSetMealbean != null) {
            mPresenter.getSetMealDishes(Param.Keys.TOKEN, getToken(), Param.Keys.COMBO_ID, mSetMealbean.getCombo_id());
            mSetMealName.setText(mSetMealbean.getDish_name());
            mPriceTv.setText(Param.Keys.RMB + mSetMealbean.getCombo_price());
        }
    }

    @Override
    protected void onInitView(Context context, View rootView) {
        mAdapter = new SetMealListAdapter(R.layout.ordersheet_set_meal_item_layout, mList);
        mAdapter.setLisenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public int initLayout() {
        return R.layout.ordersheet_set_meal_detail_layout;
    }

    @Override
    public void onSetMealDishesResult(List<Dishesbean> dishesbeans) {
        mList.clear();
        for (Dishesbean dishesbean : dishesbeans) {
            dishesbean.setGroup_id("-1");
        }
        setMealGroupbean = new SetMealGroupbean();
        setMealGroupbean.setDishesbean(mSetMealbean);
        setMealGroupbean.setGroup_name("套餐内菜品");
        setMealGroupbean.setId("-1");
        setMealGroupbean.setGroup_dishes(dishesbeans);
        mList.add(setMealGroupbean);
        mAdapter.setNewData(mList);
        if (!TextUtils.isEmpty(mSetMealbean.getOptional_group())) {
            mPresenter.getSetMealGroup(Param.Keys.TOKEN, getToken(), Param.Keys.GROUP_OP, mSetMealbean.getOptional_group());
        }
    }

    @Override
    public void onGroupDishesList(List<SetMealGroupbean> dishesbeans) {
        if (dishesbeans != null && dishesbeans.size() != 0) {
            mList.addAll(dishesbeans);
            mAdapter.setNewData(mList);
        }
        for (SetMealGroupbean dishesbean : dishesbeans) {
            selected.put(dishesbean.getId(), dishesbean.getMust_num());
        }
        if (isUpdate) {
            for (int i = 1; i < mList.size(); i++) {
                SetMealGroupbean setMealGroupbean = mList.get(i);

                for (Dishesbean dishesbean : setMealGroupbean.getGroup_dishes()) {
                    for (Dishesbean dis : alDishesbeans) {
                        if (!TextUtils.isEmpty(dishesbean.getSpecification_id())
                                && dishesbean.getGroup_id().equals(dis.getGroup_id())
                                && dishesbean.getSpecification_id().equals(dis.getSpecification_id())) {
                            dishesbean.setNum(dis.getNum());
                        }
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setSetMealbean(Dishesbean setMealbean, boolean isUpdate) {
        this.isUpdate = isUpdate;
        this.mSetMealbean = setMealbean;
        onInitData();
    }


    private int getCurrent() {
        int current = 0;
        for (SetMealGroupbean setMealGroupbean : mAdapter.getData()) {
            for (Dishesbean dishesbean : setMealGroupbean.getGroup_dishes()) {
                current += dishesbean.getNum();
            }
        }
        return current;
    }

    private SpannableString getSpan(CharSequence src, int start, int end) {
        SpannableString spannableString = new SpannableString(src);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getView().getResources().getColor(R.color.ordersheet_colorAccent));
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private int getTotal() {
        int total = 0;
        for (SetMealGroupbean setMealGroupbean : mList) {
            if (setMealGroupbean.getGroup_dishes() != null)
                total += setMealGroupbean.getGroup_dishes().size();
        }
        return total;
    }

    @Override
    public void onNumChanage() {
        mCurrentList = new ArrayList<>();
        for (SetMealGroupbean setMealGroupbean : mAdapter.getData()) {
            for (Dishesbean dishesbean : setMealGroupbean.getGroup_dishes()) {
                if (dishesbean.getNum() != 0) {
                    mCurrentList.add(dishesbean);
                }
            }
        }
        int current = getCurrent();
        String spanStr = String.format(mDecStr, getTotal(), current);
        mDetailtv.setText(getSpan(spanStr, spanStr.length() - (current + "").length() - 2, spanStr.length() - 2));
    }

    @OnClick({R.id.ordersheet_set_meal_cacel_btn, R.id.ordersheet_set_meal_add_btn})
    public void onViewClick(View view) {
        if (view.getId() == R.id.ordersheet_set_meal_cacel_btn) {
            getView().setVisibility(View.GONE);
        } else if (view.getId() == R.id.ordersheet_set_meal_add_btn) {
            setMealGroupbean.setGroup_dishes(mCurrentList);
            for (SetMealGroupbean setMealGroupbean : mAdapter.getData()) {
                int num = setMealGroupbean.isSelected();
                if (num != 0) {
                    //.......
                    showToast("还需选择" + num + "份" + setMealGroupbean.getGroup_name());
                    return;
                }
            }

            if (isUpdate) {
                EventBus.getDefault().post(new EventRouter(EventBusAction.CHILD_UPDATE, setMealGroupbean));
            } else {
                EventBus.getDefault().post(new EventRouter(EventBusAction.CHILD, setMealGroupbean));
            }
            getView().setVisibility(View.GONE);
        }
    }

    public void setSetMealGroupbean(SetMealGroupbean childdetail) {
        alDishesbeans = childdetail.getGroup_dishes();
        setSetMealbean(childdetail.getDishesbean(), true);
    }
}
