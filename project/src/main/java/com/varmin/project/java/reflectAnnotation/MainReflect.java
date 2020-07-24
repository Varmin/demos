package com.varmin.project.java.reflectAnnotation;

import com.varmin.project.base.BaseImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * created by HYY on 2020/7/14
 * description:
 */
public class MainReflect extends BaseImpl {
    /**
     * get***: 类和超类（不包括构造器）public声明的
     * declared: 所有以声明的（pu、pro、default、pri），但不包括父类。
     */
    @Override
    public void run() {
        //加载字节码文件
        getClassTest();
        printLine("Fields");
        //获取属性
        operateFields();
        printLine("Method");
        //获取方法
        operateMethod();
        printLine("constructor");
        //获取构造器
        operateConstructor();
        printLine("factory");
        //工厂模式配置文件，反射生成对象，并调用方法
        factoryReflect("com.varmin.project.java.reflectAnnotation.BeanA");
        printLine();
        factoryReflect("com.varmin.project.java.reflectAnnotation.BeanB");
    }
    private void operateConstructor() {
        BeanA obj = new BeanA();
        Class<BeanA> clazz = BeanA.class;

        /**
         * 注意：这里不是返回目标类和父类的所有public构造方法了，这里只返回目标类的public构造方法
         */
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("MainReflect.operateConstructor: constructor = "+constructor);
        }
        try {

            Constructor<BeanA> mConstructor = clazz.getConstructor(int.class, String.class);
            BeanA result = mConstructor.newInstance( 111,"kkk");
            System.out.println("MainReflect.operateConstructor: mConstructor = "+ mConstructor+", "+result);

            //无参构造器
            Constructor<BeanA> mConstructor2 = clazz.getConstructor();
            mConstructor2.newInstance();
            clazz.newInstance();//可以不使用反射，直接使用clazz对象生成
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        printLine();

        Constructor<?>[] declaredConstructor = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructor) {
            System.out.println("MainReflect.operateConstructor: declaredConstructor = "+constructor);
        }
        try {
            Constructor<BeanA> mDeclaredConstructor = clazz.getDeclaredConstructor(String.class, String.class, String.class);
            mDeclaredConstructor.setAccessible(true);
            BeanA result = mDeclaredConstructor.newInstance( "111","kkk","qqq");
            System.out.println("MainReflect.operateConstructor: mDeclaredConstructor = "+ mDeclaredConstructor+", "+result);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private void operateMethod() {
        BeanA obj = new BeanA();
        Class<BeanA> clazz = BeanA.class;

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("MainReflect.operateMethod: method = "+method);
        }
        try {
            Method mMethod = clazz.getMethod("_puMethod", String.class);
            //mMethod.setAccessible(true);
            String result = (String) mMethod.invoke(obj, "调用父类public方法");
            System.out.println("MainReflect.operateMethod: mMethod = "+mMethod.getName()+", result = "+result);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        printLine();

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("MainReflect.operateMethod: declaredMethod = "+declaredMethod);
        }
        try {
            Method mMethod = clazz.getDeclaredMethod("priMethod", String.class);
            mMethod.setAccessible(true);
            String result = (String) mMethod.invoke(obj, "调用自己private方法");
            System.out.println("MainReflect.operateMethod: mMethod = "+mMethod.getName()+", result = "+result);
        } catch (IllegalAccessException | InvocationTargetException |NoSuchMethodException e) {
            e.printStackTrace();
        }
        
    }

    private void operateFields() {
        BeanA obj = new BeanA();
        Class<BeanA> clazz = BeanA.class;
        /**
         * 自己包括父类public修饰的属性
         */
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("MainReflect.getFields: field = "+field);
        }

        try {
            Field mField = clazz.getField("_puTag");
            //mField.setAccessible(true);
            mField.set(obj, "修改了父类public属性");
            System.out.println("MainReflect.getFields: mField="+mField.getName()+", "+ mField.get(obj));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


        printLine();


        /**
         * 自己的所有属性
         */
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("MainReflect.getFields: declaredField = "+declaredField);
        }

        try {
            Field mDeclaredField = clazz.getDeclaredField("priTag");
            mDeclaredField.setAccessible(true);
            mDeclaredField.set(obj, "修改了自己private属性");
            System.out.println("MainReflect.getFields: mDeclaredField="+mDeclaredField.getName()+", "+mDeclaredField.get(obj));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    private void getClassTest() {
        Class<?> clazz1 = null;
        try {
            clazz1 = Class.forName("com.varmin.project.java.reflectAnnotation.BeanA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class<BeanA> clazz2 = BeanA.class;
        Class<? extends BeanA> clazz3 = new BeanA().getClass();

        System.out.println("MainReflect.run: "+ clazz1.toString()+", "+clazz2.toString()+", "+clazz3.toString());
        System.out.println("MainReflect.run: "+ clazz1.hashCode()+", "+clazz2.hashCode()+", "+clazz3.hashCode());
        /**
         * =====>
         * MainReflect.run: class com.varmin.project.java.BaseA, class com.varmin.project.java.BaseA, class com.varmin.project.java.BaseA
         * MainReflect.run: 1311053135, 1311053135, 1311053135
         */
    }


    private void factoryReflect(String path) {
        try {
            Class<?> clazz = Class.forName(path);

            /*for (Constructor<?> constructor : clazz.getConstructors()) {
                System.out.println("MainReflect.factoryReflect: constructor="+constructor);
            }
            for (Constructor<?> declaredConstructor : clazz.getDeclaredConstructors()) {
                System.out.println("MainReflect.factoryReflect: declaredConstructor="+declaredConstructor);
            }*/

            Constructor<?> constructor = clazz.getConstructor(String.class);
            Bean bean = (Bean) constructor.newInstance(path);
            bean.run();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
