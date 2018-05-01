package com.longfor.log.db;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.longfor.log.db.bean.CommonsLog;
import com.longfor.log.db.listener.ApplicationListener;
import com.longfor.log.db.listener.IDBCallBack;
import com.longfor.log.db.listener.IJsonArraySuccess;
import com.longfor.log.db.listener.IJsonListSuccess;
import com.longfor.log.db.listener.IUploadJsonSuccess;
import com.longfor.log.db.utils.CrashHandler;
import com.longfor.log.db.utils.LogFormatUtils;
import com.longfor.log.factory.net.UploadDataBeanUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: tongzhenhua
 * @date: 2018/3/20
 * @function:
 */
public class LogSystemManager {
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static int KEEP_ALIVE_TIME = 1;
    private static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private static ExecutorService threadPool;
    private static ExecutorService singleThreadPool;
    private LogCountConfig logCountConfig;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private String userInfo = "";

    private LogSystemManager() {
        threadPool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES*2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue);
        singleThreadPool = Executors.newSingleThreadExecutor();
    }

    /**
     * 获取数据存取类的单例
     * @return
     */
    public static LogSystemManager getInstance() {
        return DBLogManagerCreator.mInstance;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, LogCountConfig countConfig) {
        DataBaseManager.getInstance().init(context);
        if(countConfig == null) {
            logCountConfig = LogCountConfig.builder().build();
        }else {
            this.logCountConfig = countConfig;
        }
        LogsCacheManager.getInstance().init(logCountConfig);
        CrashHandler.getInstance(context).init(logCountConfig);
        ((Application)context.getApplicationContext()).registerActivityLifecycleCallbacks(new ApplicationListener());
    }

    /**
     * 更新日志存取配置信息
     * 支持动态的改变日志存取的配置信息
     * @param config
     */
    public void updateConfig(LogCountConfig config) {
        logCountConfig = config;
        LogsCacheManager.getInstance().init(config);
        LogsCacheManager.getInstance().clearLogCache();
    }

    public void execute(Runnable runnable) {
        getInstance().threadPool.execute(runnable);
    }

    /**
     * 在单线程中执行任务
     * @param runnable
     */
    private void executeSingleThreed(Runnable runnable) {
        getInstance().singleThreadPool.execute(runnable);
    }

    // 统一一个数据表存储数据

    /**
     * 同步添加日志数据
     * @param type
     * @param jsons
     */
    public void addLog(final int type, String... jsons) {
        addLog(type, true, jsons);
    }

    /**
     * 同步添加日志数据
     * @param type
     * @param isCache 是否缓存
     * @param jsons
     */
    public void addLog(final int type, boolean isCache, final String...jsons) {
        if(jsons == null || jsons.length == 0) {
            Log.e("log", "添加的日志信息为空");
            return;
        }
        List<CommonsLog> logList = new ArrayList<>();
        long time = System.currentTimeMillis();
        for(String log : jsons) {
            CommonsLog commonsLog = new CommonsLog();
            commonsLog.setLogType(type);
            commonsLog.setJson(log);
            commonsLog.setTime(time);
            logList.add(commonsLog);
        }
        if(!isCache || logCountConfig.getIgnoreChacheTypes().contains(type) || logList.size() >= logCountConfig.getCacheNum()) {
            DataBaseManager.getInstance().addCommonsLog(logList);
        }
        else {
            LogsCacheManager.getInstance().addLogCache(type, jsons);
        }
    }
    /**
     * 添加日志到数据库
     * @param type 储存的日志类型
     * @param callBack 成功回调
     * @param jsons 不确定个数String数组
     */
    public void addLogs(final int type, final IDBCallBack callBack, final String... jsons) {
        if(jsons == null || jsons.length == 0) {
            Log.e("log", "添加的日志信息为空");
            callBack.success();
            return;
        }
        executeSingleThreed(new Runnable() {
            @Override
            public void run() {
                addLog(type, true, jsons);
                Log.e("log","存了1条");
                if (callBack != null) {
                    callBack.success();
                }
            }
        });

    }

    /**
     * 同步添加日志数据
     * @param type
     * @param jsonList
     */
    public void addLogs(final int type, final Iterable<String> jsonList) {
        addLogs(type, true, jsonList);
    }

    /**
     * 同步添加日志数据
     * @param type
     * @param isCache 是否缓存
     * @param jsonList
     */
    public void addLogs(int type, boolean isCache, Iterable<String> jsonList) {
        if(jsonList == null) {
            Log.e("log", "添加的日志信息为空");
            return;
        }
        List<CommonsLog> logList = new ArrayList<>();
        long time = System.currentTimeMillis();
        for(String log : jsonList) {
            CommonsLog commonsLog = new CommonsLog();
            commonsLog.setLogType(type);
            commonsLog.setJson(log);
            commonsLog.setTime(time);
            logList.add(commonsLog);
        }
        if(!isCache || logCountConfig.getIgnoreChacheTypes().contains(type) || logList.size() >= logCountConfig.getCacheNum()) {
            DataBaseManager.getInstance().addCommonsLog(logList);
        }
        else {
            LogsCacheManager.getInstance().addLogCache(type, jsonList);
        }
    }
    /**
     * 异步添加日志到数据库
     * @param type 储存的日志类型
     * @param jsonList string集合
     * @param callBack 成功回调
     */
    public void addLogs(final int type, final Iterable<String> jsonList, final IDBCallBack callBack) {
        if(jsonList == null) {
            callBack.success();
            Log.e("log", "添加的日志信息为空");
            return;
        }
        executeSingleThreed(new Runnable() {
            @Override
            public void run() {
                addLogs(type, true, jsonList);
                if (callBack != null) {
                    callBack.success();
                }
            }
        });

    }

    /**
     * 获取 jsonArray
     * @param type
     * @param success
     */
    public void getLogsByType(final int type, final IJsonListSuccess success) {
        execute(new Runnable() {
            @Override
            public void run() {
                success.onSuccessByType(LogFormatUtils.CommonsLogsToStringArray(DataBaseManager.getInstance().getCommonsLogByType(type)));
            }
        });
    }

    /**
     * 获取 json串集合
     * @param type
     * @param num
     * @param success 回调返回json串集合
     */
    public void getLogsByType(final int type, final int num, final IJsonListSuccess success) {
        execute(new Runnable() {
            @Override
            public void run() {
                success.onSuccessByType(LogFormatUtils.CommonsLogsToStringArray(DataBaseManager.getInstance().getCommonsLogByType(type, num)));
            }
        });
    }

    /**
     * 获取 所有日志信息jsonArray
     * @param success
     */
    public void getLogs(final IJsonArraySuccess success) {
        execute(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = LogFormatUtils.CommonsLogsToJosnArray(DataBaseManager.getInstance().getCommonsLog());
                success.onSuccess(jsonArray);
            }
        });
    }

    /**
     * 获取 配置数量的日志信息jsonArray
     * @param success
     */
    public void getLogsConfig(final IJsonArraySuccess success) {
        getLogs(logCountConfig.getUploadNum(), success);
    }

    /**
     * 获取 前num条日志信息jsonArray
     * @param success
     * @param num
     */
    public void getLogs(final int num, final IJsonArraySuccess success) {
        execute(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = LogFormatUtils.CommonsLogsToJosnArray(DataBaseManager.getInstance().getCommonsLog(num));
                success.onSuccess(jsonArray);
            }
        });
    }

    /**
     * 清除所有日志信息
     * @param callBack 成功回调
     */
    public void deleteLogs(final IDBCallBack callBack) {
        execute(new Runnable() {
            @Override
            public void run() {
                DataBaseManager.getInstance().deleteLogs();
                if(callBack != null) {
                    callBack.success();
                }
            }
        });
    }

    /**
     * 清除配置数量日志信息setUploadNum设置的数量
     * @param callBack 成功回调
     */
    public void deleteLogsConfig(final IDBCallBack callBack) {
        deleteLogs(logCountConfig.getUploadNum(), callBack);
        execute(new Runnable() {
            @Override
            public void run() {
                for(int type : logCountConfig.getPriorityTypes()) {
                    DataBaseManager.getInstance().deleteLogsByType(type);
                }
                DataBaseManager.getInstance().deleteLogs(logCountConfig.getUploadNum());
            }
        });
    }
    /**
     * 清除num条日志信息
     * @param callBack 成功回调
     */
    public void deleteLogs(final int num, final IDBCallBack callBack) {
        execute(new Runnable() {
            @Override
            public void run() {
                DataBaseManager.getInstance().deleteLogs(num);
                if(callBack != null) {
                    callBack.success();
                }
            }
        });

    }

    /**
     * 删除指定类型的所有日志
     * @param type 日志类型
     * @param callBack 成功回调
     */
    public void deleteLogsByType(final int type, final IDBCallBack callBack) {
        execute(new Runnable() {
            @Override
            public void run() {
                DataBaseManager.getInstance().deleteLogsByType(type);
                if(callBack != null) {
                    callBack.success();
                }
            }
        });

    }

    /**
     * 删除指定类型的指定数目日志
     * @param type 日志类型
     * @param num 删除日志数量
     * @param callBack 删除成功回调
     */
    public void deleteLogsByType(final int type, final int num, final IDBCallBack callBack) {
        execute(new Runnable() {
            @Override
            public void run() {
                DataBaseManager.getInstance().deleteLogsByType(type, num);
                if(callBack != null) {
                    callBack.success();
                }
            }
        });

    }

    /**
     * 获取上传给后台的日志数据
     * @param type 获取日志的类型
     * @param uploadJsonSuccess 返回数据类型为后台约定数据格式
     */
    public void getUploadLogsByType(final int type, final IUploadJsonSuccess uploadJsonSuccess) {
        execute(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = LogFormatUtils.CommonsLogsToJosnArray(DataBaseManager.getInstance().getCommonsLogByType(type));
                uploadJsonSuccess.success(UploadDataBeanUtils.getUploadDataJson(logCountConfig.getAppKey(), jsonArray.toString()));
            }
        });
    }

    /**
     * 获取上传给后台的日志数据
     * 获取数量为配置的数量，默认为500条
     * @param uploadJsonSuccess 返回数据类型为后台约定数据格式
     */
    public void getUploadLogsConfig(final IUploadJsonSuccess uploadJsonSuccess) {
        execute(new Runnable() {
            @Override
            public void run() {
                List<Integer> priorityTypes = logCountConfig.getPriorityTypes();
                List<String>[] lists = new List[priorityTypes.size()+1];
                int num = logCountConfig.getUploadNum();
                for (int i = 0; i < priorityTypes.size(); i++) {
                    lists[i] = (List<String>) LogFormatUtils.CommonsLogsToStringArray(DataBaseManager.getInstance().getCommonsLogByType(priorityTypes.get(i)));
                }

                int [] types = new int[priorityTypes.size()];
                for(int i = 0; i < types.length; i++) {
                    types[i] = priorityTypes.get(i);
                }
                lists[priorityTypes.size()] =  (List<String>) LogFormatUtils.CommonsLogsToStringArray(DataBaseManager.getInstance().getCommonsLogIgnoreType(num, types));

                JSONArray jsonArray = LogFormatUtils.CommonsLogsToJosnArray(lists);
                uploadJsonSuccess.success(UploadDataBeanUtils.getUploadDataJson(logCountConfig.getAppKey(), jsonArray.toString()));
            }
        });
    }

    /**
     * 获取上传给后台的日志数据
     * 获取数量为数据库全部数据
     * @param uploadJsonSuccess 返回数据类型为后台约定数据格式
     */
    public void getUploadLogs(final IUploadJsonSuccess uploadJsonSuccess) {
        execute(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = LogFormatUtils.CommonsLogsToJosnArray(DataBaseManager.getInstance().getCommonsLog());
                uploadJsonSuccess.success(UploadDataBeanUtils.getUploadDataJson(logCountConfig.getAppKey(), jsonArray.toString()));
            }
        });
    }
    /**
     * 应用关闭或进入后台时调用，防止日志缓存数据丢失
     */
    public void exit() {
        LogsCacheManager.getInstance().clearLogCache();
    }
    /**
     * 应用关闭或进入后台时调用，防止日志缓存数据丢失
     * @param callBack 缓存保存成功的回调
     */
    public void exit(final IDBCallBack callBack) {
        execute(new Runnable() {
            @Override
            public void run() {

                LogsCacheManager.getInstance().clearLogCache();
                if(callBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success();
                        }
                    });

                }
            }
        });
    }

    /**
     * 设置用户信息
     * @param userInfo
     */
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 删除过期的日志
     * @param day
     */
    public void deleteOutDateLogs(final int day) {
        execute(new Runnable() {
            @Override
            public void run() {
                long outTimes = System.currentTimeMillis() - day*24*60*60*1000;
//        long outTimes = System.currentTimeMillis() - 2*60*1000;
                DataBaseManager.getInstance().deleteLogsByTime(outTimes);
            }
        });


    }
    /**
     * 获取用户信息
     * @return
     */
    public String getUserInfo() {
        return this.userInfo;
    }

    private static class DBLogManagerCreator {
        private static LogSystemManager mInstance = new LogSystemManager();
    }

}
