package com.longfor.log.db.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.longfor.log.db.LogCountConfig;
import com.longfor.log.db.LogSystemManager;
import com.longfor.log.db.R;
import com.longfor.log.factory.Constants;
import com.longfor.log.factory.bean.CrashLogFactory;
import com.longfor.log.factory.bean.LogInfo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author: gaomei
 * @date: 2018/3/20
 * @function:
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static Context mContext;
    private static final String TAG = "CrashHandler";
    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    LogCountConfig config;
    private static Handler handler = new Handler(Looper.getMainLooper());

    private CrashHandler() {
    }

    public static CrashHandler getInstance(Context context) {
        mContext = context;
        return sInstance;
    }

    public void init(LogCountConfig config) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.config = config;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        saveCrashLogBean(throwable);
    }

    private void saveCrashLogBean(Throwable throwable) {
        if (throwable != null) {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            throwable.printStackTrace(printWriter);
            printWriter.close();
            CrashLogFactory iLogInfoFactory = new CrashLogFactory();
            LogInfo logInfo = iLogInfoFactory.create();
            logInfo.setLogLevel(Constants.LOG_LEVEL_ERROR);
            logInfo.setCrashName(throwable.getClass().getName());
            logInfo.setCrashInfo(throwable.getMessage());
            logInfo.setCrashContent(writer.toString());
            iLogInfoFactory.save(mContext);
            LogSystemManager.getInstance().exit();
            Log.e(TAG, throwable.getClass().getName()+throwable.getMessage()+writer.toString());
            exit();
        }
    }

    private void exit() {
        if(CrashHandlerUtil.isMainProcess(mContext)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(mContext, R.string.app_error_exit, Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }).start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(config != null && config.getAppExitListener() != null) {

            config.getAppExitListener().exitApp();

        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
