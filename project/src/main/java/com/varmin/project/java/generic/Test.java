package com.varmin.project.java.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * author：yang
 * created on：2020/9/16 11:10
 * description:
 */
public class Test<T> {
    //不是泛型方法，只是使用了泛型类的泛型类型
    public T setValue(T v){
        return v;
    }

    // 返回T，该方法类型形参在方法上定义，需要类型形参在返回值前
    public <V> V setValue2(V v){
        return v;
    }

    public static <K> K setValue3(K k){
        return k;
    }

    /**
     return ((Class<T>) ((ParameterizedType) (Objects.requireNonNull(o.getClass()
     .getGenericSuperclass()))).getActualTypeArguments()[i])
     .newInstance();
     */
    public void printType(){
        /*TypeVariable<? extends Class<? extends Test>>[] type = getClass().getTypeParameters();
        System.out.println("Test.printType: type = "+ Arrays.toString(type));

        ParameterizedType gType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] gtypes = gType.getActualTypeArguments();
        System.out.println("Test.printType: gtype = " + Arrays.toString(gtypes));*/
    }
}
