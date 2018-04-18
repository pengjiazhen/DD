package com.bj.lightapp.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bj.lightapp.R;
import com.bj.lightapp.data.protocol.LightAppRes;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by jiazhen on 2018/4/15.
 * Desc:
 */
public class LightAppAdapter extends BaseQuickAdapter<LightAppRes,BaseViewHolder> {

    public LightAppAdapter(int layoutResId, @Nullable List<LightAppRes> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LightAppRes item) {
        helper.setText(R.id.item_app_name,item.getAppName());
        //helper.setImageResource(R.id.item_app_icon,R.mipmap.char_file_helper_album);
        Glide.with(mContext).load(item.getAppLogoUrl()).placeholder(R.mipmap.char_file_helper_album).into(((ImageView) helper.getView(R.id.item_app_icon)));
    }
}
