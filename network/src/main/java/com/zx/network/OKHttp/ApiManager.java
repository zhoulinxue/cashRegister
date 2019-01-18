package com.zx.network.OKHttp;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.xgsb.datafactory.JSONManager;
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
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.api.api.utils.AppLog;
import com.zx.network.NetBean;
import com.zx.network.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Name: ApiManager
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:30
 */
public class ApiManager {
    private static ApiManager mApiManager;
    private static ApiServer mApiService;
    private static Context mContext;
    private static long NET_TIMEOUT = 15;
    private static long NET_WRITE_TIME_OUT = 10;
    private static long NET_READ_TIMEOUT = 30;

    public ApiManager() {
    }

    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public static ApiServer init(Context context, String baseURL) {
        mContext = context;
        if (mApiService == null) {
            /**日志拦截器*/
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            /**设置超时和拦截器*/
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(NET_TIMEOUT, TimeUnit.SECONDS);
            client.setWriteTimeout(NET_WRITE_TIME_OUT, TimeUnit.SECONDS);
            client.setReadTimeout(NET_READ_TIMEOUT, TimeUnit.SECONDS);
            try {
                client.interceptors().add(new CommonInterceptor());
            } catch (Exception e) {
                e.getMessage();
            }
            client.interceptors().add(logging);
            /**创建Retrofit实例*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            mApiService = retrofit.create(ApiServer.class);
        }
        return mApiService;
    }

    /**
     * @param params
     * @return
     */
    private Map<String, String> genrateMap(String... params) {
        Map<String, String> map = new HashMap<>();
        if (params.length >= 2) {
            if (params.length % 2 == 0) {
                for (int i = 0; i < params.length; i++) {
                    if (i % 2 == 0) {
                        map.put(params[i], params[i + 1]);
                    }
                }
            } else {
                AppLog.print("ApiManager" + "param ....key...value  error");
            }
        }
        return map;
    }

    /**
     * 登录
     *
     * @param testCallback
     * @param user
     * @return
     */
    public NetRequest cashLogin(NetRequestCallBack<User> testCallback, User user) {
        Observable<NetBean<User>> observable = mApiService.login(genrateMap(Param.Keys.USER_NUM, user.getUserName(), Param.Keys.USER_PSW, user.getPsw()));
        return new OkHttpRequest<User>(observable, testCallback);
    }

    /**
     * 会员列表
     *
     * @param params
     * @param mCashLoginCallback
     * @return
     */
    public NetRequest getMemberList(String[] params, NetRequestCallBack<ListData<Member>> mCashLoginCallback) {
        Observable<NetBean<ListData<Member>>> observable = mApiService.getMemberList(genrateMap(params));
        return new OkHttpRequest<ListData<Member>>(observable, mCashLoginCallback);
    }

    /**
     * 新增会员及会员总数
     *
     * @param params
     * @param countCallback
     * @return
     */
    public NetRequest getCountData(String[] params, NetRequestCallBack<Countbean> countCallback) {
        Observable<NetBean<Countbean>> observable = mApiService.getCountData(genrateMap(params));
        return new OkHttpRequest<Countbean>(observable, countCallback);
    }

    /**
     * 获取充值列表
     *
     * @param params
     * @param reChargeCallback
     * @return
     */
    public NetRequest getReChargeList(String[] params, NetRequestCallBack<ReChargeListData> reChargeCallback) {
        Observable<NetBean<ReChargeListData>> observable = mApiService.getReChargeList(genrateMap(params));
        return new OkHttpRequest<ReChargeListData>(observable, reChargeCallback);
    }

    /**
     * 回去消费 明细
     *
     * @param params
     * @param reChargeCallback
     * @return
     */

    public NetRequest getConsumeList(String[] params, NetRequestCallBack<ConsumeListData> reChargeCallback) {
        Observable<NetBean<ConsumeListData>> observable = mApiService.getConsumeList(genrateMap(params));
        return new OkHttpRequest<ConsumeListData>(observable, reChargeCallback);
    }

    public NetRequest getMemberDetail(String[] params, NetRequestCallBack<Member> cemberCallback) {
        Observable<NetBean<Member>> observable = mApiService.getMemberDetail(genrateMap(params));
        return new OkHttpRequest<Member>(observable, cemberCallback);
    }

    public NetRequest getCardInfo(String[] params, NetRequestCallBack<List<Cardbean>> cardCallback) {
        Observable<NetBean<List<Cardbean>>> observable = mApiService.getCardInfo(genrateMap(params));
        return new OkHttpRequest<List<Cardbean>>(observable, cardCallback);
    }

    public NetRequest addCardEntityNum(String[] params, NetRequestCallBack<String> cardCallback) {
        Observable<NetBean<String>> observable = mApiService.addCardNum(genrateMap(params));
        return new OkHttpRequest<String>(observable, cardCallback);
    }

    public NetRequest deleteCardEntityNum(String[] params, NetRequestCallBack<String> deleteCallback) {
        Observable<NetBean<String>> observable = mApiService.deleteCardNum(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteCallback);
    }

    public NetRequest addMember(String[] params, NetRequestCallBack<String> addmemberCallback) {
        Observable<NetBean<String>> observable = mApiService.addMember(genrateMap(params));
        return new OkHttpRequest<String>(observable, addmemberCallback);
    }

    public NetRequest getMemberLevelList(String[] params, NetRequestCallBack<ListData<MemberLevel>> levelsCallback) {
        Observable<NetBean<ListData<MemberLevel>>> observable = mApiService.getMemberLevels(genrateMap(params));
        return new OkHttpRequest<ListData<MemberLevel>>(observable, levelsCallback);
    }

    public NetRequest getReChargeGive(String[] params, NetRequestCallBack<ListData<MemberReChargeGive>> reChargeGivesCallback) {
        Observable<NetBean<ListData<MemberReChargeGive>>> observable = mApiService.getRechargeGives(genrateMap(params));
        return new OkHttpRequest<ListData<MemberReChargeGive>>(observable, reChargeGivesCallback);
    }

    public NetRequest getCardMsg(String[] params, NetRequestCallBack<Cardbean> cardCallback) {
        Observable<NetBean<Cardbean>> observable = mApiService.getCardMsg(genrateMap(params));
        return new OkHttpRequest<Cardbean>(observable, cardCallback);
    }

    public NetRequest exChanageCard(String[] params, NetRequestCallBack<String> chanageCardCallback) {
        Observable<NetBean<String>> observable = mApiService.changeCard(genrateMap(params));
        return new OkHttpRequest<String>(observable, chanageCardCallback);
    }

    public NetRequest refund(String[] params, NetRequestCallBack<String> refundCardCallback) {
        Observable<NetBean<String>> observable = mApiService.refund(genrateMap(params));
        return new OkHttpRequest<String>(observable, refundCardCallback);
    }

    public NetRequest deleteMember(String[] params, NetRequestCallBack<String> deleteCardCallback) {
        Observable<NetBean<String>> observable = mApiService.delete(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteCardCallback);
    }

    public NetRequest frozen(String[] params, NetRequestCallBack<String> deleteCardCallback) {
        Observable<NetBean<String>> observable = mApiService.frozen(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteCardCallback);
    }

    public NetRequest recharge(String[] params, NetRequestCallBack<String> reChargeCallback) {
        Observable<NetBean<String>> observable = mApiService.recharge(genrateMap(params));
        return new OkHttpRequest<String>(observable, reChargeCallback);
    }

    public NetRequest cancelRecharge(String[] params, NetRequestCallBack<String> cancelCallback) {
        Observable<NetBean<String>> observable = mApiService.cancelRecharge(genrateMap(params));
        return new OkHttpRequest<String>(observable, cancelCallback);
    }

    public NetRequest getGive(String[] params, NetRequestCallBack<Double> giveCallback) {
        Observable<NetBean<Double>> observable = mApiService.getGive(genrateMap(params));
        return new OkHttpRequest<Double>(observable, giveCallback);
    }

    /**
     * 根据卡号、手机号 查询用户信息  模糊查询
     *
     * @param params
     * @param memberCallback
     * @return
     */
    public NetRequest getMemberMsgByCard(String[] params, NetRequestCallBack<Member> memberCallback) {
        Observable<NetBean<Member>> observable = mApiService.getMember(genrateMap(params));
        return new OkHttpRequest<Member>(observable, memberCallback);
    }
    /**
     * 根据卡号、手机号 查询用户信息  精确查询
     *
     * @param params
     * @param memberCallback
     * @return
     */
    public NetRequest getMemberByCard(String[] params, NetRequestCallBack<Member> memberCallback) {
        Observable<NetBean<Member>> observable = mApiService.getMemberDetailByData(genrateMap(params));
        return new OkHttpRequest<Member>(observable, memberCallback);
    }

    /**
     * 获取销售列表
     *
     * @param params
     * @param sellerListCallback
     * @return
     */
    public NetRequest getSellerList(String[] params, NetRequestCallBack<List<Sellerbean>> sellerListCallback) {
        Observable<NetBean<List<Sellerbean>>> observable = mApiService.getSellerList(genrateMap(params));
        return new OkHttpRequest<List<Sellerbean>>(observable, sellerListCallback);
    }

    /**
     * 修改会员信息
     *
     * @param params
     * @param updatemberCallback
     * @return
     */
    public NetRequest updateMember(String[] params, NetRequestCallBack<String> updatemberCallback) {
        Observable<NetBean<String>> observable = mApiService.updateMember(genrateMap(params));
        return new OkHttpRequest<String>(observable, updatemberCallback);
    }

    /**
     * 获取桌台列表
     *
     * @param params
     * @param tableListCallback
     * @return
     */
    public NetRequest getTableList(String[] params, NetRequestCallBack<List<Table>> tableListCallback) {
        Observable<NetBean<List<Table>>> observable = mApiService.getTableList(genrateMap(params));
        return new OkHttpRequest<List<Table>>(observable, tableListCallback);
    }

    /**
     * 获取桌台区域列表
     *
     * @param params
     * @param areaListCallback
     * @return
     */
    public NetRequest getTableAreaList(String[] params, NetRequestCallBack<List<TableArea>> areaListCallback) {
        Observable<NetBean<List<TableArea>>> observable = mApiService.getTableAreaList(genrateMap(params));
        return new OkHttpRequest<List<TableArea>>(observable, areaListCallback);
    }

    /**
     * 获取已点 菜单列表
     *
     * @param params
     * @param menuDishesListCallback
     * @return
     */
    public NetRequest getOrderDishesList(String[] params, NetRequestCallBack<SettalOrderResultbean> menuDishesListCallback) {
        Observable<NetBean<SettalOrderResultbean>> observable = mApiService.getOrderDishes(genrateMap(params));
        return new OkHttpRequest<SettalOrderResultbean>(observable, menuDishesListCallback);
    }

    /**
     * 获取菜单列表
     *
     * @param params
     * @param menuDishesListCallback
     * @return
     */
    public NetRequest getDishesList(String[] params, NetRequestCallBack<List<Dishesbean>> menuDishesListCallback) {
        Observable<NetBean<List<Dishesbean>>> observable = mApiService.getDishesList(genrateMap(params));
        return new OkHttpRequest<List<Dishesbean>>(observable, menuDishesListCallback);
    }

    /**
     * 菜品分类列表
     *
     * @param params
     * @param typeListCallBack
     * @return
     */
    public NetRequest getDishesTypeList(String[] params, NetRequestCallBack<List<DishesTypebean>> typeListCallBack) {
        Observable<NetBean<List<DishesTypebean>>> observable = mApiService.getDishesTypeList(genrateMap(params));
        return new OkHttpRequest<List<DishesTypebean>>(observable, typeListCallBack);
    }

    /**
     * 添加菜品
     *
     * @param params
     * @param addDishesCallback
     * @return
     */
    public NetRequest addOrderDishes(String[] params, NetRequestCallBack<String> addDishesCallback) {
        Observable<NetBean<String>> observable = mApiService.addOrderDishes(genrateMap(params));
        return new OkHttpRequest<String>(observable, addDishesCallback);
    }

    /**
     * 获取备注列表
     *
     * @param params
     * @param rmarkCallBack
     * @return
     */
    public NetRequest getRemarkList(String[] params, NetRequestCallBack<List<Remarkbean>> rmarkCallBack) {
        Observable<NetBean<List<Remarkbean>>> observable = mApiService.getRemarkList(genrateMap(params));
        return new OkHttpRequest<List<Remarkbean>>(observable, rmarkCallBack);
    }

    /**
     * 获取 套餐内分组及菜品
     *
     * @param params
     * @param setMealGroupCallback
     * @return
     */
    public NetRequest getSetMealGroup(String[] params, NetRequestCallBack<List<SetMealGroupbean>> setMealGroupCallback) {
        Observable<NetBean<List<SetMealGroupbean>>> observable = mApiService.getSetMealGroups(genrateMap(params));
        return new OkHttpRequest<List<SetMealGroupbean>>(observable, setMealGroupCallback);
    }

    /**
     * 获取套餐内 菜品
     *
     * @param params
     * @param setMealDishesCallback
     * @return
     */
    public NetRequest getSetMealDishes(String[] params, NetRequestCallBack<List<Dishesbean>> setMealDishesCallback) {
        Observable<NetBean<List<Dishesbean>>> observable = mApiService.getSetMealDishesList(genrateMap(params));
        return new OkHttpRequest<List<Dishesbean>>(observable, setMealDishesCallback);
    }

    public NetRequest deleteDishes(String[] params, NetRequestCallBack<String> deleteDishesCallback) {
        Observable<NetBean<String>> observable = mApiService.deleteAllDishes(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteDishesCallback);
    }

    public NetRequest setSettleOrder(SettalOrderbean settleOrder, NetRequestCallBack<String> settleOrderDishesCallback) {
        Observable<NetBean<String>> observable = mApiService.settleOrder(settleOrder);
        return new OkHttpRequest<String>(observable, settleOrderDishesCallback);
    }

    public NetRequest saleOut(String[] params, NetRequestCallBack<Integer> saleoutCallBack) {
        Observable<NetBean<Integer>> observable = mApiService.saleOut(genrateMap(params));
        return new OkHttpRequest<Integer>(observable, saleoutCallBack);
    }

    /**
     * 修改用餐人数
     *
     * @param params
     * @param updateNumberCallback
     * @return
     */
    public NetRequest updateGuestNum(String[] params, NetRequestCallBack<String> updateNumberCallback) {
        Observable<NetBean<String>> observable = mApiService.updateGuestNumber(genrateMap(params));
        return new OkHttpRequest<String>(observable, updateNumberCallback);
    }

    public NetRequest getSaleOutList(String[] params, NetRequestCallBack<List<SaleOutbean>> saleOutListCallBack) {
        Observable<NetBean<List<SaleOutbean>>> observable = mApiService.getSaleOutList(genrateMap(params));
        return new OkHttpRequest<List<SaleOutbean>>(observable, saleOutListCallBack);
    }

    /**
     * 更新沽清
     *
     * @param params
     * @param updateSaleOutCallBack
     * @return
     */
    public NetRequest updateSaleOutList(String[] params, NetRequestCallBack<String> updateSaleOutCallBack) {
        Observable<NetBean<String>> observable = mApiService.updateSaleOut(genrateMap(params));
        return new OkHttpRequest<String>(observable, updateSaleOutCallBack);
    }

    /**
     * 删除沽清
     *
     * @param params
     * @param deleteCallBack
     * @return
     */
    public NetRequest deleteSaleOut(String[] params, NetRequestCallBack<String> deleteCallBack) {
        Observable<NetBean<String>> observable = mApiService.deleteSaleOut(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteCallBack);
    }

    /**
     * 清空沽清
     *
     * @param params
     * @param deleteAllCallBack
     * @return
     */
    public NetRequest deleteAll(String[] params, NetRequestCallBack<String> deleteAllCallBack) {
        Observable<NetBean<String>> observable = mApiService.deleteAllSaleOut(genrateMap(params));
        return new OkHttpRequest<String>(observable, deleteAllCallBack);
    }

    /**
     * 复制菜品
     *
     * @param params
     * @param copyCallback
     * @return
     */
    public NetRequest copyDishes(String[] params, NetRequestCallBack<String> copyCallback) {
        Observable<NetBean<String>> observable = mApiService.copyDishes(genrateMap(params));
        return new OkHttpRequest<String>(observable, copyCallback);
    }

    /**
     * 获取赠送 类型列表
     *
     * @param params
     * @param giveTypeListCallback
     * @return
     */
    public NetRequest getGiveDishesTypeList(String[] params, NetRequestCallBack<List<GiveDishesTypebean>> giveTypeListCallback) {
        Observable<NetBean<List<GiveDishesTypebean>>> observable = mApiService.getGiveTypeList(genrateMap(params));
        return new OkHttpRequest<List<GiveDishesTypebean>>(observable, giveTypeListCallback);
    }

    /**
     * 获取 赠送菜品列表
     *
     * @param params
     * @param giveDishesListCallback
     * @return
     */
    public NetRequest getGiveDishesList(String[] params, NetRequestCallBack<ListData<GiveDishesbean>> giveDishesListCallback) {
        Observable<NetBean<ListData<GiveDishesbean>>> observable = mApiService.getGiveDishesList(genrateMap(params));
        return new OkHttpRequest<ListData<GiveDishesbean>>(observable, giveDishesListCallback);
    }

    /**
     * 获取 赠送原因列表
     *
     * @param params
     * @param reasonListCallback
     * @return
     */
    public NetRequest getReasonList(String[] params, NetRequestCallBack<List<String>> reasonListCallback) {
        Observable<NetBean<List<String>>> observable = mApiService.getReasonList(genrateMap(params));
        return new OkHttpRequest<List<String>>(observable, reasonListCallback);
    }

    /**
     * @param params
     * @param listCallback
     * @return
     */
    public NetRequest getGiveDetailList(String[] params, NetRequestCallBack<ListData<GiveDetailListbean>> listCallback) {
        Observable<NetBean<ListData<GiveDetailListbean>>> observable = mApiService.getGiveDetailList(genrateMap(params));
        return new OkHttpRequest<ListData<GiveDetailListbean>>(observable, listCallback);
    }

    /**
     * @param addGivebean
     * @param reasonListCallback
     * @return
     */
    public NetRequest addGivebean(AddGivebean addGivebean, NetRequestCallBack<String> reasonListCallback) {
        Observable<NetBean<String>> observable = mApiService.addGivebean(addGivebean);
        return new OkHttpRequest<String>(observable, reasonListCallback);
    }

    /**
     * 获取优惠类型
     *
     * @param params
     * @param favorableListCallback
     * @return
     */
    public NetRequest getFavorableList(String[] params, NetRequestCallBack<List<Favorablebean>> favorableListCallback) {
        Observable<NetBean<List<Favorablebean>>> observable = mApiService.getFavorableList(genrateMap(params));
        return new OkHttpRequest<List<Favorablebean>>(observable, favorableListCallback);
    }

    /**
     * 回去支付方式
     *
     * @param params
     * @param paymentTypeListCallback
     * @return
     */

    public NetRequest getPaymentType(String[] params, NetRequestCallBack<List<PayTypebean>> paymentTypeListCallback) {
        Observable<NetBean<List<PayTypebean>>> observable = mApiService.getPaymentType(genrateMap(params));
        return new OkHttpRequest<List<PayTypebean>>(observable, paymentTypeListCallback);
    }

    /**
     * 获取 账单支付情况
     *
     * @param params
     * @param paymentTypeCallback
     * @return
     */
    public NetRequest getPayList(String[] params, NetRequestCallBack<OrderPayStatusbean> paymentTypeCallback) {
        Observable<NetBean<OrderPayStatusbean>> observable = mApiService.getPayList(genrateMap(params));
        return new OkHttpRequest<OrderPayStatusbean>(observable, paymentTypeCallback);
    }

    /**
     * 结账
     * @param addPayment
     * @param payOrderCallback
     * @return
     */
    public NetRequest payOrder(Paymentbean addPayment, NetRequestCallBack<String> payOrderCallback) {
        Observable<NetBean<String>> observable = mApiService.payOrder(addPayment);
        return new OkHttpRequest<String>(observable, payOrderCallback);
    }

    /**
     * 撤单
     * @param chedanbean
     * @param cancelOrderCallback
     * @return
     */

    public NetRequest cancelOrder(Chedanbean chedanbean, NetRequestCallBack<String> cancelOrderCallback) {
        Observable<NetBean<String>> observable = mApiService.cancelOrder(chedanbean);
        return new OkHttpRequest<String>(observable, cancelOrderCallback);
    }

    /**
     *
     * @param params
     * @param cancelResonCallback
     * @return
     */
    public NetRequest cancelOrderList(String[] params, NetRequestCallBack<List<String>> cancelResonCallback) {
        Observable<NetBean<List<String>>> observable = mApiService.cancelOrderList(genrateMap(params));
        return new OkHttpRequest<List<String>>(observable, cancelResonCallback);
    }

    /**
     *
     * @param fanJZbean
     * @param fanjzResonCallback
     * @return
     */
    public NetRequest fanJzOrder(FanJZbean fanJZbean, NetRequestCallBack<String> fanjzResonCallback) {
        Observable<NetBean<String>> observable = mApiService.fanJz(fanJZbean);
        return new OkHttpRequest<String>(observable, fanjzResonCallback);
    }

    /**
     *改价
     * @param params
     * @param rePriceCallback
     * @return
     */
    public NetRequest changePrice(String[] params, NetRequestCallBack<String> rePriceCallback) {
        Observable<NetBean<String>> observable = mApiService.changePrice(genrateMap(params));
        return new OkHttpRequest<String>(observable, rePriceCallback);
    }

    /**
     *  退菜
     * @param params
     * @param returnDisheCallback
     * @return
     */
    public NetRequest returnDishes(String[] params, NetRequestCallBack<String> returnDisheCallback) {
        Observable<NetBean<String>> observable = mApiService.returnDishes(genrateMap(params));
        return new OkHttpRequest<String>(observable, returnDisheCallback);
    }

    /**
     * 打折
     * @param params
     * @param discountPriceCallback
     * @return
     */
    public NetRequest reDiscount(String[] params, NetRequestCallBack<String> discountPriceCallback) {
        Observable<NetBean<String>> observable = mApiService.rediscount(genrateMap(params));
        return new OkHttpRequest<String>(observable, discountPriceCallback);
    }

    /**
     *  获取 会员消费详情
     * @param params
     * @param moneyListCallback
     * @return
     */
    public NetRequest getMemberMoneyDetail(String[] params, NetRequestCallBack<List<MemberMoney>> moneyListCallback) {
        Observable<NetBean<List<MemberMoney>>> observable = mApiService.memberMoneyDetail(genrateMap(params));
        return new OkHttpRequest<List<MemberMoney>>(observable, moneyListCallback);
    }
}
