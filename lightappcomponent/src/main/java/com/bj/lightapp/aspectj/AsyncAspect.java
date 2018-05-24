package com.bj.lightapp.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Aspect
public class AsyncAspect {


    @Pointcut("execution(@com.bj.lightapp.aspectj.Async * *(..))")
    public void onAsyncMethod() {
    }
    @Around("onAsyncMethod()")
    public void asyncMethod(final ProceedingJoinPoint joinPoint) {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) {
                try {
                    Log.e(AsyncAspect.class.getSimpleName(),"当前执行线程:"+Thread.currentThread().getName());
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
