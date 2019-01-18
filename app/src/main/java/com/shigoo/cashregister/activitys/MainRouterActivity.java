package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.fragments.ConsumeDetailFragment;
import com.shigoo.cashregister.fragments.CopyDishesFragment;
import com.shigoo.cashregister.fragments.MemberDetailFragment;
import com.shigoo.cashregister.fragments.MemberMoneyDetailListFragment;
import com.shigoo.cashregister.fragments.TableMainFragment;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.mvplibrary.BaseActivity;
import com.zx.network.Param;

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
public class MainRouterActivity extends BaseActivity {
    private FragmentNavigator mFragmentNavigator;
    private List<Fragment> fragments = new ArrayList<>();
    private String mAction;

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_router_activity;
    }

    @Override
    protected void onCreateView() {
        EventBus.getDefault().register(this);
        mAction = getIntent().getAction();
        fragments.add(TableMainFragment.newInstance());
        fragments.add(MemberDetailFragment.newInstance());
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
        if (Param.ACTION.TABLE.equals(mAction)) {
            mFragmentNavigator.showFragment(0);
        } else {
            mFragmentNavigator.showFragment(1);
        }

    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRouterEvent(final EventRouter router) {
        switch (router.getAction()) {
            case CONSUM_DETAIL:
                mFragmentNavigator.showFragment(2);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment moneyFragment = mFragmentNavigator.getFragment(2);
                        ((MemberMoneyDetailListFragment) moneyFragment).setMemberId(router.getData() + "");
                    }
                }, 500);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
