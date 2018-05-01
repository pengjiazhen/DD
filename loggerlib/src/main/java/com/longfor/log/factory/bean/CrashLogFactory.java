package com.longfor.log.factory.bean;

import android.content.Context;
import android.util.Log;

import com.longfor.log.db.LogSystemManager;
import com.longfor.log.factory.Constants;
import com.longfor.log.factory.utils.JSONUtils;
import com.longfor.log.factory.utils.LogInitUtil;

/**
 * @author: gaomei
 * @date: 2018/3/26
 * @function: crash日志创建工厂
 */

public class CrashLogFactory extends ILogInfoFactory {
    private LogInfo log;
    @Override
    public LogInfo create() {
        log = new LogInfo();
        log.setsTime(System.currentTimeMillis());
        log.setLogType(Constants.LOG_TYPE_CRASH);
        return log;
    }

    @Override
    public void save(final Context context) {
        if(log != null) {
            if(log.geteTime() == 0) {
                log.seteTime(log.getsTime());
            }
            LogSystemManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    LogInitUtil.initCommonLogInfo(context.getApplicationContext(), log);
                    LogSystemManager.getInstance().addLogs(Constants.LOG_TYPE_CRASH, null,JSONUtils.beanToJson(log));
                    Log.e("log","存完了crash日志");
                }
            });
        }
    }
}
