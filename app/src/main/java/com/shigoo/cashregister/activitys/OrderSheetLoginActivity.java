package com.shigoo.cashregister.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.mvp.contacts.CashLoginContact;
import com.shigoo.cashregister.mvp.presenter.CashLoginPresenter;
import com.xgsb.datafactory.bean.User;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.MvpActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Name: SplashActivity
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-21 12:37
 */
public class OrderSheetLoginActivity extends MvpActivity<CashLoginPresenter> implements CashLoginContact.view {
    @BindView(R.id.ordersheet_cash_login_num_edit)
    AppCompatEditText mNumberEdite;
    @BindView(R.id.ordersheet_cash_login_psw_edit)
    AppCompatEditText mPswEdite;
    @BindView(R.id.ordersheet_cash_login_tv)
    TextView mLoginTv;

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_login_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        singleClickOnMinutes(mLoginTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = loginUser(mNumberEdite.getText().toString(), mPswEdite.getText().toString());
                if (isReady(user)) {
                    mPresenter.cashLogin(user);
                }
            }
        });
    }

    /**
     * 校验 工号 及 密码 格式
     *
     * @param user
     * @return
     */
    private boolean isReady(User user) {
        if (user != null) {
            if (TextUtils.isEmpty(user.getUserName())) {
                showToast(R.string.ordersheet_empty_name);
                return false;
            }
            if (TextUtils.isEmpty(user.getPsw())) {
                showToast(R.string.ordersheet_empty_psw);
                return false;
            }
        }
        return true;
    }

    /**
     * 组装登录用户
     *
     * @param userNum
     * @param userPsw
     * @return
     */
    private User loginUser(String userNum, String userPsw) {
        return new User(userNum, userPsw);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected CashLoginPresenter onCtreatPresenter() {
        return new CashLoginPresenter(this);
    }

    @Override
    public void loginCallbak(User user) {
        onLoginSuc(user);
        showToast("登录成功");
        if (AppUtil.isOrderDishes(this)) {
            EventBus.getDefault().post(EventBusAction.LOGIN_SUC);
        } else {
            startActivity(new Intent(this, CashRigisterMainActivity.class));
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if (AppUtil.isOrderDishes(this)) {
            EventBus.getDefault().post(EventBusAction.MAIN);
            finish();
        } else {
            showToast("不能再退了");
        }
    }
}
