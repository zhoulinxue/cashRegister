package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.enu.EventBusAction;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.mvplibrary.BaseCustomView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class FormatView extends BaseCustomView {
    @BindView(R.id.ordersheet_dishes_num_btn)
    TextView mNumTv;
    @BindView(R.id.ordersheet_dishes_format_tv)
    TextView mFormateTv;
    @BindView(R.id.ordersheet_dishes_format_remark_btn)
    TextView mRemarkTv;
    @BindView(R.id.ordersheet_dishes_w_btn)
    TextView mWdTv;
    @BindView(R.id.ordersheet_dishes_d_btn)
    TextView mDtv;
    @BindView(R.id.ordersheet_dishes_cui_cai_layout)
    LinearLayout mOrderFormat;
    @BindView(R.id.ordersheet_format_xd_layout)
    LinearLayout mXdLayout;
    @BindView(R.id.ordersheet_dishes_cui_cai_btn)
    TextView mCuicTv;
    @BindView(R.id.ordersheet_dishes_che_dan_btn)
    TextView mCheDanTv;
    onButtonClick mClickLisenter;
    private Dishesbean mCurrent;
    private Billbean mBillbean;


    public FormatView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    @Override
    protected void initView(Context context, View rootView) {

    }

    @Override
    public int initLayout() {
        return R.layout.ordersheet_format_layout;
    }

    protected void setDishes(Dishesbean dishes, boolean update) {
        mCurrent = dishes;
        mNumTv.setText(dishes.getLocal_num() + "");
        if (mCurrent.getOut_tag() == 0) {
            mWdTv.setText("外带");
        } else {
            mWdTv.setText("取消外带");
        }
        if (mCurrent.getWait_tag() == 0) {
            mDtv.setText("等叫");
            mCuicTv.setText("催菜");
        } else {
            mDtv.setText("取消等叫");
            mCuicTv.setText("起菜");
        }
    }

    @OnClick({R.id.ordersheet_dishes_format_tv,
            R.id.ordersheet_copy_tv,
            R.id.ordersheet_dishes_print_btn,
            R.id.ordersheet_dishes_cui_cai_btn,
            R.id.ordersheet_dishes_delete_btn,
            R.id.ordersheet_dishes_d_btn,
            R.id.ordersheet_dishes_w_btn,
            R.id.ordersheet_dishes_format_remark_btn,
            R.id.ordersheet_dishes_format_add_tv,
            R.id.ordersheet_dishes_format_jj_tv,
            R.id.ordersheet_dishes_tui_cai_btn,
            R.id.ordersheet_dishes_discount_btn,
            R.id.ordersheet_dishes_che_dan_btn,
            R.id.ordersheet_dishes_gai_jia_btn})
    public void onViewClick(View view) {
        if (view.getId() == R.id.ordersheet_copy_tv) {
            if (mClickLisenter != null) {
                mClickLisenter.onCopy();
            }
            return;
        }

        switch (view.getId()) {
            case R.id.ordersheet_dishes_tui_cai_btn:
                if (mCurrent != null) {
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //退菜
                    EventBus.getDefault().post(new EventRouter(EventBusAction.TUI_CAI, mCurrent));
                } else {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_gai_jia_btn:
                //改价
                if (mCurrent != null) {
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    EventBus.getDefault().post(new EventRouter(EventBusAction.GAI_JIA, mCurrent));
                } else {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_discount_btn:
                //打折
                if (mCurrent != null) {
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    EventBus.getDefault().post(new EventRouter(EventBusAction.DA_ZHE, mCurrent));
                } else {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_che_dan_btn:
                //撤单
                if (mBillbean != null) {
                    EventBus.getDefault().post(new EventRouter(EventBusAction.CHE_DAN, mBillbean));
                } else {
                    Toast.makeText(getContext(), "账单信息错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_format_tv:
                if (mClickLisenter != null) {
                    if (mCurrent == null) {
                        Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mCurrent.isMultDishes()) {
                        mClickLisenter.onFormatClick(mCurrent);
                    } else {
                        Toast.makeText(getContext(), "该菜品只有一个规格", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ordersheet_dishes_delete_btn:
                if (mClickLisenter != null) {
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mClickLisenter.delete(mCurrent)) {
                        mCurrent = null;
                    }
                }
                break;
            case R.id.ordersheet_dishes_d_btn:
                if (mCurrent.hasBacked()) {
                    Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrent.getWait_tag() == 0) {
                    mCurrent.setWait_tag(1);
                    mDtv.setText("取消等叫");
                } else {
                    mCurrent.setWait_tag(0);
                    mDtv.setText("等叫");
                }
                if (mClickLisenter != null) {
                    mClickLisenter.onStatusClick(mCurrent);
                }
                break;
            case R.id.ordersheet_dishes_w_btn:
                if (mCurrent.hasBacked()) {
                    Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrent.getOut_tag() == 0) {
                    mCurrent.setOut_tag(1);
                    mWdTv.setText("取消外带");
                } else {
                    mCurrent.setOut_tag(0);
                    mWdTv.setText("外带");
                }
                if (mClickLisenter != null) {
                    mClickLisenter.onStatusClick(mCurrent);
                }
                break;
            case R.id.ordersheet_dishes_format_add_tv:
                if (mCurrent == null) {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrent.hasBacked()) {
                    Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                    return;
                }
                int numadd = Integer.valueOf(mNumTv.getText().toString());
                numadd = numadd + 1;
                if (mClickLisenter != null) {
                    mClickLisenter.onNumChanage(numadd);
                }
                mNumTv.setText(numadd + "");
                break;
            case R.id.ordersheet_dishes_format_jj_tv:
                if (mCurrent == null) {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCurrent.hasBacked()) {
                    Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.valueOf(mNumTv.getText().toString());
                num = num - 1;
                if (num > 0) {
                    mNumTv.setText((num) + "");
                    if (mClickLisenter != null) {
                        mClickLisenter.onNumChanage(num);
                    }
                }
                break;
            case R.id.ordersheet_dishes_print_btn:
                // 打印预结单
                Toast.makeText(getContext(), "打印预结单", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ordersheet_dishes_cui_cai_btn:
                // 打催菜 单
                Toast.makeText(getContext(), mCuicTv.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ordersheet_dishes_format_remark_btn:
                if (mClickLisenter != null) {
                    mClickLisenter.onRemarkClick(mCurrent);
                }
                break;

        }
    }

    public void setClickLisenter(onButtonClick clickLisenter) {
        this.mClickLisenter = clickLisenter;
    }

    public void setCurrentBill(Billbean billbean) {
        this.mBillbean = billbean;
        if(billbean!=null) {
            if ("已结账".equals(billbean.getLocal_status())) {
                mCheDanTv.setVisibility(View.GONE);
            } else {
                mCheDanTv.setVisibility(View.VISIBLE);
            }
        }
    }

    public interface onButtonClick {
        public void onFormatClick(Dishesbean current);

        public void onRemarkClick(Dishesbean current);

        public void onNumChanage(int i);

        public void onStatusClick(Dishesbean current);

        public boolean delete(Dishesbean mCurrent);

        void onCopy();
    }

    public void setLocalStatus(String status) {
        switch (status) {
            case "已下单":
            case "已结账":
                if (!AppUtil.isOrderDishes(getContext())) {
                    mOrderFormat.setVisibility(View.VISIBLE);
                } else {
                    mOrderFormat.setVisibility(View.GONE);
                }
                mXdLayout.setVisibility(View.GONE);
                break;
            case "已开台":
            case "加菜":
                if (!AppUtil.isOrderDishes(getContext())) {
                    mOrderFormat.setVisibility(View.VISIBLE);
                } else {
                    mOrderFormat.setVisibility(View.GONE);
                }
                mXdLayout.setVisibility(View.VISIBLE);
                break;
            case "拆单支付":

                break;
        }
    }
}
