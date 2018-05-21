package com.example.demo.fallback;

import com.example.demo.service.SchedualServiceHi;
import com.harry.model.ResponseBean;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiFallBack implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {

        return "Sorry " + name + " ,Something is wrong.";
    }

    @Override
    public ResponseBean getUser(Integer id) {
        return new ResponseBean().fail("Failed !");
    }
}
