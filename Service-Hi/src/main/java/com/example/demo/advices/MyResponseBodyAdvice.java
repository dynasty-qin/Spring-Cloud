package com.example.demo.advices;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.filters.FieldFormatFilter;
import com.harry.model.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice{

    private static final Logger log = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof ResponseBean) {
            o = (ResponseBean) o;
            Object data = ((ResponseBean) o).getData();
            String jsonString = JSON.toJSONString(data,new FieldFormatFilter());
            Object parse = JSONObject.parse(jsonString);
            ((ResponseBean) o).setData(parse);
        }
        return o;
    }
}
