package com.bj.basic.data.source;

import android.app.Application;

import com.bj.basic.data.manager.IRepositoryManager;
import com.bj.basic.data.manager.RepositoryManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiazhen on 2018/4/16.
 * Desc:
 */
@Module
public  class RepositoryManagerModule {

    @Singleton
    @Provides
    public  IRepositoryManager provideRepositoryManager(Application application){
        return new RepositoryManager(application);
    }


}
