package com.zx.network.OKHttp;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.zx.network.Fileutil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Name: DownLoadManager
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 16:50
 */
public class DownLoadManager {
    private static DownLoadManager mDownLoadManager;
    private DownLoadApiService mDownloadRetrofitApiService;
    private static long NET_TIMEOUT = 15;
    private static long NET_WRITE_TIME_OUT = 10;
    private static long NET_READ_TIMEOUT = 30;
    private String BaseDownLoadUrl="http://clips.vorwaerts-gmbh.de";

    public DownLoadManager() {
    }

    public static DownLoadManager getInstance() {
        if (mDownLoadManager == null) {
            mDownLoadManager = new DownLoadManager();
        }
        return mDownLoadManager;
    }
    public DownLoadApiService initDownloadService(String url , DownloadProgressListener listener , File file){
        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener , file);
        /**日志拦截器*/
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**设置超时和拦截器*/
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(NET_TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(NET_WRITE_TIME_OUT, TimeUnit.SECONDS);
        client.setReadTimeout(NET_READ_TIMEOUT, TimeUnit.SECONDS);
        try {
            client.interceptors().add(interceptor);
        } catch (Exception e) {
            e.getMessage();
        }
        client.interceptors().add(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mDownloadRetrofitApiService = retrofit.create(DownLoadApiService.class);
        return mDownloadRetrofitApiService;
    }

    public void downLoad(String url  , final File file, final DownloadProgressListener listener){
        Observable<ResponseBody> observable= initDownloadService(BaseDownLoadUrl,listener,file).download(url);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {

                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        try {
                            return responseBody.byteStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .observeOn(Schedulers.computation()) // 用于计算任务
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        try {
                            Fileutil.writeFile(inputStream, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InputStream>() {
                    @Override
                    public void onCompleted() {
                        listener.onSuc();
                    }

                    @Override
                    public void onError(Throwable e) {
                            listener.onError();
                    }

                    @Override
                    public void onNext(InputStream inputStream) {

                    }
                });
    }

}
