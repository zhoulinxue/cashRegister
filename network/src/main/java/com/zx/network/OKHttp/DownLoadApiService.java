package com.zx.network.OKHttp;

import com.squareup.okhttp.ResponseBody;

import retrofit.http.GET;
import retrofit.http.Streaming;
import retrofit.http.Url;
import rx.Observable;

/**
 * Name: DownLoadApiService
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 17:00
 */
public interface DownLoadApiService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
