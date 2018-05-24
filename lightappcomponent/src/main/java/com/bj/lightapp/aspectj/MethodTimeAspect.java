package com.bj.lightapp.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

@Aspect
public class MethodTimeAspect {
    private String TAG = MethodTimeAspect.class.getSimpleName();
   /* //带有DebugLog注解的所有类
    @Pointcut("within(@com.example.aoplib.DebugLog *)")
    public void withinAnnotatedClass() {}

    //在带有DebugLog注解的所有类，除去synthetic修饰的方法
    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotatedType() {}

    //在带有DebugLog注解的所有类，除去synthetic修饰的构造方法
    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType() {}*/

    //在带有DebugLog注解的方法
    @Pointcut("execution(@com.bj.lightapp.aspectj.DebugLog * *(..))")
    public void method() {}

  /* //在带有DebugLog注解的构造方法
    @Pointcut("execution(@com.example.aoplib.DebugLog *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {}
*/
    @Around("method()")
    public void logAndExecute(final ProceedingJoinPoint joinPoint) throws Throwable {
        //执行方法前，做些什么
        //enterMethod(joinPoint);

        long startNanos = System.nanoTime();
        //执行原方法
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);
        //执行方法后，做些什么
        //exitMethod(joinPoint, result, lengthMillis);
        Log.e(TAG,"HAHAH");


    }

}

