package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.OrderSheetMainMenuListAdapter;
import com.xgsb.datafactory.bean.OrderSheetMainMenu;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.BaseActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: RouterActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-10 16:28
 */
public class RouterActivity extends BaseActivity {
    @BindView(R.id.ordersheet_main_recyclerView)
    RecyclerView mRecyclerView;
    OrderSheetMainMenuListAdapter mAdapter;
    private int mPosition = 0;
    private List<OrderSheetMainMenu> mList = new ArrayList<>();
    private int[] LOGO = {
            R.mipmap.ordersheet_home_icon_piont,
            R.mipmap.ordersheet_home_icon_give,
            R.mipmap.ordersheet_home_icon_estimate,
            R.mipmap.ordersheet_home_icon_wine,
            R.mipmap.ordersheet_home_icon_vip,
            R.mipmap.ordersheet_home_icon_data};


    @Override
    protected int initLayout() {
        return R.layout.ordersheet_main_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        onRouterEvent(EventBusAction.getEnum(getIntent().getAction()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        String[] arrays = getResources().getStringArray(R.array.ordersheet_menber_tab_title);
        List<String> nameArrarys = Arrays.asList(arrays);
        for (int i = 0; i < nameArrarys.size(); i++) {
            mList.add(new OrderSheetMainMenu(LOGO[i], nameArrarys.get(i)));
        }
        mAdapter = new OrderSheetMainMenuListAdapter(R.layout.ordersheet_main_menu_item_layout, mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                if (needLogin()) {
                    //未登陆时 跳转登陆界面
                    onRouterEvent(EventBusAction.LOGIN);
                    return;
                }
                onRouterEvent(EventBusAction.LOGIN_SUC);
            }
        });
    }

    private boolean needLogin() {
        return DEFAULT_TOKEN.equals(getToken()) || TextUtils.isEmpty(getToken());
    }

    private void onPage(int position) {
        switch (position) {
            case 0:
                Intent mainRuterActivity = new Intent(this, MainRouterActivity.class);
                mainRuterActivity.setAction(Param.ACTION.TABLE);
                startActivity(mainRuterActivity);
                break;
            case 1:
                startActivity(new Intent(this, GiveAsGiftActivity.class));
                break;
            case 2:
                startActivity(new Intent(RouterActivity.this, SaleOutActivity.class));
                break;
            case 3:
                break;
            case 4:
                Intent ruterActivity = new Intent(this, MainRouterActivity.class);
                ruterActivity.setAction(Param.ACTION.MEMBER);
                startActivity(ruterActivity);
                break;
            case 5:
                Intent intent = new Intent(RouterActivity.this, PerformanceActivity.class);
                intent.setAction(EventBusAction.SALE.getAction());
                startActivity(intent);
                break;
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRouterEvent(EventBusAction action) {
        switch (action) {
            case LOGIN:
                startActivity(new Intent(this, OrderSheetLoginActivity.class));
                break;
            case LOGIN_SUC:
                if (AppUtil.isOrderDishes(this)) {
                    onPage(mPosition);
                } else {
                    startActivity(new Intent(this, CashRigisterMainActivity.class));
                }
                break;
            case MAIN:
                if (!AppUtil.isOrderDishes(this)) {
                    if (!needLogin()) {
                        startActivity(new Intent(this, CashRigisterMainActivity.class));
                        finish();
                    } else {
                        onRouterEvent(EventBusAction.LOGIN);
                        finish();
                    }
                }
                break;
        }
    }
}
