package com.xgsb.datafactory.bean;

import com.xgsb.datafactory.FastJsonParser;
import com.xgsb.datafactory.JSONManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: WebData
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-26 20:22
 */
public class WebData<T> {
    private String page;
    private String width;
    private String hight;
    private List<Column> tableHead;
    private List<T> tableData;
    private List<Operate> operate;

    public WebData() {
    }

    public static WebData newInstance() {
        return new WebData();
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Column> getTableHead() {
        return tableHead;
    }

    public void setTableHead(List<Column> tableHead) {
        this.tableHead = tableHead;
    }

    public List<T> getTableData() {
        return tableData;
    }

    public void setTableData(List<T> tableData) {
        this.tableData = tableData;
    }

    public List<Operate> getOperate() {
        return operate;
    }

    public void setOperate(List<Operate> operate) {
        this.operate = operate;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getMemberWebData(List<Member> list, int width, int hight) {
        List<Member> more = new ArrayList<>();
        more.addAll(list);
        WebData<Member> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("手机号码", "mobile"));
        columnList.add(new Column("卡号", "coding_card"));
        columnList.add(new Column("姓名", "name"));
        columnList.add(new Column("会员卡类型", "grade_name"));
        columnList.add(new Column("优惠类型", "grade_discount_than_name"));
        columnList.add(new Column("金额(元)", "money"));
        columnList.add(new Column("开卡时间", "open_card_time"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Operate operate = new Operate();
        operate.setName("查看");
        operateList.add(new Operate("充值"));
        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getRechargeListData(List<ReChargebean> list, int width, int hight) {
        List<ReChargebean> more = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            more.addAll(list);
//        }
        more.addAll(list);
        WebData<ReChargebean> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("订单号", "order_money_id"));
        columnList.add(new Column("交易类型", "order_status_name"));
        columnList.add(new Column("手机号", "mobile"));
        columnList.add(new Column("储值时间", "create_time"));
        columnList.add(new Column("储值金额", "stored_value_money"));
        columnList.add(new Column("储值实收", "money"));
        columnList.add(new Column("储值赠送", "money_give"));
        columnList.add(new Column("赠送积分", "integral_give"));
        columnList.add(new Column("支付方式", "payment_method_name"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Operate operate = new Operate();
        operate.setName("撤销");
        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getConsumeListData(List<Consumebean> list, int width, int hight) {
        List<Consumebean> more = new ArrayList<>();
        more.addAll(list);
        WebData<Consumebean> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("订单号", "order_consumption_id"));
        columnList.add(new Column("手机号", "mobile"));
        columnList.add(new Column("结账时间", "create_time"));
        columnList.add(new Column("订单金额", "consumption_money"));
        columnList.add(new Column("应收金额", "receivable_money"));
        columnList.add(new Column("非储值实收", "no_storage_money"));
        columnList.add(new Column("储值实收", "storage_money"));
        columnList.add(new Column("会员优惠", "vip_discount"));
        columnList.add(new Column("其他优惠", "other_discount"));

        columnList.add(new Column("积分抵扣", "integral_deductible"));
        columnList.add(new Column("支付方式", "payment_method_name"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        Operate operate = new Operate();
//        operate.setName("查看");
//        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getSaleList(List<SalePerformancebean> saleList, int width, int hight) {
        List<SalePerformancebean> more = new ArrayList<>();
        more.addAll(saleList);
        WebData<SalePerformancebean> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("营业日期", "time"));
        columnList.add(new Column("订单编号", "orderNum"));
        columnList.add(new Column("客户姓名", "name"));
        columnList.add(new Column("桌台", "tableNum"));
        columnList.add(new Column("金额", "money"));
        columnList.add(new Column("现结", "money_xj"));
        columnList.add(new Column("会员刷卡", "money_card"));
        columnList.add(new Column("其他", "money_others"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Operate operate = new Operate();
        operate.setName("查看详情");
        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getOrderList(List<OrderPerformancebean> orderList, int width, int hight) {
        List<OrderPerformancebean> more = new ArrayList<>();
        more.addAll(orderList);
        WebData<OrderPerformancebean> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("类别", "typeName"));
        columnList.add(new Column("商品编号", "dishes_number"));
        columnList.add(new Column("商品名称", "name"));
        columnList.add(new Column("规格", "unit"));
        columnList.add(new Column("点单数量", "number"));
        columnList.add(new Column("点单金额", "disher_money"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Operate operate = new Operate();
        operate.setName("查看详情");
        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getGiveDetail(List<GiveDetailListbean> giveDetailListbeans, int width, int hight) {
        List<GiveDetailListbean> more = new ArrayList<>();
        more.addAll(giveDetailListbeans);
        WebData<GiveDetailListbean> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("时间", "stocktake_date"));
        columnList.add(new Column("桌台", "station_number"));
        columnList.add(new Column("商品", "dishes_name"));
        columnList.add(new Column("数量", "giving_number"));
        columnList.add(new Column("金额(元)", "giving_price"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        Operate operate = new Operate();
//        operate.setName("查看详情");
//        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }

    public String getMemberMoney(List<MemberMoney> list, int width, int hight) {
        List<MemberMoney> more = new ArrayList<>();
        more.addAll(list);
        WebData<MemberMoney> data = new WebData();
        data.setPage("vipManage");
        data.setWidth(width + "px");
        data.setHight(hight + "");
        List<Column> columnList = new ArrayList<>();
        List<Operate> operateList = new ArrayList<>();
        columnList.add(new Column("订单编号", "order_id"));
        columnList.add(new Column("时间", "create_time"));
        columnList.add(new Column("动作", "action"));
        columnList.add(new Column("金额(元)", "money"));
        columnList.add(new Column("余额(元)", "money_count"));
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        Operate operate = new Operate();
//        operate.setName("查看详情");
//        operateList.add(operate);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        data.setTableHead(columnList);
        data.setOperate(operateList);
        data.setTableData(more);
        return JSONManager.getInstance().toJson(data);
    }
}
