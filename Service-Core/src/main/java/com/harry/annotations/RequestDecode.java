package com.harry.annotations;

import java.lang.annotation.*;

/**
 * 请求体解密
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestDecode {

    boolean isDecode() default true;
}
