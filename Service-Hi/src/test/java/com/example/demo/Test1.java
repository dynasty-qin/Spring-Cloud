package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : Harry
 * @Date :  2018-06-07 11:29
 * @Description :
 */
public class Test1 {

    public static void main(String[] args) {

        List<String> list = null;
        String collect = list.stream().map(a -> String.valueOf(a)).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
