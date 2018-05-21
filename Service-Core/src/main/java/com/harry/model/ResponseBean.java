package com.harry.model;

import lombok.Data;
import java.io.Serializable;


/**
 * 统一封装返回数据格式
 * @author Harry
 *
 */
@Data
public class ResponseBean implements Serializable{

    public static String SUCCESS_CODE = "0";

    public static String FAIL_CODE = "999999";

    public static String OK = "ok";

    public static String FAIL = "fail";

    private Meta meta;
    private Object data;

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

    @Data
    public static class Meta implements Serializable{

        /**
         * 错误编码
         */
        private String code;

        /**
         * 消息
         */
        private String message;

        public Meta() {
        }

        public Meta(String code) {
            this.code = code;
        }

        public Meta(String code, String message) {
            this.code = code;
            this.message = message;
        }

    }

}
