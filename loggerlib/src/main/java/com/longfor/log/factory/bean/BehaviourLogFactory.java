package com.longfor.log.factory.bean;

import android.content.Context;
import android.util.Log;

import com.longfor.log.db.LogSystemManager;
import com.longfor.log.factory.Constants;
import com.longfor.log.factory.utils.JSONUtils;
import com.longfor.log.factory.utils.LogInitUtil;

/**
 * @author: tongzhenhua
 * @date: 2018/3/28
 * @function: 行为日志创建工厂
 */
public class BehaviourLogFactory extends ILogInfoFactory {
    LogInfo log;
    @Override
    public LogInfo create() {
        log = new LogInfo();
        log.setsTime(System.currentTimeMillis());
        log.setLogType(Constants.LOG_TYPE_BEHAVIOR);
        return log;
    }

    @Override
    public void save(final Context context) {
        if(log != null) {
            LogSystemManager.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    if(log.geteTime() == 0) {
                        log.seteTime(log.getsTime());
                    }
                    else {
                        log.setDuration(log.geteTime() - log.getsTime());
                    }
                    LogInitUtil.initCommonLogInfo(context.getApplicationContext(), log);
                    LogSystemManager.getInstance().addLogs(Constants.LOG_TYPE_BEHAVIOR, null,JSONUtils.beanToJson(log));
                    Log.e("log","存完了行为日志");
                }
            });

        }
    }
}
