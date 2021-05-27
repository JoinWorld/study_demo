package com.king.li.used;

/**
 * 求和 - 递归
 * @author li
 * @create 2021-05-27-16:44
 */
public class Sum {
    public static void main(String[] args) {
        System.out.println(sum(new int[]{1,34,43,54,43},0));
    }

    public static int sum(int[] num,int index){
        if (index >= num.length-1)
            return num[index];
        return num[index] += sum(num,++index);
    }
}
