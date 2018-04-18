package com.bj.dd;

import com.bj.componentlib.base.BaseApplication;
import com.bj.componentlib.router.Router;

/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public class DApplication extends BaseApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        Router.registerComponent("com.bj.lightapp.applike.LightAppLike");
    }

}
