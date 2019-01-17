package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.zx.mvplibrary.BaseCustomView;

import butterknife.BindColor;
import butterknife.BindView;


/**
 * Name: MemberTableTitleView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 15:54
 */
public class MemberTableTitleView extends BaseCustomView {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindColor(R.color.menber_main_tab_title_color)
    int nomalTvColor;
    @BindColor(R.color.menber_main_tab_title_color_pre)
    int selectedColor;
    TextView mMemberTv, mRechargeTv, mConsumeTv, mMenberLine, mRechargeLine, mConsumeLine;
    private int DEFAULT_TAB = 0;

    public MemberTableTitleView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    @Override
    protected void initView(Context context, View rootView) {
        initTabLayout(context);
    }

    public MemberTableTitleView(Context context) {
        super(context);
    }

    @Override
    public int initLayout() {
        return R.layout.member_table_layout;
    }

    private void initTabLayout(Context context) {
        TabLayout.Tab memberTab = mTabLayout.newTab();
        TabLayout.Tab memberRechaegeTAb = mTabLayout.newTab();
        TabLayout.Tab memberConsumeTab = mTabLayout.newTab();
        View memberView = LayoutInflater.from(context).inflate(R.layout.member_table_menber_layout, null);
        View memberRechaege = LayoutInflater.from(context).inflate(R.layout.member_table_recharge_layout, null);
        View memberConsume = LayoutInflater.from(context).inflate(R.layout.member_table_consume_layout, null);
        mMemberTv = memberView.findViewById(R.id.member_member_text);
        mMenberLine = memberView.findViewById(R.id.member_member_line);
        mRechargeTv = memberRechaege.findViewById(R.id.member_recharge_text);
        mRechargeLine = memberRechaege.findViewById(R.id.member_recharge_line);
        mConsumeTv = memberConsume.findViewById(R.id.member_consume_text);
        mConsumeLine = memberConsume.findViewById(R.id.member_consume_line);
        memberTab.setCustomView(memberView);
        memberRechaegeTAb.setCustomView(memberRechaege);
        memberConsumeTab.setCustomView(memberConsume);

        mTabLayout.addTab(memberTab);
        mTabLayout.addTab(memberRechaegeTAb);
        mTabLayout.addTab(memberConsumeTab);
        showTabView(DEFAULT_TAB);
    }

    public void showTabView(int position) {
            switch (position) {
                case 0:
                    mMemberTv.setTextColor(selectedColor);
                    mMenberLine.setVisibility(View.VISIBLE);
                    mRechargeTv.setTextColor(nomalTvColor);
                    mRechargeLine.setVisibility(View.INVISIBLE);
                    mConsumeTv.setTextColor(nomalTvColor);
                    mConsumeLine.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mMemberTv.setTextColor(nomalTvColor);
                    mMenberLine.setVisibility(View.INVISIBLE);
                    mRechargeTv.setTextColor(selectedColor);
                    mRechargeLine.setVisibility(View.VISIBLE);
                    mConsumeTv.setTextColor(nomalTvColor);
                    mConsumeLine.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    mMemberTv.setTextColor(nomalTvColor);
                    mMenberLine.setVisibility(View.INVISIBLE);
                    mRechargeTv.setTextColor(nomalTvColor);
                    mRechargeLine.setVisibility(View.INVISIBLE);
                    mConsumeTv.setTextColor(selectedColor);
                    mConsumeLine.setVisibility(View.VISIBLE);
                    break;
            }
    }

    public void setLisenter(TabLayout.OnTabSelectedListener mLisenter) {
        mTabLayout.addOnTabSelectedListener(mLisenter);
    }
}
