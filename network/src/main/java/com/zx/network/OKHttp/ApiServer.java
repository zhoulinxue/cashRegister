package com.zx.network.OKHttp;

import com.xgsb.datafactory.bean.AddGivebean;
import com.xgsb.datafactory.bean.Cardbean;
import com.xgsb.datafactory.bean.Chedanbean;
import com.xgsb.datafactory.bean.ConsumeListData;
import com.xgsb.datafactory.bean.Countbean;
import com.xgsb.datafactory.bean.DishesTypebean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.FanJZbean;
import com.xgsb.datafactory.bean.Favorablebean;
import com.xgsb.datafactory.bean.GiveDetailListbean;
import com.xgsb.datafactory.bean.GiveDishesTypebean;
import com.xgsb.datafactory.bean.GiveDishesbean;
import com.xgsb.datafactory.bean.ListData;
import com.xgsb.datafactory.bean.Member;
import com.xgsb.datafactory.bean.MemberLevel;
import com.xgsb.datafactory.bean.MemberMoney;
import com.xgsb.datafactory.bean.MemberReChargeGive;
import com.xgsb.datafactory.bean.OrderPayStatusbean;
import com.xgsb.datafactory.bean.OrderPerformancebean;
import com.xgsb.datafactory.bean.PayTypebean;
import com.xgsb.datafactory.bean.Paybean;
import com.xgsb.datafactory.bean.Paymentbean;
import com.xgsb.datafactory.bean.ReChargeListData;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SaleOutbean;
import com.xgsb.datafactory.bean.Sellerbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.bean.TableArea;
import com.xgsb.datafactory.bean.User;
import com.zx.network.NetBean;

import java.util.List;
import java.util.Map;

import retrofit.http.Body;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Name: ApiService
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:35
 */
public interface ApiServer {
    //点单机登陆
    public static final String MAIN_LOGIN_URL = "/reserve/reserve/login";
    //用户列表
    public static final String MEMBER_LIST_URL = "/reserve/reserve/MemberIndex";
    //用户统计
    public static final String MEMBER_MAIN_COUNT = "/reserve/reserve/MemberCount";
    //充值明细
    public static final String MEMBER_RECHARGE_LIST = "/reserve/reserve/MemberStorageIndex";
    //消费明细
    public static final String MEMBER_CONSUME_LIST = "/reserve/reserve/MemberExpensesIndex";
    //会员详情
    public static final String MEMBER_DETAIL = "/reserve/reserve/MemberDetails";
    //批量制卡 获取卡信息
    public static final String CARD_NUM = "/reserve/reserve/MemberEntityGetAll";
    //获取单卡信息
    public static final String CARD_NUM_SIGN = "/reserve/reserve/MemberEntityGet";
    //绑定卡
    public static final String ADD_CARD_NUM = "/reserve/reserve/MemberEntityCardDetails";
    //删除卡
    public static final String DELETE_CARD_NUM = "/reserve/reserve/MemberEntityCardDeles";
    //新增用户
    public static final String ADD_MEMBER = "/reserve/reserve/MemberAdd";
    //用户等级列表
    public static final String MEMBER_LEVELS = "/reserve/reserve/MemberGradeIndex";
    //赠送
    public static final String MEMBER_GIVES = "/reserve/reserve/MemberValueGiveIndex";
    //换卡
    public static final String CHANAGE_CARD = "/reserve/reserve/MemberTieUp";
    //退款
    public static final String REFUND = "/reserve/reserve/MemberRefund";
    //删除 会员
    public static final String DELETE_MEMBER = "/reserve/reserve/MemberDeles";
    //冻结
    public static final String FROEN_MEMBER = "/reserve/reserve/MemberFrozen";
    //储值明细
    public static final String RECHARGE = "/reserve/reserve/MemberStorage";
    //撤销 充值明细
    public static final String CANCELATION_RECHARGE = "/reserve/reserve/MemberStorageRevoke";
    //获取赠送 金额
    public static final String GIVE_MONEY = "/reserve/reserve/MoneyGive";
    // 根据卡号 手机号查询 用户信息
    public static final String GET_MEMBER_INFO = "/reserve/reserve/MemberDateinfo";
    //获取销售信息
    public static final String SELLER_LIST = "/reserve/reserve/MemberFramePostSale";
    // 更新会员信息
    public static final String UPDATE_MEMBER = "/reserve/reserve/MemberDetails";
    //获取桌台列表
    public static final String TABLE_LIST = "/frontend/common/tableInfo";
    //获取桌台区域列表
    public static final String TABLE_AREA_LIST = "/frontend/common/region";
    // 获取已点 菜单列表
    public static final String ORDER_DISHES_LIST = "/reserve/reserve/BillDishesItemIndex";
    // 获取菜单列表
    public static final String DISHES_LIST = "/frontend/common/dishes";
    // 菜品种类列表
    public static final String DISHES_TYPE_LIST = "/frontend/common/dishesCategory";
    //添加菜品
    public static final String ADD_DISHES = "/reserve/reserve/BillDishesItemAdd";
    //备注列表
    public static final String REMARK_LIST = "/reserve/reserve/RemarksIndex";
    //套餐内分组列表
    public static final String SETMEAL_GROUP_LIST = "/frontend/common/comboGroupNameAndDishes";
    //套餐内菜品
    public static final String SETMEAL_DISH_LIST = "/frontend/common/comboDishes";
    //删除所有菜品
    public static final String DELETE_ALL_DISHES = "/reserve/reserve/BillDishesItemDelesAll";
    //下单
    public static final String SETTAL_DISHES = "/reserve/reserve/BillDishesAdd";
    //估清
    public static final String SALE_OUT = "/reserve/reserve/DishesGuQingAdd";

    //点单机登录
    @POST(MAIN_LOGIN_URL)
    Observable<NetBean<User>> login(@QueryMap Map<String, String> params);

    @POST(MEMBER_LIST_URL)
    Observable<NetBean<ListData<Member>>> getMemberList(@QueryMap Map<String, String> stringStringMap);

    @POST(MEMBER_MAIN_COUNT)
    Observable<NetBean<Countbean>> getCountData(@QueryMap Map<String, String> stringStringMap);

    @POST(MEMBER_RECHARGE_LIST)
    Observable<NetBean<ReChargeListData>> getReChargeList(@QueryMap Map<String, String> stringStringMap);

    @POST(MEMBER_CONSUME_LIST)
    Observable<NetBean<ConsumeListData>> getConsumeList(@QueryMap Map<String, String> stringStringMap);

    @GET(MEMBER_DETAIL)
    Observable<NetBean<Member>> getMemberDetail(@QueryMap Map<String, String> stringStringMap);

    @POST(CARD_NUM)
    Observable<NetBean<List<Cardbean>>> getCardInfo(@QueryMap Map<String, String> stringStringMap);

    @POST(ADD_CARD_NUM)
    Observable<NetBean<String>> addCardNum(@QueryMap Map<String, String> stringStringMap);

    @POST(DELETE_CARD_NUM)
    Observable<NetBean<String>> deleteCardNum(@QueryMap Map<String, String> stringStringMap);

    @POST(ADD_MEMBER)
    Observable<NetBean<String>> addMember(@QueryMap Map<String, String> stringStringMap);

    @GET(MEMBER_LEVELS)
    Observable<NetBean<ListData<MemberLevel>>> getMemberLevels(@QueryMap Map<String, String> stringStringMap);

    @POST(MEMBER_GIVES)
    Observable<NetBean<ListData<MemberReChargeGive>>> getRechargeGives(@QueryMap Map<String, String> stringStringMap);

    @POST(CARD_NUM_SIGN)
    Observable<NetBean<Cardbean>> getCardMsg(@QueryMap Map<String, String> stringStringMap);

    @POST(CHANAGE_CARD)
    Observable<NetBean<String>> changeCard(@QueryMap Map<String, String> stringStringMap);

    @POST(REFUND)
    Observable<NetBean<String>> refund(@QueryMap Map<String, String> stringStringMap);

    @POST(DELETE_MEMBER)
    Observable<NetBean<String>> delete(@QueryMap Map<String, String> stringStringMap);

    @POST(FROEN_MEMBER)
    Observable<NetBean<String>> frozen(@QueryMap Map<String, String> stringStringMap);

    @POST(RECHARGE)
    Observable<NetBean<String>> recharge(@QueryMap Map<String, String> stringStringMap);

    //撤销充值
    @POST(CANCELATION_RECHARGE)
    Observable<NetBean<String>> cancelRecharge(@QueryMap Map<String, String> stringStringMap);

    //获取赠送金额
    @POST(GIVE_MONEY)
    Observable<NetBean<Double>> getGive(@QueryMap Map<String, String> stringStringMap);

    // 根据卡号 手机号查询 用户信息
    @POST(GET_MEMBER_INFO)
    Observable<NetBean<Member>> getMember(@QueryMap Map<String, String> stringStringMap);

    // 销售列表
    @POST(SELLER_LIST)
    Observable<NetBean<List<Sellerbean>>> getSellerList(@QueryMap Map<String, String> stringStringMap);

    //修改用户信息
    @POST(UPDATE_MEMBER)
    Observable<NetBean<String>> updateMember(@QueryMap Map<String, String> stringStringMap);

    //获取 桌台列表
    @POST(TABLE_LIST)
    Observable<NetBean<List<Table>>> getTableList(@QueryMap Map<String, String> stringStringMap);

    //区域列表
    @POST(TABLE_AREA_LIST)
    Observable<NetBean<List<TableArea>>> getTableAreaList(@QueryMap Map<String, String> stringStringMap);

    // 已点菜单列表
    @POST(ORDER_DISHES_LIST)
    Observable<NetBean<SettalOrderResultbean>> getOrderDishes(@QueryMap Map<String, String> stringStringMap);

    // 菜品列表
    @POST(DISHES_LIST)
    Observable<NetBean<List<Dishesbean>>> getDishesList(@QueryMap Map<String, String> stringStringMap);

    //菜品分类列表
    @POST(DISHES_TYPE_LIST)
    Observable<NetBean<List<DishesTypebean>>> getDishesTypeList(@QueryMap Map<String, String> stringStringMap);

    // 添加菜品
    @POST(ADD_DISHES)
    Observable<NetBean<String>> addOrderDishes(@QueryMap Map<String, String> stringStringMap);

    // 获取备注列表
    @POST(REMARK_LIST)
    Observable<NetBean<List<Remarkbean>>> getRemarkList(@QueryMap Map<String, String> stringStringMap);

    @POST(SETMEAL_GROUP_LIST)
    Observable<NetBean<List<SetMealGroupbean>>> getSetMealGroups(@QueryMap Map<String, String> stringStringMap);

    @POST(SETMEAL_DISH_LIST)
    Observable<NetBean<List<Dishesbean>>> getSetMealDishesList(@QueryMap Map<String, String> stringStringMap);

    @POST(DELETE_ALL_DISHES)
    Observable<NetBean<String>> deleteAllDishes(@QueryMap Map<String, String> stringStringMap);

    @POST(SETTAL_DISHES)
    Observable<NetBean<String>> settleOrder(@Body SettalOrderbean settleOrder);

    @POST(SALE_OUT)
    Observable<NetBean<Integer>> saleOut(@QueryMap Map<String, String> stringStringMap);

    //修改用餐人數
    @POST("/frontend/common/changeGuestQty")
    Observable<NetBean<String>> updateGuestNumber(@QueryMap Map<String, String> stringStringMap);

    //获取沽清列表
    @POST("/reserve/reserve/DishesGuQingIndex")
    Observable<NetBean<List<SaleOutbean>>> getSaleOutList(@QueryMap Map<String, String> stringStringMap);

    /**
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/DishesGuQingDetails")
    Observable<NetBean<String>> updateSaleOut(@QueryMap Map<String, String> stringStringMap);

    /**
     * 取消沽清
     *
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/DishesGuQingDeles")
    Observable<NetBean<String>> deleteSaleOut(@QueryMap Map<String, String> stringStringMap);

    /**
     * 清空沽清
     *
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/DishesGuQingSwitch")
    Observable<NetBean<String>> deleteAllSaleOut(@QueryMap Map<String, String> stringStringMap);

    /**
     * 复制菜品
     *
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/BillDishesItemCopy")
    Observable<NetBean<String>> copyDishes(@QueryMap Map<String, String> stringStringMap);

    @POST("/reserve/reserve/giveList")
    Observable<NetBean<List<GiveDishesTypebean>>> getGiveTypeList(@QueryMap Map<String, String> stringStringMap);

    /**
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/giveMenuList")
    Observable<NetBean<ListData<GiveDishesbean>>> getGiveDishesList(@QueryMap Map<String, String> stringStringMap);

    /**
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/givingCause")
    Observable<NetBean<List<String>>> getReasonList(@QueryMap Map<String, String> stringStringMap);

    /**
     * @param stringStringMap
     * @return
     */
    @POST("/reserve/reserve/givingRecordsList")
    Observable<NetBean<ListData<GiveDetailListbean>>> getGiveDetailList(@QueryMap Map<String, String> stringStringMap);

    /**
     * @return
     */
    @POST("/reserve/reserve/givingAdd")
    Observable<NetBean<String>> addGivebean(@Body AddGivebean addGivebean);

    @FormUrlEncoded
    @POST("/frontend/cash/favorableType")
    Observable<NetBean<List<Favorablebean>>> getFavorableList(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/system/single/paymentList")
    Observable<NetBean<List<PayTypebean>>> getPaymentType(@FieldMap Map<String, String> stringStringMap);

    @POST("/cashier/invoicing/tableListCheckout")
    Observable<NetBean<OrderPayStatusbean>> getPayList(@QueryMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/reserve/reserve/MemberDateinfos")
    Observable<NetBean<Member>> getMemberDetailByData(@FieldMap Map<String, String> stringStringMap);

    @POST("/cashier/invoicing/checkOut")
    Observable<NetBean<String>> payOrder(@Body Paymentbean addPayment);

    @POST("/cashier/invoicing/revoke")
    Observable<NetBean<String>> cancelOrder(@Body Chedanbean chedanbean);

    @POST("/cashier/invoicing/revokeReason")
    Observable<NetBean<List<String>>> cancelOrderList(@QueryMap Map<String, String> stringStringMap);

    @POST("/cashier/invoicing/overCheckOut")
    Observable<NetBean<String>> fanJz(@Body FanJZbean fanJZbean);

    @FormUrlEncoded
    @POST("/reserve/reserve/BillDishesItemDetails")
    Observable<NetBean<String>> changePrice(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/reserve/reserve/refundBilldishes")
    Observable<NetBean<String>> returnDishes(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/reserve/reserve/BillDishesItemDetails")
    Observable<NetBean<String>> rediscount(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/reserve/reserve/MemberMoneyInfoDetails")
    Observable<NetBean<ListData<MemberMoney>>> memberMoneyDetail(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("/reserve/reserve/orderClerkAchievement")
    Observable<NetBean<ListData<OrderPerformancebean>>> getOrderPermance(@FieldMap Map<String, String> stringStringMap);
}
