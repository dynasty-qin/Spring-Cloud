package com.example.demo.service;

import com.example.demo.fallback.SchedualSericeHiFallBackFactory;
import com.harry.model.ResponseBean;
import com.harry.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi",fallbackFactory = SchedualSericeHiFallBackFactory.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hello/first",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/hello/second",method = RequestMethod.POST)
    ResponseBean addUser(@RequestBody User user);
}
