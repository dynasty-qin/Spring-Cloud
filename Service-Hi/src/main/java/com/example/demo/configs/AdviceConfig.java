package com.example.demo.configs;

import com.example.demo.advices.DecodeRequestAdvice;
import com.example.demo.advices.MyResponseBodyAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import java.util.Arrays;

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
