package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.zx.mvplibrary.BaseCustomView;
import com.zx.mvplibrary.wedgit.FullScreenNumberKeyboardView;
import com.zx.network.Param;

import butterknife.BindView;

public class InputNumberView extends BaseCustomView implements FullScreenNumberKeyboardView.OnNumberClickListener {
    @BindView(R.id.ordersheet_number_view)
    FullScreenNumberKeyboardView mNumberView;
    @BindView(R.id.ordersheet_number_tv)
    TextView mNumberTv;
    @BindView(R.id.ordersheet_number_result_btn)
    TextView mSureTv;
    @BindView(R.id.ordersheet_number_unit_tv)
    TextView mUnitTv;
    @BindView(R.id.ordersheet_number_title_tv)
    TextView mTitleTv;
    @BindView(R.id.ordersheet_number_finish_btn)
    ImageView mBackImg;
    @BindView(R.id.int_card_number_edite)
    EditText editText;
    private NumberViewResult mResult;
    private StringBuffer buffer;
    private String[] numberItem;
    private boolean isInteger = true;
    private  boolean isShuaka=false;

    public InputNumberView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }

    @Override
    protected void initView(Context context, View rootView) {
        buffer = new StringBuffer();
        singleClickOnMinutes(mSureTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResult != null) {
                    String result = mNumberTv.getText().toString();
                    if (TextUtils.isEmpty(result) || Integer.valueOf(result) == 0) {
                        result = "0";
                    }
                    mResult.onSure(result,isShuaka);
                }
            }
        });
        singleClickOnMinutes(mBackImg, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mResult != null) {
                    mResult.onBack();
                }
            }
        });
        mNumberView.setOnNumberClickListener(this);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    buffer=new StringBuffer();
                    onNumberReturn(textView.getText().toString());
                    if (mResult != null) {
                        mResult.onSure(mNumberTv.getText().toString(),true);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.ordersheet_int_put_number_layout;
    }

    @Override
    public void onNumberReturn(String number) {
        if ((TextUtils.isEmpty(buffer.toString()) || (buffer.toString().startsWith("0") && isInteger))) {
            buffer = new StringBuffer();
        }
        buffer.append(number);
        mNumberTv.setText(buffer.toString());
    }

    @Override
    public void onNumberDelete() {
        if (buffer.length() - 1 >= 0) {
            buffer.replace(buffer.length() - 1, buffer.length(), "");
            mNumberTv.setText(TextUtils.isEmpty(buffer.toString()) ? "0" : buffer.toString());
        } else {
            buffer = new StringBuffer();
            mNumberTv.setText("0");
        }
    }

    public void setResult(NumberViewResult mResult) {
        this.mResult = mResult;
    }

    public void setHint(String s) {
        mNumberTv.setHint(s);
        isInteger = false;
        mNumberTv.setTextSize(getContext().getResources().getDimension(R.dimen.ordersheet_14sp));
    }

    public interface NumberViewResult {
        void onSure(String number,boolean isInteger);

        void onBack();

    }

    public void hideUnitTv(boolean ishide) {
        mUnitTv.setVisibility(ishide ? View.INVISIBLE : View.VISIBLE);
    }

    public void setUnitTv(String unit) {
        this.mUnitTv.setText(unit);
    }

    public void setTitleTv(String title) {
        this.mTitleTv.setText(title);
    }

    public void setCurrentNum(String currentNum) {
        buffer = new StringBuffer();
        buffer.append(currentNum);
        mNumberTv.setText(currentNum);
    }
}
