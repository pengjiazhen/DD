package com.bj.lightapp.serviceimpl;

import android.support.v4.app.Fragment;

import com.bj.componentservice.lightapp.LightAppService;
import com.bj.lightapp.ui.fragment.LightAppFragment;

/**
 * Created by jiazhen on 2018/4/13.
 * Desc:
 */
public class LightAppServiceImpl implements LightAppService {
    @Override
    public Fragment getLightAppFragment() {
        return new LightAppFragment();
    }
}
