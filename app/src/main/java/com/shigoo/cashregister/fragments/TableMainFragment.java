package com.shigoo.cashregister.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.shigoo.cashregister.R;
import com.shigoo.cashregister.customViews.OrderDishMenuListView;
import com.xgsb.datafactory.bean.Billbean;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.EventRouter;
import com.xgsb.datafactory.bean.Numberbean;
import com.xgsb.datafactory.bean.Remarkbean;
import com.xgsb.datafactory.bean.SetMealGroupbean;
import com.xgsb.datafactory.bean.SettalOrderResultbean;
import com.xgsb.datafactory.bean.SettalOrderbean;
import com.xgsb.datafactory.bean.Table;
import com.xgsb.datafactory.enu.DiscountType;
import com.zx.mvplibrary.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableMainFragment extends BaseFragment {
    @BindView(R.id.ordersheet_table_left_container)
    FrameLayout mLeftLayout;
    @BindView(R.id.ordersheet_table_right_container)
    FrameLayout mRightLayout;
    OrderDishMenuListView mDishesView;
    private FragmentNavigator mFragmentNavigator;

    public static TableMainFragment newInstance() {
        TableMainFragment fragment = new TableMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void preOnCreatView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.ordersheet_table_main_fragment;
    }

    @Override
    protected void onCreateView(View view, Bundle argment) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mDishesView = new OrderDishMenuListView(getContext(), mLeftLayout);
        fragments.add(TableFragment.newInstance());
        fragments.add(DishesListFragment.newInstance());
        fragments.add(CopyDishesFragment.newInstance());
        fragments.add(SettalFragment.newInstance());
        mFragmentNavigator = new FragmentNavigator(getChildFragmentManager(), new FragmentNavigatorAdapter() {
            @Override
            public Fragment onCreateFragment(int i) {
                return fragments.get(i);
            }

            @Override
            public String getTag(int i) {
                return fragments.get(i).getClass().getSimpleName();
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        }, R.id.ordersheet_table_right_container);
        mFragmentNavigator.setDefaultPosition(0);
        mFragmentNavigator.showFragment(0);
    }

    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnClick(final EventRouter eventRouter) {
        switch (eventRouter.getAction()) {
            case TABLE:
                final Table table = (Table) eventRouter.getData();
                if ("已开台".equals(table.getLocal_status())) {
                    mFragmentNavigator.showFragment(1);
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDishesView.setTable(table,false);
                    }
                }, 500);
                break;
            case ADD_DISHES:
                Dishesbean add = mDishesView.add((Dishesbean) eventRouter.getData());
                addSuc(add);
                break;
            case DISHES_UPDATE_TOTAL_NUM:
                Fragment updatefragment = mFragmentNavigator.getCurrentFragment();
                if (updatefragment instanceof DishesListFragment) {
                    ((DishesListFragment) updatefragment).updateTotalNum((Dishesbean) eventRouter.getData());
                }
            case DISHES_UPDATE_FORMAT:
                mDishesView.updateFormat((Dishesbean) eventRouter.getData());
                break;
            case DISHES_DETAIL:
                Dishesbean detail = (Dishesbean) eventRouter.getData();
                Fragment detailfragment = mFragmentNavigator.getCurrentFragment();
                if (detailfragment instanceof DishesListFragment) {
                    ((DishesListFragment) detailfragment).dishbeanDetail(detail);
                }
                break;
            case CHILD_DETAIL:
                SetMealGroupbean childdetail = (SetMealGroupbean) eventRouter.getData();
                Fragment cdetailfragment = mFragmentNavigator.getCurrentFragment();
                if (cdetailfragment instanceof DishesListFragment) {
                    ((DishesListFragment) cdetailfragment).childDishbeanDetail(childdetail);
                }
                break;
            case DISHES_LIST:
                mFragmentNavigator.showFragment(1);
                break;
            case DISHES_REMARK:
                Fragment remarkfragment = mFragmentNavigator.getCurrentFragment();
                if (remarkfragment instanceof DishesListFragment) {
                    ((DishesListFragment) remarkfragment).onRemarkClick((Dishesbean) eventRouter.getData());
                }
                break;
            case ORDER_REMARK:
                mDishesView.setOrderRemark((List<Remarkbean>) eventRouter.getData());
                break;
            case DISHES_DELETE:
                Fragment deletefragment = mFragmentNavigator.getCurrentFragment();
                if (deletefragment instanceof DishesListFragment) {
                    ((DishesListFragment) deletefragment).delete((Dishesbean) eventRouter.getData());
                }
                break;
            case CHILD:
                Dishesbean dishesbean = mDishesView.setChild((SetMealGroupbean) eventRouter.getData());
                Fragment childaddfragment = mFragmentNavigator.getCurrentFragment();
                if (childaddfragment instanceof DishesListFragment) {
                    ((DishesListFragment) childaddfragment).addDishbean(dishesbean);
                }
                break;
            case DISHES_UPDATE_REMAR:
                mDishesView.updateRemark((Dishesbean) eventRouter.getData());
                break;
            case CHILD_UPDATE:
                mDishesView.updateChild((SetMealGroupbean) eventRouter.getData());
                break;
            case SET_NUMBER:
                Numberbean numberbean = (Numberbean) eventRouter.getData();
                if (numberbean.getRequstCode() == 10201)
                    mDishesView.setUpdateNumbers(numberbean.getCurrentNum());
                break;
            case BACK_TO_MAIN:
                mFragmentNavigator.showFragment(0);
                mDishesView.setTable(null,false);
                break;
            case SHOW_TABLE_MAIN:
                mFragmentNavigator.showFragment(0);
                break;
            case ADD_SUC:
                addSuc((Dishesbean) eventRouter.getData());
                break;
            case COPY:
                mFragmentNavigator.showFragment(2);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Fragment copyFragment = mFragmentNavigator.getFragment(2);
                        ((CopyDishesFragment) copyFragment).setBillCode(eventRouter.getData() + "");
                    }
                }, 500);

                break;
            case COPY_SUC:
                mDishesView.onOrderDishesListResult((SettalOrderResultbean) eventRouter.getData());
                mFragmentNavigator.showFragment(1);
                break;
            case COPY_CHILD:
                Billbean copyChild = (Billbean) eventRouter.getData();
                Fragment copyChildFragment = mFragmentNavigator.getCurrentFragment();
                if (copyChildFragment instanceof CopyDishesFragment) {
                    ((CopyDishesFragment) copyChildFragment).copyChild(copyChild);
                }
                break;
            case SETTAL_ORDER:
                mFragmentNavigator.showFragment(3);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SettalFragment fragment = (SettalFragment) mFragmentNavigator.getFragment(3);
                        fragment.onSettale((SettalOrderbean) eventRouter.getData());
                    }
                }, 500);
                break;
            case DEMOLITION:
                SettalFragment fragment = (SettalFragment) mFragmentNavigator.getFragment(3);
                fragment.onDemolition(0);
                break;
            case CLEAN_DEMOLITION:
                SettalFragment cfragment = (SettalFragment) mFragmentNavigator.getFragment(3);
                cfragment.onDemolition(1);
                break;
            case DEMOLITION_ADD_DISHES:
                mDishesView.setDemolition((List<Dishesbean>) eventRouter.getData());
                break;
            case CANCEL_DEMOLITION:
                SettalFragment dfragment = (SettalFragment) mFragmentNavigator.getFragment(3);
                dfragment.onDemolition(2);
                mDishesView.cancelDemolition();
                break;
            case DISCOUNT:
                mDishesView.setDiscountType((DiscountType) eventRouter.getData());
                break;
            case PAY_SUC:
                mFragmentNavigator.showFragment(0);
                mDishesView.setTable(null,false);
                break;
            case BILL_REFRESH:
                mDishesView.refresh();
                break;
        }
    }

    private void addSuc(Dishesbean add) {
        Fragment addfragment = mFragmentNavigator.getCurrentFragment();
        if (addfragment instanceof DishesListFragment) {
            ((DishesListFragment) addfragment).addDishbean(add);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    public void fanjiezhang(Table table) {
       mDishesView.fanjiezhang(table);
    }
}
