package com.bj.lightapp.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AsyncAspect {

    @Around("onAsyncMethod()")
    public void doAsyncMethod(ProceedingJoinPoint joinPoint){
        asyncMethod(joinPoint);
    }

    @Pointcut("@annotation(com.bj.lightapp.aspectj.Async)")
    public void onAsyncMethod() {
    }

    private void asyncMethod(final ProceedingJoinPoint joinPoint) {
       /* try {
            Log.e(AsyncAspect.class.getSimpleName(),"当前执行线程:"+Thread.currentThread().getName());
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
        /*Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) {
                Looper.prepare();
                try {
                    Log.e(AsyncAspect.class.getSimpleName(),"当前执行线程:"+Thread.currentThread().getName());
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                Looper.loop();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
*/
    }
}
