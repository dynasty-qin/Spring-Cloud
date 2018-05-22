package com.harry.annotations;

import java.lang.annotation.*;

/**
 * 统计方法执行时长
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecTime {
}
