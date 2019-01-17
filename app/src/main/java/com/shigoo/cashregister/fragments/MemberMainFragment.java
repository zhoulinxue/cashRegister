package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.customViews.MemberTableTitleView;
import com.zx.mvplibrary.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: MemberMainFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 09:53
 */
public class MemberMainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private MemberTableTitleView mTitleView;
    @BindView(R.id.member_table_layout_container)
    FrameLayout mTableLayoutContainer;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentNavigator mNavigator;

    public static MemberMainFragment newInstance() {
        MemberMainFragment fragment = new MemberMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void preOnCreatView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.members_main_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mTitleView = new MemberTableTitleView(view.getContext(), mTableLayoutContainer);
        mTitleView.setLisenter(this);
        initViewPager();
    }

    private void initFragmnet() {
        fragments.add(MemberListFragement.newInstance());
        fragments.add(RechargeDetailListFragment.newInstance());
        fragments.add(ConsumeDetailFragment.newInstance());
        fragments.add(MemberDetailFragment.newInstance());
    }

    private void initViewPager() {
        initFragmnet();
        mNavigator = new FragmentNavigator(getChildFragmentManager(), new FragmentNavigatorAdapter() {
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
        mNavigator.setDefaultPosition(0);
        showSeletedTable(mNavigator.getCurrentPosition());
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        ((BaseFragment) mNavigator.getCurrentFragment()).onRefresh();
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mNavigator.onCreate(savedInstanceState);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        showSeletedTable(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void showSeletedTable(int position) {
        mTitleView.showTabView(position);
        mNavigator.showFragment(position);
    }

    @Override
    public boolean onBackPress() {
        if(mNavigator.getCurrentFragment() instanceof BaseFragment){
           return  ((BaseFragment)mNavigator.getCurrentFragment()).onBackPress();
        }
        return super.onBackPress();

    }
}
