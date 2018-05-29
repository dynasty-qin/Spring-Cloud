package com.harry.model;

import com.harry.annotations.FieldFormat;
import com.harry.enums.FieldFormatEnum;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private String password;

    @FieldFormat(value = FieldFormatEnum.MONEY)
    private BigDecimal money;

    @FieldFormat(value = FieldFormatEnum.SCALE)
    private BigDecimal scale;

    @FieldFormat(value = FieldFormatEnum.DATE)
    private Date birthday;
}