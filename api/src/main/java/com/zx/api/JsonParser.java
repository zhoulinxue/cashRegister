package com.zx.api;

import java.io.UnsupportedEncodingException;

public interface JsonParser {

	// 转化为json
	public String ObjectToJsonString(Object object) ;

	//解析 对象
	public Object parseObject(String json, Class<?> clazz) ;

	// 解析集合
	public Object parseArray(String json, Class<?> clazz);
}
