package com.bj.lightapp.mvp.presenter;

import android.util.Log;

import com.bj.componentlib.mvp.BasePresenter;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bj.lightapp.mvp.contract.LightAppContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiazhen on 2018/4/15.
 * Desc:
 */
public class LightAppPresenter extends BasePresenter<LightAppContract.Model,LightAppContract.View> {

    public LightAppPresenter(LightAppContract.Model model, LightAppContract.View rootView) {
        super(model, rootView);
    }

    public void getLightAppList(String userId){
        mModel.getLightAppList(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<LightAppRes>>() {
                    @Override
                    public void accept(List<LightAppRes> lightAppRes) {
                        Log.d(TAG, lightAppRes.toString());
                        mRootView.refreshLightAppList(lightAppRes);
                    }
                });
    }
}
