package com.harry.annotations;

import java.lang.annotation.*;

/**
 * @Author : Harry
 * @Date :  2018-06-27 17:16
 * @Description : 用于注解自定义类型的参数
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    String field() default "";// 含有成员变量的复杂对象中需要加锁的成员变量
}
