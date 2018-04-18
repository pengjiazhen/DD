package com.bj.lightapp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bj.componentlib.base.BaseApplication;
import com.bj.componentlib.base.BaseFragment;
import com.bj.lightapp.R;
import com.bj.lightapp.adapter.LightAppAdapter;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bj.lightapp.di.component.DaggerLightAppComponent;
import com.bj.lightapp.di.module.LightAppModule;
import com.bj.lightapp.mvp.contract.LightAppContract;
import com.bj.lightapp.mvp.presenter.LightAppPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightAppFragment extends BaseFragment<LightAppPresenter> implements LightAppContract.View{

    private RecyclerView mRecyclerView;
    private LightAppAdapter mLightAppAdapter;

    public LightAppFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light_app, container, false);
    }

    @Override
    protected String getLogTag() {
        return LightAppFragment.class.getSimpleName();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_light_app);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mLightAppAdapter = new LightAppAdapter(R.layout.item_light_app,new ArrayList<LightAppRes>());
        mRecyclerView.setAdapter(mLightAppAdapter);

        DaggerLightAppComponent.builder()
                .appComponent(BaseApplication.i().getAppComponent())
                .lightAppModule(new LightAppModule(this)).build()
                .inject(this);
        mPresenter.getLightAppList("93964");

    }

    @Override
    public void refreshLightAppList(List<LightAppRes> lightApps) {
        mLightAppAdapter.addData(lightApps);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
