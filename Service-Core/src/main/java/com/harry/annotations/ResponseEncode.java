package com.harry.annotations;

import java.lang.annotation.*;

/**
 * 响应内容处理标记
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseEncode {
}
