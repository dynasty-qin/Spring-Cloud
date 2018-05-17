package com.example.demo.model;

import com.example.demo.annotations.FieldFormat;
import com.example.demo.enums.FieldFormatEnum;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {

    private String name;

    private Integer age;

    @FieldFormat(value = FieldFormatEnum.MONEY)
    private BigDecimal money;

    @FieldFormat(value = FieldFormatEnum.SCALE)
    private BigDecimal scale;

    @FieldFormat(value = FieldFormatEnum.DATE)
    private Date birthday;

}
