package com.bj.lightapp.data.protocol;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public class LightAppRes {

    /*"{
    "appauthtype": 2,
    "apphomepage": "http://111.207.120.1:9013",
    "appid": 1040,
    "appname": "信用超期",
    "apppage1": "",
    "compid": 5,
    "logopath": "",
    "sort": 1,
    "status": 1,
    "total": 124,
    "updatetime": "2018-03-13 15:44:59.0",
    "updatetpe": 2,
    "userid": 93964
  },*/
    @SerializedName("appid")
    private String appId;//应用id
    @SerializedName("appauthtype")
    private int appAuthType;//更新标示 1添加,2修改,3删除.
    @SerializedName("appname")
    private String appName;//应用名称
    @SerializedName("apphomepage")
    private String appUrl;//应用url
    @SerializedName("logopath")
    private String appLogoUrl;//应用logo url
    @SerializedName("sort")
    private int appOrder;//应用的排序位


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getAppAuthType() {
        return appAuthType;
    }

    public void setAppAuthType(int appAuthType) {
        this.appAuthType = appAuthType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getAppLogoUrl() {
        return appLogoUrl;
    }

    public void setAppLogoUrl(String appLogoUrl) {
        this.appLogoUrl = appLogoUrl;
    }

    public int getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(int appOrder) {
        this.appOrder = appOrder;
    }

}
