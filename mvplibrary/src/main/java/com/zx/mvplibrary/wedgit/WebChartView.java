package com.zx.mvplibrary.wedgit;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xgsb.datafactory.JSONManager;
import com.zx.api.api.utils.AppLog;
import com.zx.mvplibrary.BaseCustomView;
import com.zx.mvplibrary.R;
import com.zx.mvplibrary.R2;
import com.zx.mvplibrary.web.WebOperate;
import com.zx.mvplibrary.web.XgDAppBridge;
import com.zx.mvplibrary.web.onOperateLisenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import io.starteos.dappsdk.DAppBridge;
import io.starteos.dappsdk.Request;
import io.starteos.dappsdk.Response;

/**
 * Name: WebChartView
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 10:18
 */
public class WebChartView extends BaseCustomView implements WebOperate {
    @BindView(R2.id.xgsb_webview)
    WebView mWebView;
    private DAppBridge dAppBridge;
    private int width = 0;
    private int hight = 0;


    public WebChartView(Context context, ViewGroup rootGroup) {
        super(context, rootGroup);
    }


    public WebChartView(Context context, ViewGroup rootGroup, onOperateLisenter lisenter, Handler handler) {
        this(context, rootGroup);
        dAppBridge = new XgDAppBridge(getContext(), mWebView, lisenter, handler);
    }

    public WebChartView(Context context) {
        super(context);
    }

    @Override
    protected void initView(Context context, View rootView) {
        mWebView.setWebContentsDebuggingEnabled(true);
        mWebView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mWebView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width = mWebView.getMeasuredWidth();
                hight = mWebView.getMeasuredHeight();
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.web_chart_layout;
    }

    @Override
    public void reload() {
        mWebView.reload();
    }

    @Override
    public void loadUrl(String url) {
        loadUrl(url, new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    AppLog.print("加载完毕");
                }
            }
        });
    }

    public void loadUrl(String url, WebChromeClient client) {
        mWebView.setWebChromeClient(client);
        mWebView.loadUrl(url);
    }

    public void loadLocalDefaultUrl(WebChromeClient client) {
        loadUrl("file:///android_asset/index.html", client);
    }

    @Override
    public void getDataFormWeb(String action, String methodName) {
        if (!TextUtils.isEmpty(action)) {
            mWebView.evaluateJavascript("javascript:" + methodName + "(" + JSONManager.getInstance().toJson(action) + ")", null);
        } else if (TextUtils.isEmpty(action) && !TextUtils.isEmpty(methodName)) {
            mWebView.evaluateJavascript("javascript:" + methodName + "()", null);
        }
    }

    public void refresh(String action) {
        getDataFormWeb(action, "reData");
    }

    public void loadDefaultUrl(WebChromeClient client) {
        loadUrl("http://www.kx910.com/webView/index.html#/", client);
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }

    public void callback(Request request, String json) {
        dAppBridge.callback(request, getResone(json));
    }

    public void setLisenter(onOperateLisenter mLisenter) {
        ((XgDAppBridge) dAppBridge).setLisenter(mLisenter);
    }

    private Response getResone(String json) {
        Response response = null;
        try {
            if (json.startsWith("[")) {
                response = new Response(Response.CODE_SUCCESS, "success", new JSONArray(json));
            } else {
                response = new Response(Response.CODE_SUCCESS, "success", new JSONObject(json));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void backTolast() {
        mWebView.goBack();
    }
}
