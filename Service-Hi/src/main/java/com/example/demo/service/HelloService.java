package com.example.demo.service;

import com.example.demo.model.User;

public interface HelloService {

    String sayHello(String name);

    User getUser(Integer userId);
}
