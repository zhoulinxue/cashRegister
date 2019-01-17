package com.zx.network.OKHttp;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.xgsb.datafactory.FastJsonParser;
import com.xgsb.datafactory.bean.ListData;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.api.api.utils.AppLog;
import com.zx.network.LoacalError;
import com.zx.network.NetBean;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit.HttpException;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Name: OkHttpRequest
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-14 11:28
 */
public class OkHttpRequest<T> implements NetRequest {
    private final String TAG = OkHttpRequest.class.getSimpleName();
    private boolean isDestoryed = false;
    private Subscription mSubscription;
    private Observable mObservable;
    private NetRequestCallBack<T> mCallback;
    private Type mTypeOfT;

    public OkHttpRequest(Observable mObservable, NetRequestCallBack<T> mCallback) {
        this.mObservable = mObservable;
        this.mCallback = mCallback;
        start();
    }

    @Override
    public boolean isDestoryed() {
        return isDestoryed;
    }

    @Override
    public void cancel() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        isDestoryed = true;
        mCallback = null;
    }

    private void start() {
        mSubscription = mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserverCallBack);

    }

    Observer<NetBean<T>> mObserverCallBack = new Observer<NetBean<T>>() {
        @Override
        public void onCompleted() {
            AppLog.print(".........onCompleted.............");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            LoacalError error = null;
            if (e instanceof HttpException) {     //   HTTP错误
                error = LoacalError.BAD_NETWORK;
            } else if (e instanceof ConnectException
                    || e instanceof UnknownHostException) {   //   连接错误
                error = LoacalError.CONNECT_ERROR;
            } else if (e instanceof InterruptedIOException) {   //  连接超时
                error = LoacalError.CONNECT_TIMEOUT;
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof JsonSyntaxException
                    || e instanceof NumberFormatException
                    || e instanceof ParseException) {   //  解析错误
                error = LoacalError.PARSE_ERROR;
            } else {
                error = LoacalError.UNKNOWN_LOCAL_ERROR;
            }
            if (mCallback != null) {
                mCallback.onError(error.getErrorCode(), error.getErrorMsg());
            }
        }

        @Override
        public void onNext(NetBean<T> tNetBean) {
            if (mCallback != null) {
                if (tNetBean.getCode() == 200) {
                    mCallback.onSuccess(tNetBean.getData());
                } else {
                    mCallback.onError(tNetBean.getCode(), tNetBean.getError().getMsg());
                }
            }
        }
    };


}
