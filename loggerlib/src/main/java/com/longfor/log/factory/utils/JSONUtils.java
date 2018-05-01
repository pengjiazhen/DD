package com.longfor.log.factory.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

//import com.google.gson.reflect.TypeToken;

/**
 * @author: gaomei
 * @date: 2018/3/23
 * @function:
 */

public class JSONUtils {
    /**
     * 把bean对象转换为json字符串
     * @param object
     * @return json字符串
     */
    public static String beanToJson(Object object) {
//        return GSONHandler.beanToJson(object);
        return JSON.toJSONString(object);
    }

    public static<T> String listToJson(List<T> list) {
//        return GSONHandler.beanToJson(list);
        return JSON.toJSONString(list);
    }

    public static String mapToJson(Map map) {
//        return GSONHandler.beanToJson(map);
        return JSON.toJSONString(map);
    }

    public static <T> T jsonToBean(String json, Class<T> tClass) {
//        T bean = GSONHandler.jsonToBean(json, tClass);
//        return bean;
        return JSON.parseObject(json, tClass);
    }

    /**
     * 把jsonArray转换为List列表
     * @param jsons
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsons, Class<T> tClass) {
//        List<T> list = GSONHandler.jsonToListOrMap(jsons, new TypeToken<List<T>>() {
//        });
//        return list;
        return JSON.parseArray(jsons, tClass);
    }

//    public static <T, K> Object jsonToMap(String json, Class<T> keyClass, Class<K> valueClass) {
//        Map<T, K> map = GSONHandler.jsonToListOrMap(json, new TypeToken<Map<T, K>>() {
//        });
//        return map;
//    }

}
