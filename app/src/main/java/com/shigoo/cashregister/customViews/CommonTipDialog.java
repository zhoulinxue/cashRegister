package com.shigoo.cashregister.customViews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.zx.api.api.utils.AppUtil;


/**
 * Created by chenke on 2016/11/6.
 */
public class CommonTipDialog extends Dialog {
    private Activity mContext;

    public CommonTipDialog(Activity context, int theme) {
        super(context, theme);
        mContext = context;
    }

    public CommonTipDialog(Activity context) {
        super(context);
        mContext = context;
    }

    public static class Builder {
        private ImageView dialog_content_iv;
        private int res;

        private Activity context;

        private String title;

        private String message;

        private int left, right, top, bottom;

        private int buttonId = -1;

        String[] items = null;

        private int titleTextColor = Integer.MAX_VALUE;

        private int buttonTextColor = Integer.MAX_VALUE;

        private int buttonbackgroundId = -1;

        private String leftbtnText;

        private String rightBtnText;
        private boolean isOutSideDismiss;
        private View contentView;
        private boolean Cancelable = true;

        private TextView lefTextView;
        private TextView rightTextView;
        private TextView content;
        private TextView textTitle;

        private OnClickListener leftBtnClickListener,
                rightBtnClickListener;

        public Builder(Activity context) {
            this.context = context;
        }

        public Builder(Activity context, boolean isOutSideDismiss) {
            this.context = context;
            this.isOutSideDismiss = isOutSideDismiss;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setClosable(boolean canClose) {
            this.Cancelable = canClose;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setView(View v, int left, int top, int right, int bottom) {
            this.contentView = v;
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            return this;
        }

        public Builder setLeftBtn(String positiveButtonText, OnClickListener listener) {
            this.leftbtnText = positiveButtonText;
            this.leftBtnClickListener = listener;
            return this;
        }

        public Builder setRightBtn(String negativeButtonText, OnClickListener listener) {
            this.rightBtnText = negativeButtonText;
            this.rightBtnClickListener = listener;
            return this;
        }

        public Builder setImageResource(int res) {
            this.res = res;
            return this;
        }

        public Builder setTextContentGravyLeft() {
            content.setGravity(Gravity.LEFT);
            textTitle.setTextSize(16);
            return this;
        }

        public CommonTipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CommonTipDialog dialog = new CommonTipDialog(context, R.style.dialog_round_corner);
            if (!isOutSideDismiss) {
                dialog.setCanceledOnTouchOutside(false);
            }
            View layout = inflater.inflate(R.layout.dialog_common_tip, null);
            lefTextView = ((TextView) layout.findViewById(R.id.dialog_left_tv));
            rightTextView = (TextView) layout.findViewById(R.id.dialog_right_tv);
            dialog_content_iv = (ImageView) layout.findViewById(R.id.dialog_content_iv);
            content = (TextView) layout.findViewById(R.id.dialog_content_tv);
            textTitle = (TextView) layout.findViewById(R.id.dialog_title);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.dialog_title)).setText(title);
            lefTextView.setText(leftbtnText);
            dialog_content_iv.setImageResource(res);
            if (res != 0) {
                dialog_content_iv.setVisibility(View.VISIBLE);
            }
            if (leftBtnClickListener != null) {
                lefTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leftBtnClickListener.onClick(dialog,
                                DialogInterface.BUTTON_POSITIVE);
                        lefTextView.setClickable(false);
                        dialog.cancel();
                    }
                });
            } else {
                lefTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }

            rightTextView.setText(rightBtnText);
            if (rightBtnClickListener != null) {
                rightTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rightBtnClickListener.onClick(dialog,
                                DialogInterface.BUTTON_NEGATIVE);
                        dialog.cancel();
                    }
                });
            } else {
                rightTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.dialog_content_tv))
                        .setText(message);
            } else {
                ((TextView) layout.findViewById(R.id.dialog_content_tv))
                        .setVisibility(View.GONE);
            }
            dialog.setCancelable(Cancelable);
            dialog.setContentView(layout);
            dialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {

                }
            });


            if (TextUtils.isEmpty(leftbtnText)) {
                lefTextView.setVisibility(View.GONE);
                layout.findViewById(R.id.dialog_common_line).setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(rightBtnText)) {
                rightTextView.setVisibility(View.GONE);
                layout.findViewById(R.id.dialog_common_line).setVisibility(View.GONE);
            }
            if (TextUtils.isEmpty(rightBtnText) && TextUtils.isEmpty(leftbtnText)) {
                layout.findViewById(R.id.dialog_linearlayout).setVisibility(View.GONE);
            }

            if (TextUtils.isEmpty(leftbtnText) && !TextUtils.isEmpty(rightBtnText)) {
                rightTextView.setBackgroundResource(R.drawable.common_dialog_btn_selector);
            } else if (!TextUtils.isEmpty(leftbtnText) && TextUtils.isEmpty(rightBtnText)) {
                lefTextView.setBackgroundResource(R.drawable.common_dialog_btn_selector);
            }
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (AppUtil.getScreenWidth(context) * 0.5);
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
            window.setContentView(layout);
            return dialog;
        }
    }
}

