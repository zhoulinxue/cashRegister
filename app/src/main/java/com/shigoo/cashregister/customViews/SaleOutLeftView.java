package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.NumberInputActivity;
import com.shigoo.cashregister.adapters.SaleOutListAdapter;
import com.shigoo.cashregister.mvp.contacts.SaleOutContact;
import com.shigoo.cashregister.mvp.presenter.SaleOutLeftPresenter;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.SaleOutbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.app.onChildViewClick;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.wedgit.MvpCustomView;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SaleOutLeftView extends MvpCustomView<SaleOutLeftPresenter> implements SaleOutContact.view {
    @BindView(R.id.ordersheet_menu_recyclerview)
    RecyclerView mSaleOutListView;
    SaleOutListAdapter mAdapter;
    private SaleOutbean mCurrent;
    private List<SaleOutbean> saleOutbeans = new ArrayList<>();
    private onChildViewClick mLisenter;
    @BindView(R.id.ordersheet_sale_left_delete_img)
    ImageView mDeleteImg;
    private String mCurrentNumber;
    private int mPosition;
    @BindView(R.id.ordersheet_sale_left_back_img)
    ImageView mBackimg;

    public SaleOutLeftView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    @Override
    public void onSaleDishesList(List<SaleOutbean> dishesbeans) {
        mAdapter.setNewData(dishesbeans);
    }

    @Override
    public void onUpdateResult(String msg) {
        showToast(msg);
        mCurrent.setNumber(mCurrentNumber);
        mAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new EventRouter(EventBusAction.UPDATE_SALE_OUT,mCurrent));
    }

    @Override
    public void onDeleteResult(String msg) {
        showToast(msg);
        mAdapter.remove(mPosition);
    }

    @Override
    public void onClean(String msg) {
        showToast(msg);
        saleOutbeans.clear();
        mAdapter.setNewData(saleOutbeans);
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        showToast(msg);
    }

    @Override
    protected SaleOutLeftPresenter onCtreatPresenter() {
        return new SaleOutLeftPresenter(this);
    }

    @Override
    protected void onInitData() {
        mPresenter.getSaleOutList(Param.Keys.TOKEN, getToken());
    }

    @Override
    protected void onInitView(Context context, View rootView) {
        mAdapter = new SaleOutListAdapter(R.layout.ordersheet_sale_out_item_layout, saleOutbeans);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                mAdapter.setSelected(position);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                switch (view.getId()) {
                    case R.id.ordersheet_cancel_sale_out:
                        mPresenter.deleteSaleOut(Param.Keys.TOKEN, getToken(), Param.Keys.id, mAdapter.getData().get(position).getId() + "");
                        break;
                    case R.id.ordersheet_update_sale_out:
                        mCurrent = mAdapter.getData().get(position);
                        Intent intent = new Intent(getContext(), NumberInputActivity.class);
                        String unit = "份";
                        Numberbean numberbean = new Numberbean("设置沽清值", mCurrent.getNumber(), unit, 10203);
                        intent.putExtra(Param.Keys.NUMBER, numberbean);
                        getContext().startActivity(intent);
                        break;
                }
            }
        });
        mSaleOutListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSaleOutListView.setAdapter(mAdapter);
        singleClickOnMinutes(mDeleteImg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.deleteAllSaleOut(Param.Keys.TOKEN, getToken());
            }
        });
        singleClickOnMinutes(mBackimg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.getInstance().putString(Param.Keys.TOKEN, "");
                EventBus.getDefault().post(EventBusAction.MAIN);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.ordersheet_sale_left_layout;
    }

    public void addSaleOut(Dishesbean current, int id) {
        SaleOutbean saleOutbean=current.toSaleOutbean();
        saleOutbean.setId(id);
        setCurrent(saleOutbean);
    }

    public void setCurrent(SaleOutbean dishesbean) {
        mCurrent = dishesbean;
        if (!mAdapter.getData().contains(mCurrent)) {
            mAdapter.addData(0, mCurrent);
            mAdapter.setSelected(0);
        } else {
            mAdapter.setSelected(mCurrent);
        }
    }

    public void setLisenter(onChildViewClick mLisenter) {
        this.mLisenter = mLisenter;
    }

    public void updateCurent(String currentNum) {
        mCurrentNumber = currentNum;
        mPresenter.updateSaleOut(Param.Keys.id, mCurrent.getId() + "", Param.Keys.DISHE_ID, mCurrent.getDishes_id(), Param.Keys.NUMBER, currentNum, Param.Keys.TYPE, mCurrent.getType());
    }
}
