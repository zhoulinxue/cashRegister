package com.shigoo.cashregister.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.activitys.AddAndUpdateMemberActivity;
import com.shigoo.cashregister.customViews.MemberListHeaderView;
import com.shigoo.cashregister.customViews.RechargeView;
import com.shigoo.cashregister.mvp.contacts.AddCardContact;
import com.shigoo.cashregister.mvp.contacts.MemberManageContact;
import com.shigoo.cashregister.mvp.presenter.AddCardPresenter;
import com.shigoo.cashregister.mvp.presenter.MemberManagePresenter;
import com.shigoo.cashregister.utils.DialogUtil;
import com.xgsb.datafactory.JSONManager;
import com.xgsb.datafactory.bean.Cardbean;
import com.xgsb.datafactory.bean.Countbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.MemberLevel;
import com.xgsb.datafactory.bean.Sellerbean;
import com.xgsb.datafactory.bean.WebData;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.MvpFragment;
import com.zx.mvplibrary.web.onOperateLisenter;
import com.zx.mvplibrary.wedgit.WebChartView;
import com.zx.network.Param;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.starteos.dappsdk.Request;

/**
 * Name: MemberListFragement
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //
 * Date: 2018-11-26 13:22
 */
public class MemberListFragement extends MvpFragment<MemberManagePresenter> implements MemberManageContact.view, TextView.OnEditorActionListener, RechargeView.RechargeLisenter, onOperateLisenter, AddCardContact.view {
    @BindView(R.id.web_page_header_layout)
    FrameLayout mHeaderView;
    @BindView(R.id.web_page_footer_layout)
    FrameLayout mFooterView;
    @BindView(R.id.web_chart_layout)
    FrameLayout mWebChartContainer;
    private MemberListHeaderView mMemberHeaderView;
    private List<String> mTitles;
    private int page = 1;
    private Request request;
    private WebChartView mWebCahrtView;
    private List<Member> mList;
    private Dialog mSearchDialog;
    private List<MemberLevel> mLevels;

    private boolean isExcute = false;
    private AddCardPresenter mCardPresenter;
    private int mCardType = 1;
    private RechargeView mRechargeView;
    private TextView entityCardTv;
    private EditText mInputEdite, mobileTv;
    RelativeLayout mInputLayout, mCardInputLayout;


    public static MemberListFragement newInstance() {
        MemberListFragement fragment = new MemberListFragement();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected MemberManagePresenter onCtreatPresenter() {
        return new MemberManagePresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.member_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        org.greenrobot.eventbus.EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        mCardPresenter = new AddCardPresenter(this);
        mRechargeView = new RechargeView(getContext(), this);
        mTitles = Arrays.asList(getResources().getStringArray(R.array.menber_tab_title));
        mWebCahrtView = new WebChartView(view.getContext(), mWebChartContainer, this, mHandler);
        mMemberHeaderView = new MemberListHeaderView(view.getContext(), mHeaderView);
        mMemberHeaderView.getEditText().setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mMemberHeaderView.getEditText().setOnEditorActionListener(this);
        mMemberHeaderView.getAddCard().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.CARD_FRAGMENT));
            }
        });
        initSearchMemberDialog();
        mMemberHeaderView.getAddMember().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddAndUpdateMemberActivity.class));
            }
        });
        mWebCahrtView.loadDefaultUrl();
    }

    private void initSearchMemberDialog() {
        final View inputView = LayoutInflater.from(getContext()).inflate(R.layout.input_member_dialog, null);
        final EditText memberNumedite = inputView.findViewById(R.id.member_input_edite);
        memberNumedite.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        memberNumedite.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    mPresenter.searchMembers(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, memberNumedite.getText().toString());
                    return true;
                }
                return false;
            }
        });
        TextView searchBtn = inputView.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.searchMembers(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, memberNumedite.getText().toString());
            }
        });
        memberNumedite.setInputType(InputType.TYPE_CLASS_NUMBER);
        mMemberHeaderView.getRecahrge().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSearchDialog == null) {
                    mSearchDialog = DialogUtil.contentDialog(getActivity(), inputView);
                } else {
                    mSearchDialog.show();
                }
            }
        });
    }

    private void showReChargeDialog() {
        if (mRechargeView != null) {
            mRechargeView.show();
        }
    }


    @Override
    protected void onInitData(Bundle savedInstanceState) {
        mPresenter.getCountData(Param.Keys.TOKEN, getToken());
        mPresenter.getMemberList(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, mMemberHeaderView.getKey(), Param.Keys.PAGE, page + "");
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mWebCahrtView.refresh("refresh");
        mPresenter.getCountData(Param.Keys.TOKEN, getToken());
    }

    @Override
    public void onGetMenbersCallback(List<Member> list) {
        this.mList = list;
        fillChart(list);
    }

    @Override
    public void onSearchResult(List<Member> list) {
        onGetMenbersCallback(list);
    }

    @Override
    public void onResult(Countbean countbean) {
        mMemberHeaderView.setCountbean(countbean);
    }

    @Override
    public void onAddSuc(String msg) {
        showToast(msg);
        onRefresh();
    }

    @Override
    public void onMemberList(List<MemberLevel> levels) {
        mLevels = levels;
    }

    @Override
    public void onMemberResult(Member member) {
        // todo show recharge dialog
        if (mSearchDialog != null && mSearchDialog.isShowing()) {
            if (member != null) {
                mSearchDialog.dismiss();
                mRechargeView.setUserInfo(member);
                mRechargeView.show();
            } else {
                showToast("未查找到会员信息");
            }
        } else {
            List<Member> list = new ArrayList<>();
            list.add(member);
            fillChart(list);
        }
    }

    private void fillChart(List<Member> list) {
        if (request != null) {
            String json = WebData.newInstance().getMemberWebData(list, mWebCahrtView.getWidth(), mWebCahrtView.getHight());
            mWebCahrtView.callback(request, json);
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            mWebCahrtView.refresh("refresh");
            return true;
        }
        return false;
    }

    @Override
    public void initWebview(Request request) {

    }

    @Override
    public void onGettableInfo(final Request request) {
        this.request = request;
        if (mList != null && mList.size() != 0) {
            onGetMenbersCallback(mList);
        } else {
            mPresenter.getMemberList(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, mMemberHeaderView.getKey(), Param.Keys.PAGE, page + "");
        }
    }

    @Override
    public void onOperate(Request request) {
        String operate = request.getParams().optString("method");
        switch (operate) {
            case "充值":
                Member mRechargeMember = (Member) JSONManager.getInstance().parseObject(request.getParams().opt("row_data") + "", Member.class);
                mRechargeView.setUserInfo(mRechargeMember);
                showReChargeDialog();
                break;
            case "查看":
                org.greenrobot.eventbus.EventBus.getDefault().post(new EventRouter(EventBusAction.MEMBER_DETAIL, new String[]{request.getParams().optString("row_data")}));
                break;
        }
    }

    @Override
    public void onSearch(Request request) {
        this.request = request;
        mPresenter.searchMembers(Param.Keys.TOKEN, getToken(), Param.Keys.DATA, mMemberHeaderView.getKey(), Param.Keys.PAGE, page + "");
    }

    @Override
    public void onResult(List<Cardbean> cardbean) {

    }

    @Override
    public void onAddresult(String msg) {

    }

    @Override
    public void onDeleteResult(String msg) {

    }

    @Override
    public void onCodeCardError(String msg) {

    }

    @Override
    public void onCardMsg(Cardbean cardbean) {
        mCardInputLayout.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mobileTv.clearFocus();
        entityCardTv.setText(cardbean.getCoding_card());
        mInputEdite.setText("");
        isExcute = false;
        hideInputMethod();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(EventRouter event) {
        switch (event.getAction()) {
            case SELLER_INFO:
                if (mRechargeView != null) {
                    //处理逻辑
                    mRechargeView.setSellerInfo((Sellerbean) event.getData());
                }
                break;
            case MEMBER_MSG:
                onRefresh();
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        org.greenrobot.eventbus.EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResultSuc() {
        onRefresh();
    }

    @Override
    public boolean onBackPress() {
        AppLog.print("mSearchDialog");
        if (mSearchDialog != null && mSearchDialog.isShowing()) {
            mSearchDialog.dismiss();
            return true;
        }
        if (mRechargeView != null && mRechargeView.isShowing()) {
            mRechargeView.dismiss();
            return true;
        }
        return super.onBackPress();
    }
}
