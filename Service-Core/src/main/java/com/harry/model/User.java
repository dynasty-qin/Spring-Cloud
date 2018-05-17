package com.harry.model;

import com.harry.annotations.FieldFormat;
import com.harry.enums.FieldFormatEnum;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class User implements Serializable{

    private String name;

    private Integer age;

    @FieldFormat(value = FieldFormatEnum.MONEY)
    private BigDecimal money;

    @FieldFormat(value = FieldFormatEnum.SCALE)
    private BigDecimal scale;

    @FieldFormat(value = FieldFormatEnum.DATE)
    private Date birthday;


}
