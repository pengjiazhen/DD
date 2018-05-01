package com.longfor.log.db.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;

/**
 * @author: tongzhenhua
 * @date: 2018/3/29
 * @function:
 */
public class CrashHandlerUtil {
    /**
     * 判断是否在主线程中执行 如果是返回true 不是返回false
     */
    public static boolean isInMainThread() {
        //注意这个地方 我们无法一定可以取得myLooper的值的 比如说 你的thread 没有绑定消息循环
        //那你的mylooper就返回的一定是null了，只有绑定了以后才会返回相应的值
        return Looper.myLooper() == Looper.getMainLooper();
    }


    //判断是否是主进程 如果是 就返回true 否则返回false
    public static boolean isMainProcess(Context context)
    {
        return context.getPackageName().equals(getProcessName(context));
    }

    //取得进程名
    public static String getProcessName(Context context) {
        String currentProcessName = "";
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcessName = processInfo.processName;
                break;
            }
        }
        return currentProcessName;
    }
}
