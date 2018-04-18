package com.bj.lightapp.mvp.contract;

import com.bj.componentlib.mvp.IModel;
import com.bj.componentlib.mvp.IView;
import com.bj.lightapp.data.protocol.LightAppRes;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jiazhen on 2018/4/14.
 * Desc:
 */
public interface LightAppContract {

    interface View extends IView{
        void refreshLightAppList(List<LightAppRes> lightApps);
    }

    interface Model extends IModel{
        Flowable<List<LightAppRes>> getLightAppList(String userId);
    }

}
