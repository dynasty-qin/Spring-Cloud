package com.example.demo.exception;

/**
 * @Author : Harry
 * @Date :  2018-06-27 17:27
 * @Description : 分布式锁自定义异常
 */
public class CacheLockException extends Exception {

    private String message;

    public CacheLockException(String message) {
        super(message);
    }
}
