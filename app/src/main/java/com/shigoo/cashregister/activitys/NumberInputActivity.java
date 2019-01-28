package com.shigoo.cashregister.activitys;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.customViews.InputNumberView;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.BaseActivity;
import com.zx.network.Param;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberInputActivity extends BaseActivity implements InputNumberView.NumberViewResult {
    @BindView(R.id.ordersheet_nuber_view_container)
    FrameLayout mContainer;
    InputNumberView mInputNumberView;
    private Numberbean mNumberbean;


    @Override
    protected int initLayout() {
        return R.layout.ordersheet_number_input_activity;
    }

    @Override
    protected void onCreateView() {
        ButterKnife.bind(this);
        mInputNumberView = new InputNumberView(this, mContainer);
        mInputNumberView.setResult(this);
        mNumberbean = getIntent().getParcelableExtra(Param.Keys.NUMBER);
        if (mNumberbean != null) {
            mInputNumberView.setTitleTv(mNumberbean.getTitle());
            mInputNumberView.setUnitTv(mNumberbean.getUnit());
            if (TextUtils.isEmpty(mNumberbean.getCurrentNum())) {
                mNumberbean.setCurrentNum("0");
            }
            mInputNumberView.setCurrentNum(AppUtil.getFloatFromString(mNumberbean.getCurrentNum()).intValue() + "");
        } else {
            showToast("数据错误");
            finish();
        }
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    public void onSure(String number, boolean isInteger) {
        mNumberbean.setCurrentNum(number + "");
        EventBus.getDefault().post(new EventRouter(EventBusAction.SET_NUMBER, mNumberbean));
        finish();
    }

    @Override
    public void onBack() {
        finish();
    }
}
