package com.longfor.log.factory.net;

import com.longfor.log.factory.bean.LogInfo;

import java.util.List;

/**
 * @author: gaomei
 * @date: 2018/3/22
 * @function:上传的应用数据
 */

public class UploadDataBean {

    private String platform;
    private String model;
    private String appKey;
    private List<LogInfo> logInfos;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public List<LogInfo> getLogInfos() {
        return logInfos;
    }

    public void setLogInfos(List<LogInfo> logInfos) {
        this.logInfos = logInfos;
    }
}
