package com.example.demo.aspect;

import com.example.demo.exception.CacheLockException;
import com.example.demo.lock.RedisLock;
import com.harry.annotations.CacheLock;
import com.harry.annotations.LockedComplexObject;
import com.harry.annotations.LockedObject;
import com.harry.model.ResponseBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author : Harry
 * @Date :  2018-06-28 09:29
 * @Description : 分布式锁aop
 */
@Aspect
@Component
public class CacheLockAspect {

    public static int ERROR_COUNT = 0;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 切面
     */
    @Pointcut("@annotation(com.harry.annotations.CacheLock)")
    public void cacheLock(){
    }

    @Around("cacheLock()")
    public Object around(ProceedingJoinPoint joinPoint){

        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
        Method method = null;
        if (declaredMethods != null && declaredMethods.length != 0){
            for (Method declaredMethod:declaredMethods) {
                if (name.equals(declaredMethod.getName())){
                    method = declaredMethod;
                }
            }
        }
        CacheLock annotation = method.getAnnotation(CacheLock.class);
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();
        RedisLock lock = null;
        try {
            Object lockedObject = getLockedObject(parameterAnnotations, args);
            String s = lockedObject.toString();
            // 新建锁
            lock = new RedisLock(annotation.lockPrefix(),s,redisTemplate);
            boolean lock1 = lock.lock(annotation.timeOut(), annotation.expireTime());
            System.out.println(lock1 ? "加锁成功 !" : "加锁失败 !");
            if (! lock1){
                ERROR_COUNT += 1;
                throw new CacheLockException("获取锁失败 !");
            }
            return joinPoint.proceed();
        } catch (CacheLockException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            if (lock != null){
                try {
                    lock.unlock();
                } catch (CacheLockException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseBean().fail("请稍后重试 !");
    }

    private Object getLockedObject(Annotation[][] annotations,Object[] args) throws CacheLockException {

        if (annotations == null || annotations.length == 0){
            throw new CacheLockException("没有被注解的参数 !");
        }
        if (args == null || args.length == 0){
            throw new CacheLockException("方法参数为空 !");
        }
        int index = -1;
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof LockedComplexObject){
                    LockedComplexObject lockedComplexObject = (LockedComplexObject) annotations[i][j];
                    index = i;
                    try {
                        return args[i].getClass().getDeclaredField(lockedComplexObject.field());
                    } catch (NoSuchFieldException e) {
                        throw new CacheLockException("注解对象中没有该属性 : " + (lockedComplexObject.field()));
                    }
                }
                if (annotations[i][j] instanceof LockedObject){
                    index = i;
                    break;
                }
            }
            if (index != -1){
                break;
            }
        }
        if (index == -1){
            throw new CacheLockException("请指定被锁定参数 !");
        }
        return args[index];
    }
}
