package com.longfor.log.factory.net;

import android.os.Build;
import android.util.Log;

import com.longfor.log.factory.bean.LogInfo;
import com.longfor.log.factory.utils.JSONUtils;

import java.util.List;

/**
 * @author: gaomei
 * @date: 2018/3/26
 * @function:
 */

public class UploadDataBeanUtils {

    public static String getUploadDataJson(String appkey, String logInfos) {
        List<LogInfo> logInfoList = JSONUtils.jsonToList(logInfos, LogInfo.class);
        if (logInfoList == null || logInfoList.size() == 0) {
            return "";
        }
        UploadDataBean uploadDataBean = new UploadDataBean();
        uploadDataBean.setPlatform("android");
        uploadDataBean.setModel(Build.MANUFACTURER + " " + Build.MODEL);
        uploadDataBean.setAppKey(appkey);
        uploadDataBean.setLogInfos(logInfoList);
        Log.e("tag", logInfoList.size() + "条");
        return JSONUtils.beanToJson(uploadDataBean);
    }

}
