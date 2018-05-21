package com.example.demo.service;

import com.example.demo.fallback.SchedualSericeHiFallBackFactory;
import com.harry.model.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-hi",fallbackFactory = SchedualSericeHiFallBackFactory.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/user/first",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    ResponseBean getUser(@PathVariable Integer id);
}
