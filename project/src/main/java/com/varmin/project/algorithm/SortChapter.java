package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.Arrays;

/**
 * author：yang
 * created on：2020/10/21 9:36
 * description: 排序
 */
public class SortChapter extends BaseImpl {

    @Override
    public void run() {
        printLine("冒泡排序");
        maoPao(new int[]{5, 2, 1, 4, 3});
        printLine("选择排序");
        xuanZe(new int[]{5, 2, 1, 4, 3});
        printLine("插入排序");
        chaRu(new int[]{5, 2, 1, 4, 3});
        printLine("希尔排序");
        xiEr(new int[]{8,9,1,7,2,3,5,4,6,0});

        breakTest();
    }

    /**
     * ***************************希尔排序****************************
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
     * 同时该算法是冲破O(n2）的第一批算法之一
     */
    private void xiEr(int[] arr) {
        System.out.println("SortChapter.xiEr raw: "+Arrays.toString(arr));
        //增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            System.out.println("SortChapter.xiEr gap: "+ gap);
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                System.out.println(String.format("SortChapter.xiEr: idx=(%d,%d), v=(%d,%d), %s", i, j-gap,arr[i], arr[j-gap], Arrays.toString(arr)));
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    int jv = arr[j]; int jgv = arr[j-gap];

                    int tmp = arr[j];
                    arr[j] = arr[j-gap];
                    arr[j-gap] = tmp;
                    System.out.println(String.format("SortChapter.xiEr swap : idx=(%d,%d), v=(%d,%d), %s", i, j-gap,jv, jgv, Arrays.toString(arr)));
                    j -= gap;
                }
            }
        }
        System.out.println("SortChapter.xiEr new: "+Arrays.toString(arr));
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    /**
     * 中断for循环:
     * break、continue中断最近的for
     */
    private void breakTest() {
        //中断OK
        OK:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) break OK;
            }
        }

        //标志位
        boolean flag = true;
        for (int i = 0; i < 10 && flag; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 2 && j == 5) {
                    flag = false;
                    break;
                }
            }
        }
    }

    /**
     * ***************************插入排序****************************
     * 把数组分为：已排序（元素0当作已排好的）和未排序区域
     * — 每次从未排序区域取元素，遍历比较已排序区域
     * - 将不符合条件的元素后移
     * - 将元素插入到不满足条件时的位置
     */
    private void chaRu(int[] arr) {
        System.out.println("SortChapter.chaRu raw: " + Arrays.toString(arr));
        //假设元素0已经是排序好的, 未排序的区域从1开始
        for (int i = 1; i < arr.length; i++) {//未排序区域取一个元素
            int value = arr[i];//未排序元素
            int j = 0;//插入位置
            for (j = i - 1; j >= 0; j--) {//已排序区域
                if (arr[j] > value) {// 升序：移动位置
                    arr[j + 1] = arr[j];//j还未被赋新值
                } else {//在已排序区域，<=value 则可以结束当前遍历了
                    break;
                }
                System.out.println("SortChapter.chaRu --for" +
                        ": " + i + ", " + j + ", " + Arrays.toString(arr));
            }
            //到这步时，要么j<0了，要么arr[j]不符合条件（但已经j--）
            //上面步骤中，每次都把前面的元素移到后面，但最新被比较的j还未被赋新值
            arr[j + 1] = value;//插入合适的位置
            System.out.println("SortChapter.chaRu for" +
                    ": " + i + ", " + j + ", " + Arrays.toString(arr));
        }
        System.out.println("SortChapter.chaRu new: " + Arrays.toString(arr));
    }

    /**
     * ****************************选择排序****************************
     * <p>
     * 每次遍历找到最小/大的元素，和数组的第1/2/3...个位置交换
     * 和冒泡的区别：
     * - 冒泡：每两个相互比较，互换位置
     * - 选择：每次遍历一遍数组找到最小/大，与1/2/3位置交换
     */
    private void xuanZe(int[] arrays) {
        System.out.println("SortChapter.xuanZe raw: " + Arrays.toString(arrays));
        for (int i = 0; i < arrays.length; i++) {
            boolean isSorted = true;
            int min = i;
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[j] < arrays[min]) {
                    min = j;
                    isSorted = false;
                }
            }
            int tmp = arrays[i];
            arrays[i] = arrays[min];
            arrays[min] = tmp;
            /**
             冒泡可优化：因为冒泡每次对比相邻的两个数，如果不能出现false，则证明已经排好序
             选择不可优化：因为每次都和min对比，如果不能出现false，只证明j此时正好在min位置，不需要交换位置
             */
            if (isSorted) {
                System.out.println("SortChapter.xuanZe: " + (i + 1) + "位置正好是min");
                //break;
            }
            System.out.println("SortChapter.xuanZe:  " + i + ", " + Arrays.toString(arrays));
        }
        System.out.println("SortChapter.xuanZe new: " + Arrays.toString(arrays));
    }

    /**
     * ****************************冒泡排序****************************
     * <p>
     * 从一端往另一端冒泡（从左往右、从右往左都可以），升序或降序
     * - 升序：从左往右，冒泡max；从右往左，冒泡min
     * - 降序: 同上
     */
    private void maoPao(int[] arrays) {
        System.out.println("SortChapter.maoPao raw：" + Arrays.toString(arrays));
        //遍历次数：如果有2个元素则遍历1次就可以了，所以 < length -1
        for (int i = 0; i < arrays.length - 1; i++) {
            boolean isSort = true;
            int tmp;
            // -1: 比较j和j+1，所以需要-1
            // -i是为了减少遍历，因为前面已经排序过的不需要再遍历了
            for (int j = 0; j < arrays.length - 1 - i; j++) {//每次遍历冒泡排序一个元素
                if (arrays[j] > arrays[j + 1]) {
                    tmp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = tmp;
                    isSort = false;
                }
            }
            //如果已经排序完成，则不再遍历
            if (isSort) {
                System.out.println("SortChapter.maoPao done：break at i=" + i);
                break;
            }
            System.out.println("SortChapter.maoPao new: " + i + ", " + Arrays.toString(arrays));
        }
    }
}
