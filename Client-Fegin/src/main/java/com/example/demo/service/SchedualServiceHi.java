package com.example.demo.service;

import com.example.demo.fallback.SchedualSericeHiFallBackFactory;
import com.harry.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Harry
 * @Date :  2018-05-30 10:09
 * @Description : Fegin 调用方式(业务层)
 */
@Component
@FeignClient(value = "service-hi",fallbackFactory = SchedualSericeHiFallBackFactory.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/user/second",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
    User getUser(@PathVariable(value = "userId") Integer userId);
}
