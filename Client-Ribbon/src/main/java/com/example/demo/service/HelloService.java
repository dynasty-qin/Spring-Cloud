package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    public String hiService(String name){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://SERVICE-HI/hi", String.class, name);

        forEntity.getBody();
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name,String.class);
    }
}
