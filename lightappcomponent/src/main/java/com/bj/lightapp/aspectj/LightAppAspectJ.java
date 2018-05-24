package com.bj.lightapp.aspectj;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

import com.bj.componentlib.base.BaseApplication;
import com.longfor.log.factory.bean.BehaviourLogFactory;
import com.longfor.log.factory.bean.LogInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Method;
import java.util.Stack;

/**
 * Created by jiazhen on 2018/4/28.
 * Desc:
 */
@Aspect
public class LightAppAspectJ {

    private static final String TAG = LightAppAspectJ.class.getSimpleName();

    BehaviourLogFactory mFactoryShow;
    LogInfo mBehaviourShowLog; // 界面展示时长日志

    private Stack<Long> mActivityCreateTime = new Stack<>();


    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        //String key = joinPoint.getSignature().toString();
        //Log.d(TAG, "onActivityMethodBefore: " + key);
    }

    @Before("execution(* android.app.Activity.onCreate(..))")
    public void onActivityOnCreateBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        if (!TextUtils.isEmpty(key) && key.contains("BaseActivity"))return;
        mActivityCreateTime.push(System.currentTimeMillis());
        Log.e(TAG, "" + key);
        //Log.d(TAG, "onActivityMethodBefore: onCreate "+mFactoryLive );
    }

    @Before("execution(* android.app.Activity.onDestroy(..))")
    public void onActivityOnDestroyBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        if (!TextUtils.isEmpty(key) && key.contains("BaseActivity"))return;
        String declaringTypeName = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Long startTime = mActivityCreateTime.pop();
        BehaviourLogFactory behaviourLogFactory = new BehaviourLogFactory();
        LogInfo logInfo = behaviourLogFactory.create();
        logInfo.setEventId(declaringTypeName);
        logInfo.setEventTag("live");
        logInfo.setsTime(startTime);
        logInfo.seteTime(System.currentTimeMillis());
        behaviourLogFactory.save(BaseApplication.i());
        Log.e(TAG, "" + declaringTypeName+" 存活:"+(logInfo.geteTime()-logInfo.getsTime())+"毫秒");
        //Log.d(TAG, "onActivityMethodBefore: onDestroy "+mFactoryLive);
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
        Log.d(TAG, "onResume: sum = "+mFactoryShow);
    }

    @Before("execution(* android.support.v4.app.Fragment.onPause(..))")
    public void onFragmentMethodOnPauseBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        mBehaviourShowLog.seteTime(System.currentTimeMillis());
        mFactoryShow.save(BaseApplication.i());
        Log.d(TAG, "onPause: sum = "+mFactoryShow);
    }
}
