package com.example.demo.controller;

import com.example.demo.service.SchedualServiceHi;
import com.harry.annotations.ExecTime;
import com.harry.model.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private SchedualServiceHi serviceHi;

    @ExecTime
    @RequestMapping(value = "/{userId}",produces = "application/json",method = RequestMethod.GET)
    public ResponseBean getUser(@PathVariable Integer userId){

        return new ResponseBean().success(serviceHi.getUser(userId));
    }
}
