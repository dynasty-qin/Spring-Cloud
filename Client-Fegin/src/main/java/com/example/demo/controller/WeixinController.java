package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.task.RedisTokenHelper;
import com.harry.annotations.ExecTime;
import com.harry.annotations.RequestDecode;
import com.harry.model.InputText;
import com.harry.model.Perception;
import com.harry.model.ResponseBean;
import com.harry.model.UserInfo;
import com.harry.utils.HttpUtils;
import com.harry.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Harry
 * @Date :  2018-05-29 14:58
 * @Description : 微信服务器验证,自动回复,获取AccessToken
 */
@RestController
@RequestMapping(value = "weixin")
public class WeixinController {

    public static final String TULING_API_HOST = "http://openapi.tuling123.com";
    public static final String TULING_API_PATH = "/openapi/api/v2";

    @Autowired
    private RedisTokenHelper tokenHelper;

    @ExecTime
    @GetMapping(value = "check")
    public String check(@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr){
        return echostr;
    }

    @ExecTime
    @PostMapping(value = "check")
    @RequestDecode
    public String message(@RequestBody String s) throws IOException, XmlPullParserException {

        if(s == null || "".equals(s)){

            return null;
        }
        Map<String, String> xmlParse = XMLUtil.xmlParse(s);

        String content = xmlParse.get("Content");

        String event = xmlParse.get("Event");// subscribe : 订阅, unsubscribe : 取消订阅
        String toUserName = xmlParse.get("ToUserName");
        String fromUserName = xmlParse.get("FromUserName");
        String msgType = xmlParse.get("MsgType");
        Date date = new Date();
        if (event != null && !"".equals(event)){

            String msg;
            if ("subscribe".equals(event)){
                msg = "Welcome To Harry's Official Account !";
            }else if ("unsubscribe".equals(event)){
                msg = fromUserName + " unsubscribe !";
            }else {
                return "";
            }
            String a = "<xml>" +
                    "<ToUserName>" + fromUserName + "</ToUserName>" +
                    "<FromUserName>" + toUserName + "</FromUserName>" +
                    "<CreateTime>" + date.getTime() + "</CreateTime>" +
                    "<MsgType>text</MsgType>" +
                    "<Content>" + new String(msg.getBytes(),"iso8859-1") + "</Content>" +
                    "</xml>";

            return a;
        }
        String msgContent;
        if ("%E6%88%91%E7%88%B1%E4%BD%95%E7%91%9E".equals(URLEncoder.encode(content))){
            msgContent = URLDecoder.decode("%E4%BD%95%E7%91%9E%E4%B9%9F%E7%88%B1%E4%BD%A0%E5%91%A6%2C%E5%B0%8F%E5%82%BB%E5%86%B0%2C%E4%B9%88%E4%B9%88%E5%93%92+%7E");
        }else{
            content = content.trim();
            if (content == null || "".equals(content)) {
                msgContent = "What are you talking about ?";
            }else{
                msgContent = sendRequest(content) == null ? "对不起,我不太懂这个 ~" : sendRequest(content);
            }
        }
        String a = "<xml>" +
                "<ToUserName>" + fromUserName + "</ToUserName>" +
                "<FromUserName>" + toUserName + "</FromUserName>" +
                "<CreateTime>" + date.getTime() + "</CreateTime>" +
                "<MsgType>" + msgType + "</MsgType>" +
                "<Content>" + new String(msgContent.getBytes(),"iso8859-1") + "</Content>" +
                "</xml>";
        return a;
    }

    private String sendRequest(String info){

        Map<String,Object> bodys = new HashMap<>();

        Perception perception = new Perception();

        InputText inputText = new InputText();
        inputText.setText(info);
        perception.setInputText(inputText);
        UserInfo userInfo = new UserInfo();
        bodys.put("perception",perception);
        bodys.put("userInfo",userInfo);
        String s = JSONObject.toJSONString(bodys);

        String post = HttpUtils.post(TULING_API_HOST + TULING_API_PATH, s);
        JSONObject parse = (JSONObject) JSONObject.parse(post);
        JSONArray results = (JSONArray) parse.get("results");
        JSONObject o = (JSONObject) results.get(0);
        JSONObject values = (JSONObject) o.get("values");
        String text = (String) values.get("text");
        return text;
    }

    @GetMapping(value = "accessToken",produces = "application/json")
    public ResponseBean getAccessToken(){

        Map<String,Object> map = new HashMap<>();
        map.put("AccessToken",tokenHelper.getObject("access_token"));

        return new ResponseBean().success(map);
    }
}
