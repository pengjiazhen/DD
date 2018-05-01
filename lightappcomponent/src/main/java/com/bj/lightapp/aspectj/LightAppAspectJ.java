package com.bj.lightapp.aspectj;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.bj.componentlib.base.BaseApplication;
import com.longfor.log.factory.bean.BehaviourLogFactory;
import com.longfor.log.factory.bean.LogInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Method;

/**
 * Created by jiazhen on 2018/4/28.
 * Desc:
 */
@Aspect
public class LightAppAspectJ {

    private static final String TAG = LightAppAspectJ.class.getSimpleName();

    BehaviourLogFactory mFactoryShow;
    LogInfo mBehaviourShowLog; // 界面展示时长日志
    BehaviourLogFactory mFactoryLive;
    LogInfo mBehaviourLiveLog; // 界面存活时间日志


    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d(TAG, "onActivityMethodBefore: " + key);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Before("execution(@com.bj.lightapp.aspectj.ClickTrack * *(..))")
    public void onItemClick(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg!=null) {
                String clickLabel = ((ClickBean) arg).getClickLabel();
                Log.e(TAG,"onItemClick: app_id = "+clickLabel);
            }
        }



        Class declaringType = signature.getDeclaringType();
        Method[] declaredMethods = declaringType.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            ClickTrack annotation = declaredMethod.getAnnotation(ClickTrack.class);
            if (annotation!=null){
                Log.e(TAG,"onItemClick: id = "+annotation.id());
            }
        }
    }
    //android.support.v4.app.Fragment
    @Before("execution(* android.support.v4.app.Fragment.onResume(..))")
    public void onFragmentMethodOnResumeBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        mFactoryShow = new BehaviourLogFactory();
        mBehaviourShowLog = mFactoryShow.create();
        Log.d(TAG, "onResume: sum = ");
    }

    @Before("execution(* android.support.v4.app.Fragment.onPause(..))")
    public void onFragmentMethodOnPauseBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        mBehaviourShowLog.seteTime(System.currentTimeMillis());
        mFactoryShow.save(BaseApplication.i());
        Log.d(TAG, "onPause: sum = ");
    }
}
