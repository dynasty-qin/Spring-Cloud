package com.harry.annotations;

import java.lang.annotation.*;

/**
 * 相应内容标记
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseEncode {
}
