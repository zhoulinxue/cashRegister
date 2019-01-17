package com.xgsb.datafactory;

import com.zx.api.JsonParser;

import java.io.UnsupportedEncodingException;

/**
 * Name: JSONUtil
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 10:46
 */
public class JSONManager{
    private  static JSONManager mUtil;
    private JsonParser mParser;

    public JSONManager() {
        mParser=new FastJsonParser();
    }

    public static JSONManager getInstance(){
        if(mUtil==null){
            mUtil=new JSONManager();
        }
        return mUtil;
    }

    /**
     * 对象转 json
     * @param object
     * @return
     */
    public String toJson(Object object) {
          return   mParser.ObjectToJsonString(object);
    }

    /**
     * json 转 对象
     * @param json
     * @param clazz
     * @return
     */
    public Object parseObject(String json, Class<?> clazz) {
            return mParser.parseObject(json,clazz);
    }

    /**
     * json 转集合
     * @param json
     * @param clazz
     * @return
     */
    public Object parseArray(String json, Class<?> clazz) {

            return mParser.parseArray(json,clazz);
    }
}
