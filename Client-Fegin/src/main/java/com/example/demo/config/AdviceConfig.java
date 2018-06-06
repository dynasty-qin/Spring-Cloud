package com.example.demo.config;

import com.example.demo.advice.DecodeRequestAdvice;
import com.example.demo.advice.MyResponseBodyAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import java.util.Arrays;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : Advice配置
 */
@Configuration
@EnableWebMvc
public class AdviceConfig extends DelegatingWebMvcConfiguration{

    @Autowired
    private DecodeRequestAdvice requestAdvice;
    @Autowired
    private MyResponseBodyAdvice responseEncode;

    @Override
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = super.requestMappingHandlerAdapter();
        requestMappingHandlerAdapter.setRequestBodyAdvice(Arrays.asList(requestAdvice));
        requestMappingHandlerAdapter.setResponseBodyAdvice(Arrays.asList(responseEncode));
        return requestMappingHandlerAdapter;
    }

}
