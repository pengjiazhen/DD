package com.bj.componentlib.di.component;

import android.app.Application;

import com.bj.basic.data.manager.IRepositoryManager;
import com.bj.basic.data.source.RepositoryManagerModule;
import com.bj.componentlib.base.BaseApplication;
import com.bj.componentlib.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by jiazhen on 2018/4/16.
 * Desc:
 */
@Singleton
@Component(modules = {ApplicationModule.class,
        RepositoryManagerModule.class,
})
public interface AppComponent {

    IRepositoryManager getRepositoryManager();

    void inject(BaseApplication instance);

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
