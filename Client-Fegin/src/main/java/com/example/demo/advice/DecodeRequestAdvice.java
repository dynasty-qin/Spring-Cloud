package com.example.demo.advice;

import com.harry.annotations.RequestDecode;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : 针对GET,POST请求的处理
 */
@ControllerAdvice
public class DecodeRequestAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {

        return methodParameter.getMethodAnnotation(RequestDecode.class) != null;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {

        RequestDecode methodAnnotation = methodParameter.getMethodAnnotation(RequestDecode.class);
        if (methodAnnotation.isDecode()){
            try {
                return new DecodeHttpInputMessage(httpInputMessage);
            } catch (Exception e) {
                e.printStackTrace();
                return httpInputMessage;
            }
        }else{
            return httpInputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
}
