package com.harry.annotations;

import com.harry.enums.FieldFormatEnum;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldFormat {

    FieldFormatEnum value();
}
