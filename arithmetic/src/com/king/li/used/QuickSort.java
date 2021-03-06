package com.king.li.used;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 快速排序-时间复杂度O(nlogN)
 *
 * @author li
 * @create 2021-05-27-18:01
 */
public class QuickSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(5);
        list.add(3);
//        System.out.println(quickSort(list));

        int[] s = new int[]{5, 9, 4, 2, 1, 6, 3, 8, 7, 0};
        quickSort(s, 0, s.length - 1);
        System.out.println(Arrays.toString(s));

    }

    public static List<Integer> quickSort(List<Integer> num) {
        List<Integer> result = new ArrayList<>();
        if (num.size() <= 1) {
            return num;
        }
        int base = num.get(0);
        List<Integer> left = new ArrayList<>(); //小于等于基数的列表
        List<Integer> right = new ArrayList<>(); //大于基数的列表
        for (int i = 1; i < num.size(); i++) {
            if (num.get(i) <= base) {
                left.add(num.get(i));
            } else {
                right.add(num.get(i));
            }
        }
        result.addAll(quickSort(left));
        result.add(base);
        result.addAll(quickSort(right));
        return result;
    }

    public static void quickSort(int s[], int l, int r) {
        if (l < r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x)
                    j--;
                if (i < j)
                    s[i++] = s[j];
                while (i < j && s[i] < x)
                    i++;
                if (i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quickSort(s, l, i - 1);
            quickSort(s, i + 1, r);
        }
    }
}
