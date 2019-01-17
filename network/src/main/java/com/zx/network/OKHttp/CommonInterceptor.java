package com.zx.network.OKHttp;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zx.api.api.utils.AppLog;

/**
 * Name: CommonInterceptor
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:45
 */
public class CommonInterceptor implements Interceptor {
    private String netWork = "app_net_suc  :";

    @Override
    public Response intercept(Chain chain) {
        try {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .addHeader("Connection", "keep-alive")
                    .build();
            Response response = chain.proceed(request);
            AppLog.print(netWork + response.code() + " _ " + chain.request().urlString());
            return response;
        } catch (Exception i) {
            i.printStackTrace();
            return null;
        }
    }
}
