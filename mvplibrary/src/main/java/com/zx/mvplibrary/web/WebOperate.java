package com.zx.mvplibrary.web;

/**
 * Name: WebOperate
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-28 10:23
 */
public interface WebOperate {

    public void reload();

    public void loadUrl(String url);

    public void getDataFormWeb(String action,String methodName);
}
