package com.shigoo.cashregister.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * viewpager嵌套adapter
 */
public class TabPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;


    public TabPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
