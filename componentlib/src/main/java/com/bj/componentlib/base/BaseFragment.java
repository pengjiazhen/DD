package com.bj.componentlib.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bj.componentlib.R;
import com.bj.componentlib.mvp.IPresenter;

import javax.inject.Inject;

public abstract class BaseFragment<P extends IPresenter> extends Fragment {
    protected String TAG = getLogTag();
    @Inject
    protected P mPresenter;
    public BaseFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    protected abstract String getLogTag();
}
