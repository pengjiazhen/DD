package com.longfor.log.db;

import com.longfor.log.db.listener.IAppExit;
import com.longfor.log.factory.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tongzhenhua
 * @date: 2018/3/23
 * @function:
 */
public class LogCountConfig {
    private int cacheNum; // 缓存日志个数
    private int uploadNum; // 一次上传日志条数
    private List<Integer> logTypes; // 上传日志的类型
    private List<Integer> ignoreCacheTypes; // 忽略缓存的日志类型
    private String appKey;
    private List<Integer> priorityTypes; // 优先上传日志的类型
    private IAppExit iAppExit; // crash时调用结束应用的回调

    LogCountConfig(String appKey, int cacheNum, int uploadNum, List<Integer> logTypes, List<Integer> ignoreCacheTypes, List<Integer> priorityTypes, IAppExit iAppExit) {
        this.appKey = appKey;
        this.cacheNum = cacheNum;
        this.uploadNum = uploadNum;
        this.logTypes = logTypes;
        this.ignoreCacheTypes = ignoreCacheTypes;
        this.priorityTypes = priorityTypes;
        this.iAppExit = iAppExit;

    }
    /**
     * 获取app退出回调
     * @return
     */
    public IAppExit getAppExitListener() {
        return this.iAppExit;
    }
    /**
     * 获取appkey
     * @return
     */
    public String getAppKey() {
        return appKey;
    }
    /**
     * 获取一次上传日志的数量
     * @return
     */
    public int getUploadNum() {
        return uploadNum;
    }

    /**
     * 获取缓存个数
     * @return
     */
    public int getCacheNum() {
        return cacheNum;
    }

    public List<Integer> getLogTypes() {
        return logTypes;
    }

    /**
     * 获取优先上传日志类型
     * @return
     */
    public List<Integer> getPriorityTypes() {
        return priorityTypes;
    }

    /**
     * 获取忽略缓存的日志类型
     * @return
     */
    public List<Integer> getIgnoreChacheTypes() {
        return ignoreCacheTypes;
    }

    public static ConfigBuilder builder() {
        return new ConfigBuilder();
    }

    public static class ConfigBuilder {
        private String appkey = "LongFor";
        private int cacheNum = 0; // 缓存日志个数
        private int uploadNum = 500; // 一次上传日志条数
        private List<Integer> priorityTypes = new ArrayList<>(); // 优先上传日志的类型
        private List<Integer> logTypes = new ArrayList<>(); // 上传日志的类型
        private List<Integer> ignoreCacheTypes = new ArrayList<>(); // 忽略缓存的日志类型
        private IAppExit iAppExit; // crash时调用结束应用的回调
        /**
         * 配置appKey
         * @param appkey
         */
        public ConfigBuilder setAppkey(String appkey) {
            this.appkey = appkey;
            return this;
        }
        /**
         * 配置缓存日志的数量
         * @param cacheNum 默认500条
         * @return
         */
        public ConfigBuilder setCacheNum(int cacheNum) {
            this.cacheNum = cacheNum;
            return this;
        }

        /**
         * 配置一次上传日志的数量
         * @param uploadNum 默认500条
         * @return
         */
        public ConfigBuilder setUploadNum(int uploadNum) {
            this.uploadNum = uploadNum;
            return this;
        }

        public ConfigBuilder addLogsType(int logType) {
            logTypes.add(Integer.valueOf(logType));
            return this;
        }
        /**
         * 添加优先上传日志类型
         * @param types
         * @return
         */
        public ConfigBuilder addPriorityTypes(int ... types) {
            for (int type : types) {
                priorityTypes.add(Integer.valueOf(type));
            }
            return this;
        }
        /**
         * 添加忽略缓存的日志类型
         * @param types
         * @return
         */
        public ConfigBuilder addIgnoreCacheType(int ... types) {
            for (int type : types) {
                ignoreCacheTypes.add(Integer.valueOf(type));
            }
            return this;
        }
        /**
         * 添加crash发生应用程序的回调，一般是结束应用程序
         * @param iAppExit
         * @return
         */
        public ConfigBuilder setAppExitListener(IAppExit iAppExit) {
            this.iAppExit = iAppExit;
            return this;
        }
        public LogCountConfig build() {
            if(priorityTypes.size() == 0) {
                priorityTypes.add(Constants.LOG_TYPE_CRASH);
            }
            return new LogCountConfig(appkey, cacheNum, uploadNum, logTypes, ignoreCacheTypes, priorityTypes, iAppExit);
        }
    }
}
