package com.king.li.used;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 选择排序-时间复杂度为 O(n^2)

 * @author li
 * @create 2021-05-27-11:41
 */
public class SelectionSort {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndSet(2);
        int[] nums = {23, 432, 21, 1, 25, 69, 39};
        for (int i = 0; i < nums.length; i++) {
            int index = getSmallestNum(nums,i); //最小数下标
            //交换最小数和当前数的位置
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
        for (int newNum : nums) {
            System.out.println(newNum);
        }
    }

    /**
     * 获取最小数下标
     * @param nums
     * @param startIndex
     * @return
     */
    public static int getSmallestNum(int[] nums,int startIndex) {
        int temp = nums[startIndex];
        int index = startIndex;
        for(int i = startIndex;i < nums.length - startIndex;i++){
            if (nums[i] < temp) {
                temp = nums[i];
                index = i;
            }
        }
        return index;
    }
}
