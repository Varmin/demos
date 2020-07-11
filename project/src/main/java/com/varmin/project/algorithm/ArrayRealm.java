package com.varmin.project.algorithm;

import com.varmin.project.base.IBase;
import java.util.Arrays;

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
        //
    }

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
