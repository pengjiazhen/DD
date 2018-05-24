package com.bj.lightapp.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bj.componentlib.base.BaseActivity;
import com.bj.componentlib.router.arouter.ARouterPath;
import com.bj.lightapp.R;
import com.bj.lightapp.aspectj.Async;
import com.bj.lightapp.ui.fragment.LightAppFragment;
import com.longfor.log.db.LogSystemManager;
import com.longfor.log.db.listener.IUploadJsonSuccess;

@Route(path = ARouterPath.URL_LIGHT_APP_ACTIVITY)
public class LightAppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_light);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new LightAppFragment()).commit();
        LogSystemManager.getInstance().getUploadLogsConfig(new IUploadJsonSuccess() {
            @Override
            public void success(String uploadJson) {
                //Log.e(TAG,uploadJson);
            }
        });
        Log.e(TAG,"onCreate当前执行线程:"+Thread.currentThread().getName());
        initData();
    }

    //@DebugLog
    @Async
    public void initData(){
        Log.e(TAG,"initData当前执行线程:"+Thread.currentThread().getName());
    }
    @Override
    protected String getTag() {
        return LightAppActivity.class.getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
