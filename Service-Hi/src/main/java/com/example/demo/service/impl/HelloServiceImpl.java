package com.example.demo.service.impl;

import com.example.demo.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService{

    public String sayHello(String name){
        return "hello, " + name + " !";
    }

}
