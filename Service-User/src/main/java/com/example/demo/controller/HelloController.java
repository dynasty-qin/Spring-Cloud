package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.property.DemoProperty;
import com.example.demo.service.HelloService;
import com.harry.annotations.ExecTime;
import com.harry.annotations.RequestDecode;
import com.harry.model.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class HelloController {

    @Resource
    private HelloService helloService;
    @Autowired
    private DemoProperty demoProperty;

    @RequestMapping(value = "/{userId}",produces = "application/json",method = RequestMethod.GET)
    public ResponseBean sayHello(@PathVariable Integer userId){

        return new ResponseBean().success(helloService.getUser(userId));
    }

    @ExecTime
    @RequestDecode
    @RequestMapping(value = "/second",produces="application/json",method = RequestMethod.POST)
    public ResponseBean addUser(@RequestBody User user){

        User user1 = new User();

        user1.setName("tom");
        user1.setId(3);
//        user1.setAge(10);
        user1.setMoney(new BigDecimal("0.01005464321000"));
        user1.setScale(new BigDecimal("0.154600065400"));
        user1.setBirthday(new Date());

        return new ResponseBean().success(user1);
    }

    @ExecTime
    @RequestDecode
    @GetMapping(value = "/demo",produces = "application/json")
    public ResponseBean getDemoName(){
        return new ResponseBean().success(demoProperty.getName());
    }
}