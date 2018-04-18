package com.bj.lightapp.mvp.model;

import com.bj.basic.data.manager.IRepositoryManager;
import com.bj.componentlib.mvp.BaseModel;
import com.bj.lightapp.data.api.LightAppService;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bj.lightapp.mvp.contract.LightAppContract;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by jiazhen on 2018/4/14.
 * Desc:
 */
public class LightAppModel extends BaseModel implements LightAppContract.Model {

    //@Inject
    public LightAppModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Flowable<List<LightAppRes>> getLightAppList(String userId) {
        return mRepositoryManager.obtainRetrofitService(LightAppService.class).getUserLightApp(userId);
    }
}
