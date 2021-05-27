package com.king.li.used;

/**
 * 二分法查询-时间复杂度 O(logN)
 * 输入的数组必须是有序的
 *
 * @author li
 * @create 2021-05-27-9:34
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {0,3,7,30,44,54,67,80};
        System.out.println("该元素索引为" + search(nums,44));
    }

    public static Integer search(int[] nums, int num) {
        int low = 0;
        int high = nums.length - 1;
        int executes = 0; //执行次数
        while(low <= high){
            int mid = (low + high) / 2;
            System.out.println("执行次数为" + ++executes);
            if (nums[mid] == num) {
                return mid;
            }else if(nums[mid] > num){
                high = mid -1;
            }else if(nums[mid] < num){
                low = mid + 1;
            }
        }
        return null;
    }
}
