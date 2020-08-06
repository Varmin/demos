package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * author：yang
 * created on：2020/8/6 11:37
 * description: 字符串相关
 */
public class CharRealm extends BaseImpl {
    @Override
    public void run() {
        // 反转字符串
        char[] reversArr = new char[]{'V', 'a', 'r', 'm', 'i', 'n'};
        reversChar(reversArr);

    }

    //--------------------------反转字符串--------------------------
    private void reversChar(char[] chars) {
        int start = 0;
        int end = chars.length -1;
        while (start < end){
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start ++;
            end --;
        }
        System.out.println("CharRealm.reversChar: "+ Arrays.toString(chars));
    }
}
