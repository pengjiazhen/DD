package com.longfor.log.factory.bean;

import android.content.Context;

import com.longfor.log.db.LogSystemManager;
import com.longfor.log.factory.Constants;
import com.longfor.log.factory.utils.JSONUtils;
import com.longfor.log.factory.utils.LogInitUtil;

/**
 * @author: tongzhenhua
 * @date: 2018/3/28
 * @function: PV或时长日志创建工厂
 */
public class PVDurationLogFactory extends ILogInfoFactory {
    private LogInfo log;
    @Override
    public LogInfo create() {
        log = new LogInfo();
        log.setsTime(System.currentTimeMillis());
        log.setLogType(Constants.LOG_TYPE_PV_DURATION);
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
                    LogSystemManager.getInstance().addLogs(Constants.LOG_TYPE_PV_DURATION, null, JSONUtils.beanToJson(log));
                }
            });
        }
    }
}
