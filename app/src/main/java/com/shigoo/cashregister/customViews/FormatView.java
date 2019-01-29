package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shigoo.cashregister.R;
import com.shigoo.cashregister.customViews.viewChildClick.OrderListViewBriage;
import com.shigoo.cashregister.print.PrintManager;
import com.shigoo.cashregister.print.attr.Address;
import com.shigoo.cashregister.print.inter.PrintProviderInterface;
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
    private Dishesbean mCurrent;
    private Billbean mBillbean;
    onButtonClick mButtonLisenter;
    private OrderListViewBriage.onFormatChildClick mFormatClick;


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
            if (mFormatClick != null) {
                mFormatClick.onCopy(mBillbean);
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
                    mFormatClick.onReturnDishes(mCurrent);
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
                    mFormatClick.onChanagePrice(mCurrent);
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
                    mFormatClick.onDiscount(mCurrent);
                } else {
                    Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_che_dan_btn:
                //撤单
                if (mBillbean != null) {
                    mFormatClick.onChedan(mBillbean);
                } else {
                    Toast.makeText(getContext(), "账单信息错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ordersheet_dishes_format_tv:
                if (mFormatClick != null) {
                    if (mCurrent == null) {
                        Toast.makeText(getContext(), "未选中菜品", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mCurrent.isMultDishes()) {
                        mFormatClick.onFormatClick(mCurrent);
                    } else {
                        Toast.makeText(getContext(), "该菜品只有一个规格", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ordersheet_dishes_delete_btn:
                if (mFormatClick != null) {
                    if (mCurrent.hasBacked()) {
                        Toast.makeText(getContext(), "菜品已退", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mButtonLisenter.delete(mCurrent)) {
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
                if (mButtonLisenter != null) {
                    mButtonLisenter.onStatusClick(mCurrent);
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
                if (mButtonLisenter != null) {
                    mButtonLisenter.onStatusClick(mCurrent);
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
                if (mButtonLisenter != null) {
                    mButtonLisenter.onNumChanage(numadd);
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
                    if (mButtonLisenter != null) {
                        mButtonLisenter.onNumChanage(num);
                    }
                }
                break;
            case R.id.ordersheet_dishes_print_btn:
                // 打印预结单
                Toast.makeText(getContext(), "打印预结单", Toast.LENGTH_SHORT).show();
                Address address = new Address("192.168.188.250", 9100);
                PrintProviderInterface providerInterface = PrintManager.getInstance().creatWifiPrint(address);
                mButtonLisenter.printPaper(providerInterface);
                break;
            case R.id.ordersheet_dishes_cui_cai_btn:
                // 打催菜 单
                Toast.makeText(getContext(), mCuicTv.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ordersheet_dishes_format_remark_btn:
                if (mFormatClick != null) {
                    mFormatClick.onRemarkClick(mCurrent);
                }
                break;

        }
    }

    public void setCurrentBill(Billbean billbean) {
        this.mBillbean = billbean;
        if (billbean != null) {
            if ("已结账".equals(billbean.getLocal_status())) {
                mCheDanTv.setVisibility(View.GONE);
            } else {
                mCheDanTv.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setFormatClick(OrderListViewBriage.onFormatChildClick formatClick) {
        this.mFormatClick = formatClick;
    }

    public void setClickLisenter(onButtonClick orderDishMenuListView) {
        this.mButtonLisenter = orderDishMenuListView;
    }

    public interface onButtonClick {

        public void onNumChanage(int i);

        public void onStatusClick(Dishesbean current);

        public boolean delete(Dishesbean mCurrent);

        void printPaper(PrintProviderInterface providerInterface);
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
