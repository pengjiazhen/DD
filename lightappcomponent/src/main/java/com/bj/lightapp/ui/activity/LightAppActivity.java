package com.bj.lightapp.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bj.componentlib.base.BaseActivity;
import com.bj.componentlib.router.arouter.ARouterPath;
import com.bj.lightapp.R;
import com.bj.lightapp.ui.fragment.LightAppFragment;

@Route(path = ARouterPath.URL_LIGHT_APP_ACTIVITY)
public class LightAppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_light);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new LightAppFragment()).commit();
    }

    @Override
    protected String getTag() {
        return LightAppActivity.class.getSimpleName();
    }
}
