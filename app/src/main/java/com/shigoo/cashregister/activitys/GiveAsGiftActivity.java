package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.fragments.GiveAsGiftFragment;
import com.shigoo.cashregister.fragments.GiveDetailListFragment;
import com.zx.api.api.utils.SPUtil;
import com.zx.mvplibrary.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiveAsGiftActivity extends BaseActivity {
    private FragmentNavigator mFragmentNavigator;
    private List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.ordersheet_logo_title_tv)
    TextView mGiveTv;
    @BindView(R.id.ordersheet_give_list_tv)
    TextView mGiveListTv;
    @BindView(R.id.ordersheet_back_img)
    ImageView mBackImg;

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_give_as_gift_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        fragments.add(GiveAsGiftFragment.newInstance());
        fragments.add(GiveDetailListFragment.newInstance());
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
        mGiveTv.setTextColor(getResources().getColor(R.color.ordersheet_white));
        singleClickOnMinutes(mGiveTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGiveTv.setTextColor(getResources().getColor(R.color.ordersheet_white));
                mGiveListTv.setTextColor(getResources().getColor(R.color.ordersheet_table_des_color));
                mFragmentNavigator.showFragment(0);
            }
        });
        singleClickOnMinutes(mGiveListTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGiveListTv.setTextColor(getResources().getColor(R.color.ordersheet_white));
                mGiveTv.setTextColor(getResources().getColor(R.color.ordersheet_table_des_color));
                mFragmentNavigator.showFragment(1);
            }
        });
        singleClickOnMinutes(mBackImg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtil.getInstance().clear();
                finish();
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }
}
