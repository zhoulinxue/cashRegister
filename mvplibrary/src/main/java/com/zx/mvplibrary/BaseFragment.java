package com.zx.mvplibrary;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.zx.api.api.app.BackHandler;
import com.zx.api.api.app.onChildViewClick;
import com.zx.api.api.utils.AppLog;
import com.zx.api.api.utils.AppUtil;
import com.zx.api.api.utils.SPUtil;
import com.zx.network.Param;

/**
 * Name: BaseFragment
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-24 16:40
 */
public abstract class BaseFragment extends Fragment implements BackHandler {
    protected Handler mHandler;
    protected final int DEFAULT_MINUTES = 1000;
    private View rootView;
    private InputMethodManager mInputMethodManager;
    private Context mContext;
    private boolean isInit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mHandler = new Handler();
        preOnCreatView();
        if (rootView == null) {
            rootView = inflater.inflate(initLayout(), null);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        this.mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    protected abstract void preOnCreatView();

    /**
     * 初始化 界面布局
     *
     * @return 布局 xml
     */
    protected abstract int initLayout();

    /**
     * 初始化组件
     */
    protected abstract void onCreateView(View view, Bundle argment);

    /**
     * 初始化数据
     *
     * @param savedInstanceState 获取已保存的数据
     */
    protected abstract void onInitData(Bundle savedInstanceState);


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateView(view, getArguments());
        if (getUserVisibleHint()) {
            onInitData(savedInstanceState);
        }
        isInit = true;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    /**
     * 单次点击事件
     *
     * @param v
     * @param listener
     */
    public void singleClickOnMinutes(View v, View.OnClickListener listener) {
        AppUtil.registerClick(DEFAULT_MINUTES, v, listener);
    }

    protected void hideInputMethod() {
        if (getActivity().getWindow().getDecorView() != null && this.mInputMethodManager != null) {
            this.mInputMethodManager.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isInit) {
            onRefresh();
        }
    }

    public void onRefresh() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public boolean onBackPress() {
        return false;
    }

    public String getToken() {
        return SPUtil.getInstance().getString(Param.Keys.TOKEN, "default_token");
    }
}
