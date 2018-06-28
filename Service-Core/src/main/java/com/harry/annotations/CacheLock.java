package com.harry.annotations;

import java.lang.annotation.*;

/**
 * @Author : Harry
 * @Date : 2018-06-27 17:09
 * @Description : 标注加锁的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    String lockPrefix() default "";// redis 锁key前缀

    long timeOut() default 2000;// 轮询锁的时间

    int expireTime() default 1000;// key在redis里存在时间
}
