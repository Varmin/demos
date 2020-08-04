package com.varmin.project.algorithm;

import com.varmin.project.base.IBase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * created by HYY on 2020/7/8
 * description: 数组相关
 */
public class ArrayRealm implements IBase {
    @Override
    public void run() {
        //删除排序数组中的重复项
        int[] array = {1, 2, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9};
        removeDuplicates(array);
        int[] array2 = {1, 2, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9};
        removeDuplicatesBetter(array2);

        //只出现一次的数字
        int[] array3 = {1, 1, 2, 4, 3, 4, 2, 5, 5};
        singleNumber(array3);

        //两个数组的交集 II
        int[] array4 = {2, 4, 5, 8, 9, 10};
        int[] array5 = {1, 3, 5, 7, 9};
        intersect(array4, array5);

        //两数之和
        int[] array6 = {1, 2, 3, 5, 7, 8, 9};
        twoSum(array6, 12);
        twoSumHash(array6,12);
    }

    //--------------------------两数之和--------------------------
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 思路：
     * 1、遍历nums，计算元素得到target需要的值，再在nums中查找该值（查找时排除元素自己）, O(n2)
     * 2. 一边遍历nums一边查找已经遍历过的hashmap中的值（target - 元素）， O(n)
     */

    /**
     * @param nums   给定数组
     * @param target 目标值
     */
    private int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            for (int i1 = 0; i1 < nums.length; i1++) {
                if (i != i1 && t == nums[i1]) {
                    System.out.println("ArrayRealm.twoSum, target="+target+"值："+ nums[i]+", "+nums[i1]);
                    return new int[]{i, i1};
                }
            }
        }
        return new int[]{};
    }

    private int[] twoSumHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = target - nums[i];
            if (map.containsKey(t)) {
                if(map.get(t) != i){
                    System.out.println("ArrayRealm.twoSumHash，target = "+target+", 值："+ nums[i]+", " + nums[map.get(t)]);
                    return new int[]{i, map.get(t)};
                }
            }else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }
    //--------------------------两个数组的交集--------------------------

    /**
     * 1. 遍历最小数组放入map中，记录出现次数
     * 遍历最小数组：为了降低空间复杂度
     * 2. 遍历最长数组找相交数字，并更新map中记录次数
     */
    private int[] intersect(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            return intersect(arr2, arr1);
        }
        //记录arr1中每个元素出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersect = new int[arr1.length];
        int curIndex = 0;
        for (int num : arr2) {
            int count = map.getOrDefault(num, 0);
            //更新map中的记录次数，及时减小/删除已经相交过的元素
            if (count > 0) {
                intersect[curIndex++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        int[] result = Arrays.copyOfRange(intersect, 0, curIndex);
        System.out.println("ArrayRealm.intersect: " + Arrays.toString(result));
        return result;
    }

    //--------------------------只出现一次的数字--------------------------
    private int singleNumber(int[] array3) {
        int single = 0;
        for (int num : array3) {
            single ^= num;
        }
        System.out.println("ArrayRealm.singleNumber: " + single + ", " + Arrays.toString(array3));
        return single;
    }

    //--------------------------删除排序数组中的重复项--------------------------

    /**
     * 删除排序数组中的重复项
     * FM：有序、相邻重复、快慢双指针
     * 思路：
     * * 慢指针：被参照位置
     * * 快指针：对比慢指针位置，是否相同，决定单/双指针移动
     */
    private int removeDuplicates(int[] array) {
        if (array == null || array.length == 0) return 0;
        System.out.println("ArrayRealm.removeDuplicates: rowArray: " + Arrays.toString(array) + ", rowLength=" + array.length);
        int x = 0;
        for (int y = 1; y < array.length; y++) {
            if (array[x] != array[y]) {
                System.out.println("ArrayRealm.removeDuplicates (" + x + "," + y + ")");
                array[x + 1] = array[y];
                x++;//不同时：快慢指针都后移
            } else {
                //相同时：慢指针不动，快指针后移
            }
        }
        System.out.println("ArrayRealm.removeDuplicates: newArray: " + Arrays.toString(array) + ", 有效排序长度: " + (x + 1));
        return x + 1;
    }

    /**
     * 需求同上
     * 需优化：[x] != [y]时，有可能中间没有其它项，不需要重新赋值
     */
    private int removeDuplicatesBetter(int[] array) {
        if (array == null || array.length == 0) return 0;
        System.out.println("ArrayRealm.removeDuplicates: rowArray: " + Arrays.toString(array) + ", rowLength=" + array.length);
        int x = 0;
        for (int y = 1; y < array.length; y++) {
            if (array[x] != array[y]) {
                //当中间有间隔项时再重新赋值，提高效率
                if (y - x > 1) {
                    System.out.println("ArrayRealm.removeDuplicates (" + x + "," + y + ")");
                    array[x + 1] = array[y];
                }
                x++;//不同时：快慢指针都后移
            } else {
                //相同时：慢指针不动，快指针后移
            }
        }
        System.out.println("ArrayRealm.removeDuplicates: newArray: " + Arrays.toString(array) + ", 有效排序长度: " + (x + 1));
        return x + 1;
    }
}
