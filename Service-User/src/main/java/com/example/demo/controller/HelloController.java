package com.example.demo.controller;

import com.harry.model.User;
import com.example.demo.property.DemoProperty;
import com.example.demo.service.HelloService;
import com.harry.annotations.ExecTime;
import com.harry.annotations.RequestDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping(value = "/user")
public class HelloController {

    @Resource
    private HelloService helloService;
    @Autowired
    private DemoProperty demoProperty;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public User sayHello(@PathVariable Integer userId){

        return helloService.getUser(userId);
    }

    @ExecTime
    @RequestDecode
    @RequestMapping(value = "/second",produces="application/json",method = RequestMethod.POST)
    public User addUser(@RequestBody User user){

        user.setName("tom");
        user.setId(3);
//        user1.setAge(10);
        user.setMoney(new BigDecimal("0.01005464321000"));
        user.setScale(new BigDecimal("0.154600065400"));
        user.setBirthday(new Date());

        return user;
    }

    @ExecTime
    @RequestDecode
    @GetMapping(value = "/demo",produces = "application/json")
    public String getDemoName(){
        return demoProperty.getName();
    }
}
