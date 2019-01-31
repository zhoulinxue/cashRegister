package com.xgsb.datafactory.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Paymentbean implements Parcelable {
    private String token;
    private String bill_code;
    private String original_price;
    private float receivable;
    private String member_id;
    private String deduction_integral;
    private String deduction_principal;
    private String deduction_give;
    private String cashier_id;
    private String selling_id;
    private String person_in_charge_id;
    private String no_storage_money;
    private List<Integer> dishes;
    private List<AddFavorablebean> sale;
    private List<AddPayment> payments;
    private List<String> voucher_id;
    private String pay_name;
    private String pay_amount;
    private String create_date;
    private String cashier_name;

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBill_code() {
        return bill_code;
    }

    public void setBill_code(String bill_code) {
        this.bill_code = bill_code;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public float getReceivable() {
        return receivable;
    }

    public void setReceivable(float receivable) {
        this.receivable = receivable;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getDeduction_integral() {
        return deduction_integral;
    }

    public void setDeduction_integral(String deduction_integral) {
        this.deduction_integral = deduction_integral;
    }

    public String getDeduction_principal() {
        return deduction_principal;
    }

    public void setDeduction_principal(String deduction_principal) {
        this.deduction_principal = deduction_principal;
    }

    public String getDeduction_give() {
        return deduction_give;
    }

    public void setDeduction_give(String deduction_give) {
        this.deduction_give = deduction_give;
    }

    public String getCashier_id() {
        return cashier_id;
    }

    public void setCashier_id(String cashier_id) {
        this.cashier_id = cashier_id;
    }

    public String getSelling_id() {
        return selling_id;
    }

    public void setSelling_id(String selling_id) {
        this.selling_id = selling_id;
    }

    public String getPerson_in_charge_id() {
        return person_in_charge_id;
    }

    public void setPerson_in_charge_id(String person_in_charge_id) {
        this.person_in_charge_id = person_in_charge_id;
    }

    public String getNo_storage_money() {
        return no_storage_money;
    }

    public void setNo_storage_money(String no_storage_money) {
        this.no_storage_money = no_storage_money;
    }

    public List<Integer> getDishes() {
        return dishes;
    }

    public void setDishes(List<Integer> dishes) {
        this.dishes = dishes;
    }

    public List<AddFavorablebean> getSale() {
        return sale;
    }

    public void setSale(List<AddFavorablebean> sale) {
        this.sale = sale;
    }

    public List<AddPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<AddPayment> payments) {
        this.payments = payments;
    }

    public List<String> getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(List<String> voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Paymentbean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.bill_code);
        dest.writeString(this.original_price);
        dest.writeFloat(this.receivable);
        dest.writeString(this.member_id);
        dest.writeString(this.deduction_integral);
        dest.writeString(this.deduction_principal);
        dest.writeString(this.deduction_give);
        dest.writeString(this.cashier_id);
        dest.writeString(this.selling_id);
        dest.writeString(this.person_in_charge_id);
        dest.writeString(this.no_storage_money);
        dest.writeList(this.dishes);
        dest.writeTypedList(this.sale);
        dest.writeTypedList(this.payments);
        dest.writeStringList(this.voucher_id);
        dest.writeString(this.pay_name);
        dest.writeString(this.pay_amount);
        dest.writeString(this.create_date);
        dest.writeString(this.cashier_name);
    }

    protected Paymentbean(Parcel in) {
        this.token = in.readString();
        this.bill_code = in.readString();
        this.original_price = in.readString();
        this.receivable = in.readFloat();
        this.member_id = in.readString();
        this.deduction_integral = in.readString();
        this.deduction_principal = in.readString();
        this.deduction_give = in.readString();
        this.cashier_id = in.readString();
        this.selling_id = in.readString();
        this.person_in_charge_id = in.readString();
        this.no_storage_money = in.readString();
        this.dishes = new ArrayList<Integer>();
        in.readList(this.dishes, Integer.class.getClassLoader());
        this.sale = in.createTypedArrayList(AddFavorablebean.CREATOR);
        this.payments = in.createTypedArrayList(AddPayment.CREATOR);
        this.voucher_id = in.createStringArrayList();
        this.pay_name = in.readString();
        this.pay_amount = in.readString();
        this.create_date = in.readString();
        this.cashier_name = in.readString();
    }

    public static final Creator<Paymentbean> CREATOR = new Creator<Paymentbean>() {
        @Override
        public Paymentbean createFromParcel(Parcel source) {
            return new Paymentbean(source);
        }

        @Override
        public Paymentbean[] newArray(int size) {
            return new Paymentbean[size];
        }
    };
}
