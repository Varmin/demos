package com.varmin.project.algorithm;

import com.varmin.project.base.BaseImpl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

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
        // 字符串中的第一个唯一字符
        String str = "hahahahello";
        firstSingleChar(str);
    }

    private int firstSingleChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c,0)+1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                System.out.println("CharRealm.firstSingleChar: index="+i+", char="+ str.charAt(i));
                return i;
            }
        }
        return -1;
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
