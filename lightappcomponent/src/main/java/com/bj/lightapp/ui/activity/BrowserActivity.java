package com.bj.lightapp.ui.activity;

import android.os.Bundle;

import com.bj.componentlib.base.BaseActivity;
import com.bj.lightapp.R;

public class BrowserActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
    }

    @Override
    protected String getTag() {
        return BrowserActivity.class.getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
