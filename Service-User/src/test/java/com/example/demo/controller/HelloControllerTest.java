package com.example.demo.controller;

import com.harry.model.User;
import com.example.demo.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void sayHello() {
        User user = helloService.getUser(1);
        System.out.println(user.getName());
    }

    @Test
    public void addUser() {
    }

    @Test
    public void getDemoName() {
    }
}