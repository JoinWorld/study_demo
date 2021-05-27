package com.king.li.used;

/**
 * 阶乘 - 递归算法
 * @author li
 * @create 2021-05-27-15:14
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int num){
        if (num <= 1)
            return num;
        num = num * factorial(num - 1);
        return num;
    }
}
