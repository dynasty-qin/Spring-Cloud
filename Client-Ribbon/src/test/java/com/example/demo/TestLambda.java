package com.example.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @Author : Harry
 * @Date :  2018-05-17 16:43
 * @Description :
 */
public class TestLambda {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,7,8,78,30);

        int stats = nums
                .stream()
                .mapToInt(x -> x)
                .reduce(0, (x,y) -> x + y);
        System.out.println(stats);

        long count = nums.stream().mapToInt(x -> x).sum();
        System.out.println(count);
        /*System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());*/
    }
}
