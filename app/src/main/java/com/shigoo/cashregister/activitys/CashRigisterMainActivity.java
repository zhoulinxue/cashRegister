package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.fragments.AddCardFragment;
import com.shigoo.cashregister.fragments.BooksOffFragment;
import com.shigoo.cashregister.fragments.MemberDetailFragment;
import com.shigoo.cashregister.fragments.MemberMainFragment;
import com.shigoo.cashregister.fragments.OrdersFragment;
import com.shigoo.cashregister.fragments.PrintMainFragment;
import com.shigoo.cashregister.fragments.TableMainFragment;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.Table;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.BaseActivity;
import com.zx.mvplibrary.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.ITabView.TabIcon;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Name: CashRigisterMainActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-23 14:05
 */
public class CashRigisterMainActivity extends BaseActivity {
    @BindView(R.id.left_tab_layout)
    VerticalTabLayout mLeftTabLayout;
    @BindView(R.id.left_bar_layout)
    RelativeLayout mLeftLayout;
    private List<TabLayout.Tab> mTabs = new ArrayList<>();
    private TabLayout.Tab mTables, mTabOrders, mTabMenber, mTabBookOff, mTabPrint;
    private final int[] mTabImgs = {R.mipmap.books, R.mipmap.orders, R.mipmap.menbers, R.mipmap.jiaoban, R.mipmap.print};
    private final int[] mTabText = {R.string.table, R.string.orders, R.string.menbers, R.string.book_off, R.string.print};

    private List<Fragment> fragments = new ArrayList<>();
    public static String TEST = "test";
    FragmentNavigator mFragmentNavigator;

    @Override
    protected int initLayout() {
        return R.layout.cash_main_main_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        initTableLayout();
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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mFragmentNavigator.onCreate(savedInstanceState);
        mFragmentNavigator.setDefaultPosition(4);
        mLeftTabLayout.setTabSelected(mFragmentNavigator.getCurrentPosition());
        mLeftTabLayout.getTabAt(mFragmentNavigator.getCurrentPosition()).getTitleView().setBackgroundResource(R.drawable.cash_main_left_tab_press_bg);
        mFragmentNavigator.showFragment(mFragmentNavigator.getCurrentPosition());
    }

    /**
     * 初始化代码
     */
    private void initTableLayout() {
        addTabs();
        mLeftTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                for (int i = 0; i < mTabImgs.length; i++) {
                    if (i == position) {
                        tab.getTitleView().setBackgroundResource(R.drawable.cash_main_left_tab_press_bg);
                        mFragmentNavigator.showFragment(position);
                        if (position == 0) {
                            mLeftLayout.setVisibility(View.GONE);
                        } else {
                            mLeftLayout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mLeftTabLayout.getTabAt(i).getTitleView().setBackground(null);
                    }
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentNavigator.onSaveInstanceState(outState);
    }

    @OnClick({R.id.exit_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exit_tv:
                onBackPressed();
                break;
        }
    }

    /**
     * 添加 tab
     */
    private void addTabs() {
        for (int i = 0; i < mTabImgs.length; i++) {
            TabIcon.Builder iconBuilder = new TabIcon.Builder();
            iconBuilder.setIcon(mTabImgs[i], mTabImgs[i]);
            iconBuilder.setIconGravity(Gravity.TOP);
            QTabView tabView = new QTabView(this);
            tabView.setIcon(iconBuilder.build());
            ITabView.TabTitle.Builder titleBuilder = new ITabView.TabTitle.Builder();
            titleBuilder.setTextColor(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
            titleBuilder.setTextSize((int) getResources().getDimension(R.dimen.member_tabl_text_size));
            titleBuilder.setContent(getResources().getString(mTabText[i]));
            tabView.setTitle(titleBuilder.build());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams
                    ((int) getResources().getDimension(R.dimen.cash_main_tab_width),
                            (int) getResources().getDimension(R.dimen.cash_main_tab_width));
            lp.gravity = Gravity.CENTER;
            tabView.getTitleView().setLayoutParams(lp);
            tabView.getTitleView().setPadding(0, 10, 0, 10);
            mLeftTabLayout.setTabMode(VerticalTabLayout.TAB_MODE_SCROLLABLE);
            mLeftTabLayout.addTab(tabView);
            addFragments(i);
        }
        MemberDetailFragment fragment = MemberDetailFragment.newInstance();
        fragments.add(fragment);
        AddCardFragment cardFragment = AddCardFragment.newInstance();
        fragments.add(cardFragment);

    }

    private void addFragments(int positon) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString(TEST, getResources().getString(mTabText[positon]));
        switch (positon) {
            case 0:
                fragment = TableMainFragment.newInstance();
                break;
            case 1:
                fragment = OrdersFragment.newInstance();
                break;
            case 2:
                fragment = MemberMainFragment.newInstance();
                break;
            case 3:
                fragment = BooksOffFragment.newInstance();
                break;
            case 4:
                fragment = PrintMainFragment.newInstance();
                break;

        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(EventRouter fragmentEvent) {
        switch (fragmentEvent.getAction()) {
            case MEMBER_DETAIL:
                final Member member = (Member) JSONManager.getInstance().parseObject(fragmentEvent.getData()+"", Member.class);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MemberDetailFragment) fragments.get(5)).updateId(member.getId() + "");
                    }
                }, 500);
                mFragmentNavigator.showFragment(5);
                break;
            case MEMBER_DETAIL_BACK:
                mFragmentNavigator.showFragment(2);
                break;
            case CARD_FRAGMENT:
                mFragmentNavigator.showFragment(6);
                break;
            case TABLE_BACK:
                if (mLeftLayout.getVisibility() == View.GONE) {
                    mLeftLayout.setVisibility(View.VISIBLE);
                } else {
                    mLeftLayout.setVisibility(View.GONE);
                }
                break;
            case FAN_JIE_ZHANG:
               final Table table = (Table) fragmentEvent.getData();
                mFragmentNavigator.showFragment(0);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((TableMainFragment) fragments.get(0)).fanjiezhang(table);
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mFragmentNavigator.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mFragmentNavigator.getCurrentFragment() instanceof BaseFragment) {
            if (((BaseFragment) mFragmentNavigator.getCurrentFragment()).onBackPress()) {
                return;
            }
        }
        SPUtil.getInstance().clear();
        startActivity(new Intent(this, OrderSheetLoginActivity.class));
    }
}
