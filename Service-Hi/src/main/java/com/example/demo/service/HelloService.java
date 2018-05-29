package com.example.demo.service;

import com.harry.model.User;

public interface HelloService {

    String sayHello(String name);

    User getUser(Integer userId);
}
