package com.bj.lightapp.di.module;

import com.bj.basic.data.manager.IRepositoryManager;
import com.bj.componentlib.di.scope.FragmentScoped;
import com.bj.lightapp.mvp.contract.LightAppContract;
import com.bj.lightapp.mvp.model.LightAppModel;
import com.bj.lightapp.mvp.presenter.LightAppPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiazhen on 2018/4/16.
 * Desc:
 */
@Module
public class LightAppModule {

   private LightAppContract.View mView;

    public LightAppModule(LightAppContract.View view) {
        mView = view;
    }

    @FragmentScoped
    @Provides
    public LightAppModel provideLightAppModel(IRepositoryManager repositoryManager){
        return new LightAppModel(repositoryManager);
    }

    //@Binds
   // public abstract LightAppContract.Model provideLightAppModel(LightAppModel lightAppModel);

    @FragmentScoped
    @Provides
    public LightAppPresenter provideLightAppPresenter(LightAppModel lightAppModel){
        return new LightAppPresenter(lightAppModel,mView);
    }
}
