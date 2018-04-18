package com.bj.componentlib.base;

import android.app.Application;

import com.bj.basic.data.manager.IRepositoryManager;
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
