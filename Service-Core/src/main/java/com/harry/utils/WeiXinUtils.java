package com.harry.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import com.harry.constant.WeiXinConstants;
import com.harry.model.AccessToken;
import com.harry.model.MyX509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Harry
 * @Description 微信网页授权工具类
 * @date  2017年7月11日
 */
public class WeiXinUtils {

		// 获取access_token的接口地址（GET） 限200（次/天）
		public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	    private static Logger log = LoggerFactory.getLogger(WeiXinUtils.class);

		/**
		 * 获取access_token
		 * @param appid 凭证
		 * @param appsecret 密钥
		 * @return
		 */
		public static AccessToken getAccessToken(String appid, String appsecret) {
			
			AccessToken accessToken = null;

			String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
				} catch (JSONException e) {
					accessToken = null;
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
				}
			}
			return accessToken;
		}

		/**
		 * 发起https请求并获取结果
		 * 
		 * @param requestUrl 请求地址
		 * @param requestMethod 请求方式（GET、POST）
		 * @param outputStr 提交的数据
		 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
		 */
		public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
			JSONObject jsonObject = null;
			StringBuffer buffer = new StringBuffer();
			try {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				URL url = new URL(requestUrl);
				HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
				httpUrlConn.setSSLSocketFactory(ssf);

				httpUrlConn.setDoOutput(true);
				httpUrlConn.setDoInput(true);
				httpUrlConn.setUseCaches(false);
				// 设置请求方式（GET/POST）
				httpUrlConn.setRequestMethod(requestMethod);

				if ("GET".equalsIgnoreCase(requestMethod))
					httpUrlConn.connect();

				// 当有数据需要提交时
				if (null != outputStr) {
					OutputStream outputStream = httpUrlConn.getOutputStream();
					// 注意编码格式，防止中文乱码
					outputStream.write(outputStr.getBytes("UTF-8"));
					outputStream.close();
				}

				// 将返回的输入流转换成字符串
				InputStream inputStream = httpUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				// 释放资源
				inputStream.close();
				inputStream = null;
				httpUrlConn.disconnect();
				jsonObject = JSONObject.parseObject(buffer.toString());
			} catch (ConnectException ce) {
				log.error("Weixin server connection timed out.");
			} catch (Exception e) {
				log.error("https request error:{}", e);
			}
			return jsonObject;
		}
		
		/**
		 * 通过code获取openid
		 * @param code
		 * @return
		 */
		public static String getOpenId(String code){
			
			String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			String replace = url.replace("APPID", WeiXinConstants.WEI_XIN_APP_ID).replace("SECRET", WeiXinConstants.WEI_XIN_APP_SECRET).replace("CODE", code);
			// 发送请求
			JSONObject jsonObject = httpRequest(replace, "GET", "");
			System.out.println(jsonObject+"++++++++++++++++++++++++++++++++++++++");
			
			return jsonObject.getString("openid");
		}
		
		 /**
	     * 发送模板消息
	     * appId 公众账号的唯一标识
	     * appSecret 公众账号的密钥
	     * openId 用户标识
	     */
	   /* public static void send_template_message(String appId, String appSecret,WxTemplate temp) {
	    	// 获取access_token
	    	String access_token = ThreadToken.accessToken.getToken();
	        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
	        String jsonString = JSONObject.toJSONString(temp);
	        JSONObject httpRequest = httpRequest(url, "POST", jsonString);
	        System.out.println(httpRequest);
	    }*/
	    
	    /**
		 * 获取微信授权code的url
		 */
	    public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	    
	    /**
	     * 生成获取code的url
	     * @return
	     */
	    public static String getCodeRequest(){

	        String result = null;
	        
	        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(WeiXinConstants.WEI_XIN_APP_ID));

	        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8("http://www.7htr.com/qhtr/"));

	        GetCodeRequest = GetCodeRequest.replace("SCOPE", "snsapi_base");

	        result = GetCodeRequest;

	        return result;
	    }
	    
	    /**
	     * 转码成utf8
	     * @param str
	     * @return
	     */
	    public static String urlEnodeUTF8(String str){

	        String result = str;

	        try {

	            result = URLEncoder.encode(str,"UTF-8");

	        } catch (Exception e) {

	            e.printStackTrace();
	        }

	        return result;
	}
}
