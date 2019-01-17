package com.xgsb.datafactory;

import com.alibaba.fastjson.JSONObject;
import com.zx.api.JsonParser;

import java.io.UnsupportedEncodingException;

/**
 * Name: FastJsonParser
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 10:58
 */
public class FastJsonParser implements JsonParser {
    @Override
    public String ObjectToJsonString(Object object) {
        // TODO Auto-generated method stub
        return JSONObject.toJSONString(object);
    }

    @Override
    public Object parseObject(String json, Class<?> clazz) {
        // TODO Auto-generated method stub
        return JSONObject.parseObject(json, clazz);
    }

    @Override
    public Object parseArray(String json, Class<?> clazz) {
        // TODO Auto-generated method stub
        return JSONObject.parseArray(json, clazz);
    }
}
