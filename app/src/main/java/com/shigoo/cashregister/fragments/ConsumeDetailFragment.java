package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.customViews.ConsumeHeaderView;
import com.shigoo.cashregister.mvp.contacts.ConsumeContact;
import com.shigoo.cashregister.mvp.presenter.ConsumePresenter;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.xgsb.datafactory.bean.WebData;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

/**
 * Name: RechargeDetailListFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 10:04
 */
public class ConsumeDetailFragment extends MvpFragment<ConsumePresenter> implements ConsumeContact.view, onOperateLisenter {
    @BindView(R.id.web_page_header_layout)
    FrameLayout mHeaderViewLayout;
    @BindView(R.id.web_page_footer_layout)
    FrameLayout mFooterView;
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebChartContainer;
    private ConsumeHeaderView mHeader;
    private WebChartView mWebCahrtView;
    private Request mRequest;
    private ConsumeListData mList;

    public static ConsumeDetailFragment newInstance() {
        ConsumeDetailFragment fragment = new ConsumeDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        onInitData(null);
    }

    @Override
    protected ConsumePresenter onCtreatPresenter() {
        return new ConsumePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.consume_layout;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        mHeader = new ConsumeHeaderView(getContext(), mHeaderViewLayout);
        mWebCahrtView = new WebChartView(view.getContext(), mWebChartContainer, this, mHandler);
        mWebCahrtView.loadDefaultUrl();
        mHeader.getSearchbtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getConsumeList(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, mHeader.getKey(), Param.Keys.START_DATE, mHeader.getStartTime(), Param.Keys.END_DATE, mHeader.getEndTime());
            }
        });
        mHeader.getCleanBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHeader.clean();
                onRefresh();
            }
        });
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getConsumeList(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, mHeader.getKey(), Param.Keys.START_DATE, mHeader.getStartTime(), Param.Keys.END_DATE, mHeader.getEndTime());
    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void onGettableInfo(Request request) {
        mRequest = request;
        if (mList != null) {
            onGetConsumeListCallback(mList);
        }
    }

    @Override
    public void onOperate(Request request) {

    }

    @Override
    public void onSearch(Request request) {

    }

    @Override
    public void onGetConsumeListCallback(ConsumeListData consumeListData) {
        this.mList = consumeListData;
        if (mRequest != null) {
            if (consumeListData != null && consumeListData.getData() != null && consumeListData.getData().size() != 0) {
                mHeader.setQueryNum(consumeListData.getData().size());
                mHeader.setNum(consumeListData);
                String json = WebData.newInstance().getConsumeListData(consumeListData.getData(), mWebCahrtView.getWidth(), mWebCahrtView.getHight());
                AppLog.print("onGetConsumeListCallback" + json);
                mWebCahrtView.callback(mRequest, json);
            }
        }
    }
}
