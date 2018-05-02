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

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bj.componentlib.base.BaseApplication;
import com.bj.componentlib.base.BaseFragment;
import com.bj.componentlib.router.arouter.ARouterPath;
import com.bj.lightapp.R;
import com.bj.lightapp.adapter.LightAppAdapter;
import com.bj.lightapp.aspectj.ClickBean;
import com.bj.lightapp.aspectj.ClickTrack;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bj.lightapp.di.component.DaggerLightAppComponent;
import com.bj.lightapp.di.module.LightAppModule;
import com.bj.lightapp.mvp.contract.LightAppContract;
import com.bj.lightapp.mvp.presenter.LightAppPresenter;
import com.bj.lightapp.ui.activity.BrowserActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterPath.URL_LIGHT_APP_FRAGMENT)
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
        mLightAppAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List data = adapter.getData();
                LightAppRes lightAppRes = (LightAppRes) data.get(position);
                onLightAppClick(new ClickBean(lightAppRes.getAppId(),lightAppRes));
            }
        });
        mRecyclerView.setAdapter(mLightAppAdapter);

        DaggerLightAppComponent.builder()
                .appComponent(BaseApplication.i().getAppComponent())
                .lightAppModule(new LightAppModule(this)).build()
                .inject(this);
        mPresenter.getLightAppList("93964");

    }


    @ClickTrack(id=1324343)
    private void onLightAppClick(ClickBean clickBean) {
       //clickBean.
        Intent intent = new Intent(getContext(), BrowserActivity.class);
        startActivity(intent);
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
