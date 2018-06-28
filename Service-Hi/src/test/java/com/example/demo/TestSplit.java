package com.example.demo;

/**
 * @Author : Harry
 * @Date :  2018-06-20 09:31
 * @Description :
 */
public class TestSplit {

    public static void main(String[] args) {
        String a = "123.551";
        String[] split = a.split("\\.");
        System.out.println(split[0]);
    }
}
