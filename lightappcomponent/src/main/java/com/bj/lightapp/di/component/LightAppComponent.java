package com.bj.lightapp.di.component;

import com.bj.componentlib.di.component.AppComponent;
import com.bj.componentlib.di.scope.FragmentScoped;
import com.bj.lightapp.di.module.LightAppModule;
import com.bj.lightapp.ui.fragment.LightAppFragment;

import dagger.Component;

/**
 * Created by jiazhen on 2018/4/16.
 * Desc:
 */
@FragmentScoped
@Component(modules = LightAppModule.class,dependencies = AppComponent.class)
public interface LightAppComponent {
    void inject(LightAppFragment lightAppFragment);
}
