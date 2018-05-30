package com.example.demo.advices;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : 自定义InputMessage
 */
public class DecodeHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    private static final Logger log = LoggerFactory.getLogger(DecodeHttpInputMessage.class);

    public DecodeHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
        this.headers = inputMessage.getHeaders();
        InputStream body = inputMessage.getBody();
        String bytes = IOUtils.toString(body, "UTF-8");
        log.info("RequestBody : " + bytes);
//        System.out.println("DecodeHttpInputMessage : " + bytes);
        this.body = new ByteArrayInputStream(bytes.getBytes("utf-8"));
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    @Override
    public InputStream getBody() {
        return body;
    }
}
