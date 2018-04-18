package com.bj.componentlib.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bj.basic.data.manager.IRepositoryManager;
import com.bj.componentlib.BuildConfig;
import com.bj.componentlib.di.component.AppComponent;
import com.bj.componentlib.di.component.DaggerAppComponent;

import javax.inject.Inject;

/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public abstract class BaseApplication extends Application {

    @Inject
    IRepositoryManager mRepositoryManager;

    private static BaseApplication mApplication;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        mApplication = this;
        mAppComponent = DaggerAppComponent.builder().application(this).build();
        mAppComponent.inject(this);
    }

    public static BaseApplication i() {
        return mApplication;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


}
