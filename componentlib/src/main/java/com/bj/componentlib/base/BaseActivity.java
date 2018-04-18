package com.bj.componentlib.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bj.componentlib.mvp.BasePresenter;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected String TAG = getTag();

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract String getTag();
}
