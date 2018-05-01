package com.longfor.log.db.utils;

import android.util.Log;

import com.longfor.log.db.bean.CommonsLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tongzhenhua
 * @date: 2018/3/19
 * @function:
 */
public class LogFormatUtils {
    private static final String TAG = "LogFormatUtils";

    /**
     * CommonsLog集合转化为jsonArray
     * @param commonsLogs
     * @return
     */
    public static JSONArray CommonsLogsToJosnArray(Iterable<CommonsLog> commonsLogs) {
        if(commonsLogs == null) {
            Log.e(TAG, "转换的集合为空");
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for(CommonsLog log : commonsLogs) {
            try {
                JSONObject jsonObject = new JSONObject(log.getJson());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return jsonArray;
    }
    /**
     * CommonsLog集合转化为String集合
     * @param commonsLogs
     * @return
     */
    public static Iterable<String> CommonsLogsToStringArray(Iterable<CommonsLog> commonsLogs) {
        if(commonsLogs == null) {
            Log.e(TAG, "转换的集合为空");
            return null;
        }
        List<String> stringList = new ArrayList<>();
        for(CommonsLog log : commonsLogs) {
            stringList.add(log.getJson());
        }
        return stringList;
    }

    /**
     * CommonsLog集合列表转化为jsonArray
     * @param stringLists
     * @return
     */
    public static JSONArray CommonsLogsToJosnArray(Iterable<String>... stringLists) {
        if(stringLists == null) {
            Log.e(TAG, "转换的集合为空");
            return null;
        }
        int count =0;
        JSONArray jsonArray = new JSONArray();
        for(Iterable<String> stringList : stringLists) {
            if(stringList==null) {
                continue;
            }
            for (String string : stringList) {
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    jsonArray.put(jsonObject);
                    count++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.e("log","取出的日志count="+count);
        return jsonArray;
    }
}
