package com.easytest.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//Json处理
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String converObj2Json(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * json转字符串
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T convertJson2Obj(String json, Class<T> classz){
        return JSONObject.parseObject(json,classz);
    }

    /**
     * 字符串数组转集合对象
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJsonArray2List(String json, Class<T> classz){
        return JSONArray.parseArray(json,classz);
    }
}
