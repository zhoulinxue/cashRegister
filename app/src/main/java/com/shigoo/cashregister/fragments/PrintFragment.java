package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.zx.mvplibrary.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.shigoo.cashregister.activitys.CashRigisterMainActivity.TEST;


/**
 * Name: TableFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-01 14:27
 */
public class PrintFragment extends BaseFragment {
    @BindView(R.id.test_text)
    TextView testTv;


    public static PrintFragment newInstance() {
        PrintFragment fragment = new PrintFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void preOnCreatView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.text_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        testTv.setText(argment.getString(TEST));
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }
}
