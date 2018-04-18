package com.bj.dd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bj.componentlib.base.BaseActivity;
import com.bj.componentlib.router.arouter.ARouterPath;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Object service = Router.getInstance().getService(LightAppService.class.getSimpleName());
        if (service!=null){
            LightAppService lightAppService = (LightAppService) service;
            Fragment lightAppFragment = lightAppService.getLightAppFragment();
            Log.d(TAG,lightAppFragment+"");
            getSupportFragmentManager().beginTransaction().add(R.id.container,lightAppFragment).commit();
        }*/

        Fragment fragment = (Fragment) ARouter.getInstance().build(ARouterPath.URL_LIGHT_APP_FRAGMENT).navigation();
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();

    }

    @Override
    protected String getTag() {
        return MainActivity.class.getSimpleName();
    }

    public void toLightAPPList(View view) {
        try {
            ARouter.getInstance().build(ARouterPath.URL_LIGHT_APP_ACTIVITY).navigation(this,new NavCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    Log.d("ARouter", "找到了");
                }

                @Override
                public void onLost(Postcard postcard) {
                    Log.d("ARouter", "找不到了");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    Log.d("ARouter", "跳转完了");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    Log.d("ARouter", "被拦截了");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
