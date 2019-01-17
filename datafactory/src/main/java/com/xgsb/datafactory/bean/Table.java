package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.zx.api.api.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Table
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-12-12 14:35
 */
public class Table implements Parcelable, Cloneable {
    private String table_id;
    private String table_number;
    private String region_id;
    private String region_name;
    private String site_num;
    private String lowest_expense;
    private String spell;
    private String use_tag;
    private String lock_tag;
    private String lock_time;
    private String locker_name;
    private List<Billbean> bill;
    private String local_status;
    private boolean isKx;
    private boolean mAssembleTable;
    private Billbean mBillbean;

    public void setBill(List<Billbean> bill) {
        this.bill = bill;
    }

    public List<Billbean> getBill() {
        return bill;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getSite_num() {
        return site_num;
    }

    public void setSite_num(String site_num) {
        this.site_num = site_num;
    }

    public String getLowest_expense() {
        return lowest_expense;
    }

    public void setLowest_expense(String lowest_expense) {
        this.lowest_expense = lowest_expense;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getUse_tag() {
        return use_tag;
    }

    public void setUse_tag(String use_tag) {
        this.use_tag = use_tag;
    }

    public String getLock_tag() {
        return lock_tag;
    }

    public void setLock_tag(String lock_tag) {
        this.lock_tag = lock_tag;
    }

    public String getLock_time() {
        return lock_time;
    }

    public void setLock_time(String lock_time) {
        this.lock_time = lock_time;
    }

    public String getLocker_name() {
        return locker_name;
    }

    public void setLocker_name(String locker_name) {
        this.locker_name = locker_name;
    }

    public Table() {
    }

    public String getLocal_status() {
        return local_status;
    }

    public void setLocal_status(String local_status) {
        this.local_status = local_status;
    }

    /**
     * 是否为空闲台
     *
     * @return
     */
    public boolean isKx() {
        isKx = ("0".equals(getUse_tag()) && "0".equals(lock_tag));
        return isKx;
    }

    /**
     * 是否锁台
     *
     * @return
     */
    public boolean isLocked() {
        return "1".equals(lock_tag);
    }

    public boolean isYd() {
        List<Billbean> billbeans = hasBillTag("3");
        return billbeans == null ? false : (billbeans.size() != 0 && getBill().size() == 1);
    }

    /**
     * @param level
     * @return
     */
    public List<Billbean> hasBillTag(String level) {
        List<Billbean> list = new ArrayList<>();
        if (getBill() == null || getBill().size() == 0) {
            return null;
        } else {
            for (int i = 0; i < getBill().size(); i++) {
                Billbean billbean = getBill().get(i);
                if (level.equals(billbean.getBill_tag())) {
                    list.add(billbean);
                }
            }
        }
        return list;
    }

    public boolean isAssembleTable() {
        List<Billbean> pnum = hasBillTag("2");
        mAssembleTable = (pnum != null && pnum.size() != 0);
        return mAssembleTable;
    }

    public void setAssembleTable(boolean assembleTable) {
        this.mAssembleTable = assembleTable;
    }

    public void setCurrentBillbean(Billbean billbean) {
        this.mBillbean = billbean;
    }

    public Billbean getBillbean() {
        return mBillbean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.table_id);
        dest.writeString(this.table_number);
        dest.writeString(this.region_id);
        dest.writeString(this.region_name);
        dest.writeString(this.site_num);
        dest.writeString(this.lowest_expense);
        dest.writeString(this.spell);
        dest.writeString(this.use_tag);
        dest.writeString(this.lock_tag);
        dest.writeString(this.lock_time);
        dest.writeString(this.locker_name);
        dest.writeTypedList(this.bill);
        dest.writeString(this.local_status);
        dest.writeByte(this.isKx ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mAssembleTable ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.mBillbean, flags);
    }

    protected Table(Parcel in) {
        this.table_id = in.readString();
        this.table_number = in.readString();
        this.region_id = in.readString();
        this.region_name = in.readString();
        this.site_num = in.readString();
        this.lowest_expense = in.readString();
        this.spell = in.readString();
        this.use_tag = in.readString();
        this.lock_tag = in.readString();
        this.lock_time = in.readString();
        this.locker_name = in.readString();
        this.bill = in.createTypedArrayList(Billbean.CREATOR);
        this.local_status = in.readString();
        this.isKx = in.readByte() != 0;
        this.mAssembleTable = in.readByte() != 0;
        this.mBillbean = in.readParcelable(Billbean.class.getClassLoader());
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel source) {
            return new Table(source);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };
    /**
     * 按优先级 获取当前应该暂时的 单号
     *
     * @param item
     * @param level
     * @return
     */
    /**
     * 按优先级 获取当前应该展示的 单号
     *
     * @param level
     * @return
     */
    public Billbean getCurrentBill(String level) {
        int lev = Integer.valueOf(level);
        if (getBill() == null || getBill().size() == 0) {
            return null;
        }
        for (int i = 0; i < getBill().size(); i++) {
            Billbean billbean = getBill().get(i);
            if (level.equals(billbean.getBill_tag())) {
                if ("1".equals(billbean.getBill_tag()) && "0".equals(getUse_tag())) {
                    //当前台 为主台 且 未被使用  迭代 找下一个台
                    return getCurrentBill((lev + 1) + "");
                }
                return billbean;
            }
        }
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Billbean getOrherBill() {
        for (Billbean billbean : getBill()) {
            if (!"1".equals(billbean.getBill_tag())) {
                return billbean;
            }
        }
        return null;
    }

    public Billbean getMainBill() {
        for (Billbean billbean : getBill()) {
            if ("1".equals(billbean.getBill_tag())) {
                return billbean;
            }
        }
        return null;
    }
}
