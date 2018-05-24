package com.example.demo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : Harry
 * @Date :  2018-05-24 10:11
 * @Description :
 */
@Data
@Component
@ConfigurationProperties("demo")
public class DemoProperty {

    private String name;
}
