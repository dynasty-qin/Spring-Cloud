package com.example.demo;

import com.harry.utils.XMLUtil;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.Map;

/**
 * @Author : Harry
 * @Date :  2018-05-29 17:02
 * @Description :
 */
public class XmlTest {

    public static void main(String[] args) throws IOException, XmlPullParserException {

        String s = "<xml><ToUserName><![CDATA[gh_adab5f4e931b]]></ToUserName>" +
                "<FromUserName><![CDATA[oF_rQ0fDBYCCsVRmOwFGdukEw9YE]]></FromUserName>" +
                "<CreateTime>1527582085</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[哈]]></Content>" +
                "<MsgId>6560915097477312507</MsgId>" +
                "</xml>";
        Map<String, String> xmlParse = XMLUtil.xmlParse(s);
        xmlParse.forEach((a,b) -> System.out.println(a + " : " + b));
    }
}
