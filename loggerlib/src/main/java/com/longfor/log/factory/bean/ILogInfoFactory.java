package com.longfor.log.factory.bean;

import android.content.Context;

/**
 * @author: gaomei
 * @date: 2018/3/26
 * @function:
 */

public abstract class ILogInfoFactory {
    public abstract LogInfo create();
    public abstract void save(final Context context);
}
