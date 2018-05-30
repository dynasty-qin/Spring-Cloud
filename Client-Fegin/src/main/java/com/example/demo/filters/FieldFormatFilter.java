package com.example.demo.filters;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.harry.annotations.FieldFormat;
import com.harry.enums.FieldFormatEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : 自定义字段格式化
 */
public class FieldFormatFilter implements ValueFilter {

    private static final Logger logger = LoggerFactory.getLogger(FieldFormatFilter.class);

    @Override
    public Object process(Object o, String key, Object value) {

        Field field;
        try {
            field = o.getClass().getDeclaredField(key);
            if (field.isAnnotationPresent(FieldFormat.class)) {
                FieldFormatEnum formatType = field.getAnnotation(FieldFormat.class).value();
                if (value instanceof BigDecimal) {
                    BigDecimal decimalValue = (BigDecimal) value;
                    switch (formatType) {
                        case MONEY:
                            value = decimalValue.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
                            break;
                        case SCALE:
                            value = decimalValue.stripTrailingZeros().toPlainString();
                            break;
                        default:
                            logger.error("Unsupported formatting type !");
                            break;
                    }
                }else if (value instanceof Date){
                    Date date = (Date) value;
                    switch (formatType){
                        case DATE:
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            value = sdf.format(date);
                        default:
                            break;
                    }
                }
            }
            return value;
        } catch (Exception e) {
            logger.info("Formatting failed ! Reason : " + e.getMessage());
            return value;
        }
    }
}
