package com.harry.model;

import java.io.Serializable;


/**
 * 统一封装返回数据格式
 * @author Harry
 *
 */
public class ResponseBean implements Serializable{
    

    public static String SUCCESS_CODE = "0";

    public static String FAIL_CODE = "999999";

    public static String OK = "ok";

    public static String FAIL = "fail";

    private Meta meta;
    private Object data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseBean success() {
        this.meta = new Meta(SUCCESS_CODE, OK);
        return this;
    }
    

    public ResponseBean success(String code, String message) {
        this.meta = new Meta(code, message);
        return this;
    }

    public ResponseBean success(Object data) {
        this.meta = new Meta(SUCCESS_CODE, OK);
        this.data = data;
        return this;
    }
    
    public ResponseBean fail() {
        this.meta = new Meta(FAIL_CODE, FAIL);
        return this;
    }

    public ResponseBean fail(String message) {
        this.meta = new Meta(FAIL_CODE, message);
        return this;
    }

    public ResponseBean fail(String code, String message) {
        this.meta = new Meta(code, message);
        return this;
    }

    public class Meta implements Serializable{

        public Meta(String code) {
            this.code = code;
        }

        public Meta(String code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * 错误编码
         */
        private String code;

        /**
         * 消息
         */
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer("Meta:[ code :");
            sb.append(this.code);
            sb.append(" , message: ");
            sb.append(this.message);
            sb.append(" ]");
            return sb.toString();
        }
    }
    
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("响应参数： ResponseBean:[ ");
        sb.append(this.meta.toString());
        sb.append(", data: ");
        sb.append(this.data);
        sb.append(" ]");
        return sb.toString();
    }

}
