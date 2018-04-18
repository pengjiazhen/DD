package com.bj.basic.data.manager;

import android.app.Application;
import android.content.Context;

import com.bj.basic.data.net.RetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * ================================================
 * 用来管理网络请求层,以及数据缓存层,以后可能添加数据库请求层
 * <p>
 * Created by JessYan on 13/04/2017 09:52
 * Contact with jess.yan.effort@gmail.com
 * Follow me on https://github.com/JessYanCoding
 * ================================================
 */
@Singleton
public class RepositoryManager implements IRepositoryManager {

    private Application mApplication;
    private final Map<String, Object> mRetrofitServiceCache = new HashMap<>();
    private final Map<String, Object> mCacheServiceCache = new HashMap<>();

    @Inject
    public RepositoryManager(Application application) {
        this.mApplication = application;
    }


    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        return  RetrofitFactory.getInstance().create(service);
    }

    @Override
    public <T> T obtainCacheService(Class<T> cache) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public Context getContext() {
        return mApplication;
    }
}
