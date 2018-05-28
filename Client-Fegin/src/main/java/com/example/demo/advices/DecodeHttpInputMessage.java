package com.example.demo.advices;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DecodeHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public DecodeHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
        this.headers = inputMessage.getHeaders();
        InputStream body = inputMessage.getBody();
        String bytes = IOUtils.toString(body, "UTF-8");
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
