package com.example.demo.fallback;

import com.example.demo.service.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiFallBack implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {

        return "Sorry " + name + " ,Something is wrong.";
    }
}
