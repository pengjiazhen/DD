package com.bj.lightapp;

import android.content.Intent;

import com.bj.componentlib.base.BaseApplication;
import com.longfor.log.db.LogCountConfig;
import com.longfor.log.db.LogSystemManager;
import com.longfor.log.db.listener.IAppExit;
import com.longfor.log.factory.Constants;

/**
 * Created by jiazhen on 2018/4/18.
 * Desc:
 */
public class LightAppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogManager();
    }

    private void initLogManager(){
        LogCountConfig config = LogCountConfig.builder()
                .setCacheNum(20) // 每20条日志存储一次数据库
                .setUploadNum(20) // 每次上传300条数据
                .addIgnoreCacheType(Constants.LOG_TYPE_CRASH) // 设置崩溃日志直接保存数据库
                .setAppkey("com.longfor.logstatisticsdemo")
                .setAppExitListener(new IAppExit() {
                    @Override
                    public void exitApp() {
                        // 应用发生crash时关闭应用，这个是退出应用的方法
                        exit();
                    }
                })
                .build();
        LogSystemManager.getInstance().init(this, config);
    }
    public void exit() {
        Intent intent = new Intent();
        intent.setAction(Constants.Action_EXIT);
        sendBroadcast(intent);
    }
}
