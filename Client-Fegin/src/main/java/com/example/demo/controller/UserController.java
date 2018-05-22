package com.example.demo.controller;

import com.example.demo.service.SchedualServiceHi;
import com.harry.model.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private SchedualServiceHi serviceHi;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseBean getUser(@PathVariable Integer id){
        return serviceHi.getUser(id);
    }
}
