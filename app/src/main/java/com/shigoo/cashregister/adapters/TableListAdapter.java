package com.shigoo.cashregister.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shigoo.cashregister.adapters.viewHolder.TableHoldView;
import com.shigoo.cashregister.utils.TablesUtils;
import com.xgsb.datafactory.bean.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: TableListAdapter
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-12 16:04
 */
public class TableListAdapter extends BaseQuickAdapter<Table, TableHoldView> implements Filterable {
    private List<Table> backData;
    private Filter mFilter;

    public TableListAdapter(int layoutResId, @Nullable List<Table> data) {
        super(layoutResId, data);
        this.backData = data;
    }

    @Override
    protected void convert(TableHoldView helper, Table item) {
        helper.setItem(item);
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    //我们需要定义一个过滤器的类来定义过滤规则
    class MyFilter extends Filter {
        //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();
            List<Table> list;
            if (TextUtils.isEmpty(charSequence) || "全部区域".equals(charSequence)) {//当过滤的关键字为空的时候，我们则显示所有的数据
                list = backData;
            } else {//否则把符合条件的数据对象添加到集合中
                list = new ArrayList<>();
                for (Table table : TablesUtils.fillList(backData)) {
                    if (table.getRegion_name().equals(charSequence) || table.getLocal_status().equals(charSequence) || table.getTable_number().equals(charSequence)) {
                        list.add(table);
                    }
                }
            }
            result.values = list; //将得到的集合保存到FilterResults的value变量中
            result.count = list.size();//将集合的大小保存到FilterResults的count变量中
            return result;
        }

        //在publishResults方法中告诉适配器更新界面
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count > 0) {
                setNewData((List<Table>) filterResults.values);
            } else {
                setNewData(new ArrayList<Table>());
                Toast.makeText(mContext, "没找到桌台", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
