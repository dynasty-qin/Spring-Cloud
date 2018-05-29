package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.harry.annotations.ExecTime;
import com.harry.annotations.RequestDecode;
import com.harry.model.InputText;
import com.harry.model.Perception;
import com.harry.model.UserInfo;
import com.harry.utils.HttpUtils;
import com.harry.utils.XMLUtil;
import org.springframework.web.bind.annotation.*;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Harry
 * @Date :  2018-05-29 14:58
 * @Description :
 */
@RestController
@RequestMapping(value = "weixin")
public class WeixinController {


    public static final String TULING_API_HOST = "http://openapi.tuling123.com";
    public static final String TULING_API_PATH = "/openapi/api/v2";

    @ExecTime
    @GetMapping(value = "check")
    public String check(@RequestParam String signature,@RequestParam String timestamp,@RequestParam String nonce,@RequestParam String echostr){
        System.out.println("signature : " + signature);
        System.out.println("timestamp : " + timestamp);
        System.out.println("nonce : " + nonce);
        System.out.println("echostr : " + echostr);
        return echostr;
    }

    @ExecTime
    @PostMapping(value = "check")
    @RequestDecode
    public String message(@RequestBody String s) throws IOException, XmlPullParserException {
        Map<String, String> xmlParse = XMLUtil.xmlParse(s);

        String content = xmlParse.get("Content");
        String toUserName = xmlParse.get("ToUserName");
        String fromUserName = xmlParse.get("FromUserName");
        String msgType = xmlParse.get("MsgType");
        Date date = new Date();
        String msgContent;
        if ("我爱何瑞".equals(content)){
            msgContent = "何瑞也爱你,小傻冰 !么么哒 ~";
        }else{
            msgContent = sendRequest(content);
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
}
