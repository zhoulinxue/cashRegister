package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.fragments.SaleOutMainFragment;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: MainRouterActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-11 10:37
 */
public class SaleOutActivity extends BaseActivity {
    private FragmentNavigator mFragmentNavigator;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_router_activity;
    }

    @Override
    protected void onCreateView() {
        fragments.add(SaleOutMainFragment.newInstance());
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
        }, R.id.ordersheet_fragment_container);
        mFragmentNavigator.setDefaultPosition(0);
        mFragmentNavigator.showFragment(0);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRouterEvent(EventBusAction action) {
        switch (action) {
            case BACK_TO_MAIN:
                finish();
                break;
        }
    }
}
