package com.shigoo.cashregister.customViews;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shigoo.cashregister.R;
import com.xgsb.datafactory.bean.Countbean;
import com.zx.mvplibrary.BaseCustomView;
import com.zx.mvplibrary.wedgit.ClearableEditTextWithIcon;

import butterknife.BindView;

/**
 * Name: MemberListHeaderView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 13:25
 */
public class MemberListHeaderView extends BaseCustomView {

    @BindView(R.id.member_search_edit)
    ClearableEditTextWithIcon mEditText;
    @BindView(R.id.search_btn)
    ImageView mSearchImg;
    @BindView(R.id.menber_num_decript)
    TextView mTotalNum;
    @BindView(R.id.new_menber_num_decript)
    TextView mAddNum;
    @BindView(R.id.make_card)
    ImageView mAddCard;
    @BindView(R.id.add_menbers)
    ImageView mAddMember;
    @BindView(R.id.recharge)
    ImageView mReCharge;

    public MemberListHeaderView(Context mContext, ViewGroup rootGroup) {
        super(mContext, rootGroup);
    }

    public MemberListHeaderView(Context mContext) {
        super(mContext);
    }

    @Override
    protected void initView(Context context, View rootView) {
        mEditText.setIconResource(R.mipmap.icon_seach);
    }

    @Override
    public int initLayout() {
        return R.layout.members_recent_header_layout;
    }

    public String getKey() {
        if (mEditText != null && !TextUtils.isEmpty(mEditText.getText().toString())) {
            return mEditText.getText().toString();
        }
        return "";
    }

    public ClearableEditTextWithIcon getEditText() {
        return mEditText;
    }

    public ImageView getSearchImg() {
        return mSearchImg;
    }

    public void setCountbean(Countbean countbean) {
        String total = String.format("共  %s  个会员", countbean.getCount_member());
        String addNum = String.format("今日新增  %s  个", countbean.getThis_count_member());
        SpannableString bigSpanT = getSpannable(total, 1, 3 + (countbean.getCount_member() + "").length());
        SpannableString colorSpanC = getSpannable(addNum, 4, 6 + (countbean.getThis_count_member() + "").length());
        mTotalNum.setText(bigSpanT);
        mAddNum.setText(colorSpanC);

        mTotalNum.setText(getSpan(mTotalNum.getText(), 1, 3 + (countbean.getCount_member() + "").length()));
        mAddNum.setText(getSpan(mAddNum.getText(), 4, 6 + (countbean.getThis_count_member() + "").length()));
    }

    private SpannableString getSpannable(CharSequence src, int start, int end) {
        SpannableString spannableString = new SpannableString(src);
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.5f);
        spannableString.setSpan(sizeSpan01, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    private SpannableString getSpan(CharSequence src, int start, int end) {
        SpannableString spannableString = new SpannableString(src);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getView().getResources().getColor(R.color.member_detail_title_color));
        spannableString.setSpan(colorSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public ImageView getAddCard() {
        return mAddCard;
    }

    public ImageView getAddMember() {
        return mAddMember;
    }

    public ImageView getRecahrge() {
        return mReCharge;
    }
}
