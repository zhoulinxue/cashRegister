package com.zx.network.OKHttp;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zx.network.Fileutil;

import java.io.File;
import java.io.IOException;

/**
 * Name: DownloadProgressInterceptor
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 16:39
 */
public class DownloadProgressInterceptor implements Interceptor {
    private long startpos;
    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener , File file){
        this.listener = listener;
        startpos = Fileutil.readFile(file);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        //断点续传
        Request request = chain.request().newBuilder().addHeader("Range" , "bytes=" + startpos + "-").build();
        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}
