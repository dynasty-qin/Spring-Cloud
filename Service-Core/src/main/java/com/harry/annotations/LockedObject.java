package com.harry.annotations;

import java.lang.annotation.*;

/**
 * @Author : Harry
 * @Date : 2018-06-27 17:14
 * @Description : 加锁的对象字段
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
}
