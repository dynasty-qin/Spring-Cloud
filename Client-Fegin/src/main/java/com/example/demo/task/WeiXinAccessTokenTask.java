package com.example.demo.task;

import com.harry.model.AccessToken;
import com.harry.utils.WeiXinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author : Harry
 * @Date :  2018-05-30 09:48
 * @Description : 获取微信AccessToken定时任务
 */
@Component
public class WeiXinAccessTokenTask {

    @Value("${weixin.appId}")
    private String appId;

    @Value("${weixin.appSecret}")
    private String appSecret;

    @Autowired
    private RedisTokenHelper tokenHelper;

    private static final Logger log = LoggerFactory.getLogger(WeiXinAccessTokenTask.class);

    private AccessToken accessToken;

    @Scheduled(initialDelay = 1000, fixedDelay = 7000*1000)
    public void getAccessToken(){

        accessToken = WeiXinUtils.getAccessToken(appId, appSecret);
        tokenHelper.saveObject("access_token",accessToken.getToken());
        log.info("Get WeiXin AccessToken success : " + accessToken.getToken());
    }
}
