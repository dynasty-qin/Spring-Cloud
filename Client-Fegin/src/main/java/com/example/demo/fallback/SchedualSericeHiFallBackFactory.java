package com.example.demo.fallback;

import com.harry.model.User;
import com.example.demo.client.SchedualServiceHi;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author : Harry
 * @Date :  2018-05-17 14:22
 * @Description : 断路器
 */
@Component
public class SchedualSericeHiFallBackFactory implements FallbackFactory<SchedualServiceHi> {

    private static final Logger log = LoggerFactory.getLogger(SchedualSericeHiFallBackFactory.class);

    @Override
    public SchedualServiceHi create(final Throwable throwable) {

        return new SchedualServiceHi() {
            @Override
            public String sayHiFromClientOne(String name) {

                log.error(throwable.getMessage());
                return "failed !";
            }

            @Override
            public User getUser(Integer id) {
                log.error(throwable.getMessage());
                return new User();
            }
        };
    }
}
