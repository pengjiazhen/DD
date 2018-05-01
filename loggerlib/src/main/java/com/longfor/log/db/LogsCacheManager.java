package com.longfor.log.db;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tongzhenhua
 * @date: 2018/3/23
 * @function:
 */
public class LogsCacheManager {
    private SparseArray<List<String>> sparseArray = new SparseArray<>();
    private LogCountConfig config;

    private LogsCacheManager() {

    }

    static class Holder {
        private static LogsCacheManager mInstance = new LogsCacheManager();
    }

    public static LogsCacheManager getInstance() {
        return Holder.mInstance;
    }

    public void init(LogCountConfig countConfig) {
        this.config = countConfig;
    }

    public void addLogCache(int logType, String... logJsons) {
        List<String> logs = sparseArray.get(logType);
        if(logs == null) {
            logs = new ArrayList<>();
        }
        if(logJsons != null) {
            for(String log : logJsons) {
                logs.add(log);
            }
        }
        if(logs.size() >= config.getCacheNum()) {
            LogSystemManager.getInstance().addLogs(logType, false,logs);
            logs.clear();
        }
        sparseArray.put(logType, logs);
    }

    public void addLogCache(int logType, Iterable<String> logJsons) {
        List<String> logs = sparseArray.get(logType);
        if(logs == null) {
            logs = new ArrayList<>();
        }
        if(logJsons != null) {
            for(String log : logJsons) {
                logs.add(log);
            }
        }
        if(logs.size() >= config.getCacheNum()) {
            LogSystemManager.getInstance().addLogs(logType, false, logs);
            logs.clear();
        }
        sparseArray.put(logType, logs);
    }
    /**
     * 存储所有的缓存日志到数据库
     */
    public void clearLogCache() {
        for(int i = 0; i < sparseArray.size(); i++) {
            int logType = sparseArray.keyAt(i);
            List<String> logs = sparseArray.get(logType);
            LogSystemManager.getInstance().addLogs(logType, false, logs);
            logs.clear();
        }
        sparseArray.clear();
    }
}
