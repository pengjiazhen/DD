package com.longfor.log.factory.utils;

import android.content.Context;

import com.longfor.log.db.LogSystemManager;
import com.longfor.log.factory.bean.LogInfo;

/**
 * @author: tongzhenhua
 * @date: 2018/3/28
 * @function:
 */
public class LogInitUtil {
    /**
     * 初始化log日志
     * 给日志公共部分的信息赋值
     * @param context
     * @param logInfo
     * @return
     */
    public static LogInfo initCommonLogInfo(Context context, LogInfo logInfo){
        logInfo.setAppVersion(DeviceSystemUtils.getAppVersion(context));
        logInfo.setOsVersion(DeviceSystemUtils.getOSVersion());
        logInfo.setUserInfo(LogSystemManager.getInstance().getUserInfo());
        logInfo.setNetwork(DeviceSystemUtils.getNetworkType(context));
        logInfo.setCpu(String.valueOf(DeviceSystemUtils.getProcessCpuRate()));
        logInfo.setMemory(DeviceSystemUtils.getAppMemory(context));
//        logInfo.setsTime(System.currentTimeMillis());
        return logInfo;
    }
}
