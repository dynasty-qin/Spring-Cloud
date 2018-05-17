package com.example.demo.annotations;

import com.example.demo.enums.FieldFormatEnum;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldFormat {

    FieldFormatEnum value();
}
