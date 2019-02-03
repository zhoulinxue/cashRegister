package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.rmondjone.locktableview.LockTableView;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.NUllContact;
import com.shigoo.cashregister.mvp.presenter.NullPresenter;
import com.shigoo.cashregister.utils.ChartUtil;
import com.zx.mvplibrary.MvpFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class PrintMainFragment extends MvpFragment<NullPresenter> implements NUllContact.view {
    private static final int DEFAULT_POSITION = 0;
    @BindView(R.id.tabLayout)
    TabLayout mTableLayout;
    private List<String> titleList;
    private List<TextView> titleView = new ArrayList<>();
    private List<TextView> lineView = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    @BindColor(R.color.menber_main_tab_title_color)
    int nomalTvColor;
    @BindColor(R.color.menber_main_tab_title_color_pre)
    int selectedColor;
    private int position;
    ArrayList<ArrayList<String>> mList = new ArrayList<>();
    private FragmentNavigator mFragmentNavigator;

    public static PrintMainFragment newInstance() {
        PrintMainFragment fragment = new PrintMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NullPresenter onCtreatPresenter() {
        return new NullPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.print_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        iniTalLayout();
        initFragment();
    }

    private void initFragment() {
        fragments.add(PrintSaleListFragment.newInstance());
        fragments.add(PrintKindListFragment.newInstance());
        fragments.add(PrintSaleDetailListFragment.newInstance());
        fragments.add(PrintTableConsumeListFragment.newInstance());
        mFragmentNavigator = new FragmentNavigator(getChildFragmentManager(), new FragmentNavigatorAdapter() {
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
        }, R.id.ordersheet_print_container);
    }

    private void iniTalLayout() {
        titleList = Arrays.asList(getResources().getStringArray(R.array.print_title));
        for (String title : titleList) {
            TabLayout.Tab tab = mTableLayout.newTab();
            View view = LayoutInflater.from(getContext()).inflate(R.layout.print_table_item_layout, null);
            TextView name = view.findViewById(R.id.member_member_text);
            TextView line = view.findViewById(R.id.member_member_line);
            name.setText(title);
            tab.setCustomView(view);
            mTableLayout.addTab(tab);
            titleView.add(name);
            lineView.add(line);
        }
        mTableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelect(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setSelect(DEFAULT_POSITION);
    }

    private void setSelect(int defaultPosition) {
        position = defaultPosition;
        for (int i = 0; i < titleList.size(); i++) {
            if (defaultPosition == i) {
                titleView.get(i).setTextColor(selectedColor);
                lineView.get(i).setVisibility(View.VISIBLE);
                if (i < fragments.size())
                    mFragmentNavigator.showFragment(defaultPosition);
            } else {
                titleView.get(i).setTextColor(nomalTvColor);
                lineView.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mFragmentNavigator.onSaveInstanceState(outState);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mFragmentNavigator.onCreate(savedInstanceState);
        mFragmentNavigator.setDefaultPosition(0);
        mFragmentNavigator.showFragment(0);
    }
}
