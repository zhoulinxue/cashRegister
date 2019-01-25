package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.fragments.OrderPerformaceFragment;
import com.shigoo.cashregister.mvp.contacts.SalesPerformanceContact;
import com.shigoo.cashregister.mvp.presenter.SalesPerformancePresenter;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.SalePerformancebean;
import com.xgsb.datafactory.bean.WebData;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.DateUtil;
import com.zx.mvplibrary.MvpActivity;
import com.zx.mvplibrary.web.InitWebView;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.starteos.dappsdk.Request;

public class PerformanceActivity extends MvpActivity<SalesPerformancePresenter> implements SalesPerformanceContact.view {
    @BindView(R.id.ordersheet_logo_title_tv)
    TextView mTitleTv;
    @BindView(R.id.ordersheet_logo_name_tv)
    TextView mNameTv;
    @BindView(R.id.ordersheet_back_img)
    ImageView mBackImg;
    private String mAction;
    FragmentNavigator mFragmentNavigator;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected SalesPerformancePresenter onCtreatPresenter() {
        return new SalesPerformancePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_salesperformance_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        fragments.add(OrderPerformaceFragment.newInstance());
        mFragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), new FragmentNavigatorAdapter() {
            @Override
            public Fragment onCreateFragment(int i) {
                return fragments.get(i);
            }

            @Override
            public String getTag(int i) {
                return fragments.get(i).getClass().getSimpleName();
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        }, R.id.fragment_container);

    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mFragmentNavigator.onCreate(savedInstanceState);
        mFragmentNavigator.setDefaultPosition(0);
//        mAction = getIntent().getAction();
//        mAction = EventBusAction.SALE.getAction();
        mAction = EventBusAction.ORDER.getAction();
        if (EventBusAction.SALE.getAction().equals(mAction)) {

        } else if (EventBusAction.ORDER.getAction().equals(mAction)) {
            mFragmentNavigator.showFragment(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentNavigator.onSaveInstanceState(outState);
    }

    @OnClick({R.id.ordersheet_back_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ordersheet_back_img:
                quitUser();
                Intent intent = new Intent(getContext(), RouterActivity.class);
                intent.setAction(EventBusAction.MAIN.getAction());
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    public void onSalePerformanceListResult(List<SalePerformancebean> dishesbeans) {

    }

    @Override
    public void onOrderPerformanceListResult(List<OrderPerformancebean> dishesbeans) {

    }

}
