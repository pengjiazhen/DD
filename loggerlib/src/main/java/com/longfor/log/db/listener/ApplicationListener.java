package com.longfor.log.db.listener;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.longfor.log.db.LogSystemManager;

/**
 * @author: tongzhenhua
 * @date: 2018/3/30
 * @function:
 */
public class ApplicationListener implements Application.ActivityLifecycleCallbacks {

    private int foregroundCount = 0; // 位于前台的 Activity 的数目

    @Override
    public void onActivityStopped(Activity activity) {
        foregroundCount--;
        if(foregroundCount <= 0) {
            LogSystemManager.getInstance().exit();
        }
    }
    @Override
    public void onActivityStarted(Activity activity) {
        if (foregroundCount <= 0) {
            // TODO 这里处理从后台恢复到前台的逻辑
        }
        foregroundCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
