package com.bj.dd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.bj.componentlib.base.BaseActivity;
import com.bj.componentlib.router.Router;
import com.bj.componentservice.lightapp.LightAppService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object service = Router.getInstance().getService(LightAppService.class.getSimpleName());
        if (service!=null){
            LightAppService lightAppService = (LightAppService) service;
            Fragment lightAppFragment = lightAppService.getLightAppFragment();
            Log.d(TAG,lightAppFragment+"");
            getSupportFragmentManager().beginTransaction().add(R.id.container,lightAppFragment).commit();
        }
    }

    @Override
    protected String getTag() {
        return MainActivity.class.getSimpleName();
    }

    public void toLightAPPList(View view) {

    }
}
