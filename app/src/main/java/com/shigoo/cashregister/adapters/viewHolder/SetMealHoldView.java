package com.shigoo.cashregister.adapters.viewHolder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.adapters.SetMealChildListAdapter;
import com.shigoo.cashregister.adapters.SetMealListAdapter;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetMealHoldView extends BaseViewHolder {
    @BindView(R.id.ordersheet_set_meal_groupname_tv)
    TextView mGroupName;
    @BindView(R.id.ordersheet_set_meal_rule_tv)
    TextView mNumTv;
    @BindView(R.id.ordersheet_set_meal_group_recyclerview)
    RecyclerView mChildRecycler;
    private SetMealListAdapter.onNumChanage mLisenter;


    public SetMealHoldView(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    public void setItem(final SetMealGroupbean item) {
        if (item.getGroup_dishes() != null && item.getGroup_dishes().size() != 0) {
            final SetMealChildListAdapter mMealChildListAdapter = new SetMealChildListAdapter(R.layout.ordersheet_setmeal_child_layout, item.getGroup_dishes());
            mMealChildListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Dishesbean childitem = mMealChildListAdapter.getData().get(position);
                    switch (view.getId()) {
                        case R.id.ordersheet_decrice_tv:
                            childitem.setNum(childitem.getNum() == 0 ? 0 : childitem.getNum() - 1);
                            break;
                        case R.id.ordersheet_add_tv:
                            if (item != null) {
                                if (("0".equals(item.getIs_reply()) && childitem.getNum() == 1) || !item.isMore()) {
                                    Toast.makeText(itemView.getContext(), "不能再点该菜品", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            childitem.setNum(childitem.getNum() + 1);
                            break;
                    }
                    mMealChildListAdapter.notifyItemChanged(position);
                }
            });
            mMealChildListAdapter.setLisenter(mLisenter);
            mMealChildListAdapter.setSetMealGroupbean(item);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mChildRecycler.setLayoutManager(manager);
            mChildRecycler.setAdapter(mMealChildListAdapter);
        }
        mGroupName.setText(item.getGroup_name());
        mNumTv.setText(item.getRule());
    }

    public void setLisenter(SetMealListAdapter.onNumChanage lisenter) {
        this.mLisenter = lisenter;
    }
}
