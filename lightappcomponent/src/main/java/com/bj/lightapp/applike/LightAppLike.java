package com.bj.lightapp.applike;

import com.bj.componentlib.applicationlike.IApplicationLike;
import com.bj.componentlib.router.Router;
import com.bj.componentservice.lightapp.LightAppService;
import com.bj.lightapp.serviceimpl.LightAppServiceImpl;


/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public class LightAppLike implements IApplicationLike {


    private Router router = Router.getInstance();
    @Override
    public void onCreate() {
        router.addService(LightAppService.class.getSimpleName(),new LightAppServiceImpl());
    }

    @Override
    public void onStop() {
        router.removeService(LightAppService.class.getSimpleName());
    }


}
