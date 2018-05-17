package com.example.demo.controller;

import com.example.demo.service.SchedualServiceHi;
import com.harry.model.ResponseBean;
import com.harry.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/hi")
public class HiController {

    @Autowired
    private SchedualServiceHi serviceHi;

    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    public String hi(@RequestParam String name){

        return serviceHi.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public ResponseBean addUser(@RequestBody User user){
        return serviceHi.addUser(user);
    }

}
