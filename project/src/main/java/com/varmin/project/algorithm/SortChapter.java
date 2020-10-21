package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.util.Arrays;

/**
 * author：yang
 * created on：2020/10/21 9:36
 * description: 排序
 */
public class SortChapter extends BaseImpl {
    int[] arrays = new int[]{5,4,3,2,1};

    @Override
    public void run() {
        maoPao(arrays);
    }

    /**
     * 冒泡排序
     * 从一端往另一端冒泡（从左往右、从右往左都可以），升序或降序
     *  - 升序：从左往右，冒泡max；从右往左，冒泡min
     *  - 降序: 同上
     * 特点：
     *  -
     *
     */
    private void maoPao(int[] arrays) {
        System.out.println("SortChapter.maoPao 初始数组："+ Arrays.toString(arrays));
        //遍历次数：如果有2个元素则遍历1次就可以了，所以 < length -1
        for (int i = 0; i < arrays.length -1; i++) {
            boolean isSort = true;
            int tmp=0;
            // -1: 比较j和j+1，所以需要-1
            // -i是为了减少遍历，因为前面已经排序过的不需要再遍历了
            for (int j = 0; j < arrays.length -1 -i; j++) {//每次遍历冒泡排序一个元素
                if (arrays[j] > arrays[j+1]) {
                    tmp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = tmp;
                    isSort = false;
                }
            }
            //如果已经排序完成，则不再遍历
            if (isSort) {
                System.out.println("SortChapter.maoPao: 已排序完成：break at i="+i);
                break;
            }
            System.out.println("SortChapter.maoPao: "+i+", "+Arrays.toString(arrays));
        }
    }
}
