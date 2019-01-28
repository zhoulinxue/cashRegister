package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.PrintContacts;
import com.shigoo.cashregister.mvp.contacts.PrintFragmentContact;
import com.shigoo.cashregister.mvp.presenter.PrintFragmentPresenter;
import com.xgsb.datafactory.bean.KindRecivebean;
import com.xgsb.datafactory.bean.Printbean;
import com.xgsb.datafactory.bean.SaleCountbean;
import com.xgsb.datafactory.bean.TableCosumbean;
import com.zx.mvplibrary.BaseFragment;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.network.Param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

import static com.shigoo.cashregister.activitys.CashRigisterMainActivity.TEST;


/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class PrintFragment extends MvpFragment<PrintFragmentPresenter> implements PrintFragmentContact.view, onOperateLisenter {
    private static final int DEFAULT_POSITION = 0;
    @BindView(R.id.tabLayout)
    TabLayout mTableLayout;
    private List<String> titleList;
    private List<TextView> titleView = new ArrayList<>();
    private List<TextView> lineView = new ArrayList<>();
    @BindColor(R.color.menber_main_tab_title_color)
    int nomalTvColor;
    @BindColor(R.color.menber_main_tab_title_color_pre)
    int selectedColor;
    private int position;

    public static PrintFragment newInstance() {
        PrintFragment fragment = new PrintFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PrintFragmentPresenter onCtreatPresenter() {
        return new PrintFragmentPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.print_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        iniTalLayout();
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
            } else {
                titleView.get(i).setTextColor(nomalTvColor);
                lineView.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getSaleList(Param.Keys.TOKEN, getToken());
        mPresenter.getKindRecive(Param.Keys.TOKEN, getToken());
        mPresenter.getSaleCountList(Param.Keys.TOKEN, getToken());
        mPresenter.getTableCosumList(Param.Keys.TOKEN, getToken());
    }

    @Override
    public void onSaleResult(List<Printbean> msg) {

    }

    @Override
    public void onKindResult(List<KindRecivebean> list) {

    }

    @Override
    public void onSaleCountList(List<SaleCountbean> list) {

    }

    @Override
    public void onTableConsumList(List<TableCosumbean> list) {

    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void getTableInfo(Request request) {

    }

    @Override
    public void operateHandle(Request request) {

    }

    @Override
    public void searchOperate(Request request) {

    }

    @Override
    public void currentPage(Request request) {

    }

    @Override
    public void orderDetailsData(Request request) {

    }

    @Override
    public void handoverPrint(Request request) {

    }

    @Override
    public void handDutyHistroyListPrint(Request request) {

    }

    @Override
    public void getPayNumOrderDetailsData(Request request) {

    }
}
