package com.zx.network.OKHttp;

/**
 * Name: DownloadProgressListener
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 16:41
 */
public interface DownloadProgressListener {

    public void onSuc();

    public void  onError();

    public void onProcess(long total,long process);
}
