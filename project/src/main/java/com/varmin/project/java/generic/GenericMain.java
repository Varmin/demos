package com.varmin.project.java.generic;

import com.varmin.project.base.BaseImpl;

import java.lang.reflect.Array;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * author：yang
 * created on：2020/8/7 10:54
 * description: 泛型
 */
public class GenericMain extends BaseImpl {
    /*
    Food
        |--Fruit
           |--Banana
           |--Apple
               |--RedApple
        |--Meat
           |--Pork
           |--Beef
               |--RedBeef
    */
    public class Food {
        public void taste(){
            System.out.println("taste: "+getClass().getSimpleName());
        }
    }

    public class Fruit extends Food {}
    public class Meat extends Food {}

    public class Banana extends Fruit {}
    public class Apple extends Fruit {}
    public class RedApple extends Apple {}

    public class Pork extends Meat {}
    public class Beef extends Meat {}
    public class RedBeef extends Beef {}

//--------------------------多个边界--------------------------
    public interface IWater{
        void drink();
    }
    public interface ISmell{
        void smell();
    }

    //只能一个类，可以多个接口
    public class All<T extends Fruit & IWater & ISmell>{
        private T t;
        public void f(){
            t.taste();
            t.smell();
        }
    }


    //--------------------------泛型应用--------------------------

    /**
     * class Plate<T extends Fruit & IWater>{}
     * 类型声明处的extends为“边界”、&：为实现接口
     */
    public static class Plate<T extends Fruit>{
        /**
         * 类型实参处的 extends、super为“通配符”
         */
        private List<?> l1;
        private List<? extends Fruit> l2;
        private List<? super Apple> l3;
        private T mT;

        //实际上不属于泛型方法，只是引用了泛型类的泛型参数
        public void add(T t){
            this.mT = t;
        }

        //泛型方法
        public <K extends Fruit> void add2(K k){
            k.taste();
        }

        //static泛型方法，不能使用类上的泛型参数
        public static <P extends Food> void add3(P p){
            p.taste();
        }

        public void filled(){
            System.out.println("Plate.filled: " + mT);
        }

    }
//----------------------------------------------------

    @Override
    public void run() {
        Plate p = new Plate();
        Plate<?> p1 = new Plate<>();
        Plate<? extends Fruit> p2 = new Plate<Apple>();
        Plate<? super RedApple> p3 = new Plate<Fruit>();

        Test<String> test = new Test();
        test.setValue("Hello");

        test.setValue2(1);
        test.<Integer>setValue2(2);

        Test.setValue3(true);
        Test.<Boolean>setValue3(true);

        test.printType();

        //展示数组的一种特殊行为：将派生类的数组赋值给基类的引用
        Fruit[] fruits = new Apple[10];
        //class [Lcom.varmin.project.java.generic.GenericMain$Fruit;, class [Lcom.varmin.project.java.generic.GenericMain$Apple;
        System.out.println("GenericMain.run: "+ new Fruit[10].getClass() + ", " + new Apple[10].getClass());
        fruits[0] = new Apple();
        fruits[0] = new RedApple();
        // 编译期：Fruit放入Fruit[]没有问题，运行时：知道是Apple类型的数组，Fruit不能向上转型，报错
        //fruits[0] = new Fruit();// 运行时异常：java.lang.ArrayStoreException

        //  Apple 的 List 不是 Fruit 的 List: 编译期不相同，运行时相同
        // Compile Error: incompatible types(编译错误：不兼容类型)
        //List<Fruit> fList = new ArrayList<Apple>();
        //数组是赋值，List是泛型
        //class java.util.ArrayList, class java.util.ArrayList
        System.out.println("GenericMain.run: "+ new ArrayList<Fruit>().getClass() + ", "+ new ArrayList<Apple>().getClass());

       /* List<Fruit> fl2 = new ArrayList<>();
        fl2 = new ArrayList<RedApple>();// Compile Error: incompatible types
        fl2 = new ArrayList<Apple>();// Compile Error: incompatible types
        fl2 = new ArrayList<Fruit>();
        fl2 = new ArrayList<Food>();// Compile Error: incompatible types
        fl2.add(new RedApple());
        fl2.add(new Apple());
        fl2.add(new Fruit());
        fl2.add(new Food());// Compile Error: incompatible types
        Fruit f2 = fl2.get(0);

        *//**
         * List<? extends Fruit> fl3 = new ArrayList<>();

         * fl3 = new ArrayList<RedApple>();
         * fl3 = new ArrayList<Apple>();
         * fl3 = new ArrayList<Fruit>();
         * fl3 = new ArrayList<Food>();// Compile Error: incompatible types
         *
           List<? extends Fruit> 是指List的类型是Fruit的子类，但不确定具体是哪个子类类型。而不是指List.add添加
         * fl3.add(new RedApple());// Compile Error: incompatible types
         * fl3.add(new Apple());// Compile Error: incompatible types
         * fl3.add(new Fruit());// Compile Error: incompatible types
         * fl3.add(new Food());// Compile Error: incompatible types
         *
         *
         * 思路问题，容易迷惑
         * 一定要区分List的类型，和可传入的类型。
         * extends 范围和add范围重合，所以不能添加
         *      extends：Fruit子类，add：Fruit子类
         *      因为有最大边界，所以get时能向上转型
         * super 范围和add范围不重合，所以能添加
         *      super：Fruit基类，add：Fruit子类
         *      因为只有最小边界，所以get时无法向上转型
         *
         * List<? extends Fruit>是指List中要存放的具体类型是Fruit的子类，具体不知道哪个
         *     * 可选范围如图，List最大边界是Fruit，没有最小边界，所以不确定能add的类型
         *     * 因为不知道Fruit的具体子类，所以不能存放Fruit子类的对象。否则两者范围可能有交集，例如：List<RedApple>.add(apple)显然是不行的
         * List<? super Fruit>是指List中要存放的具体类型是Fruit的基类，具体不知道哪个
         *     * 可选范围如图，知道List的最小边界是Fruit，所以可以确定能
         *     * 虽然不知道Fruit的具体基类，但最起码是Fruit类型，例如: List<Food>.add(redApple)这是可以的
         *
         * 不能简单的理解为：
         *      List<? extends Fruit>是需要存入的对象是Fruit的子类
         *      List<? super Fruit>是需要存入的对象是Furit的基类
         *      而是List是哪种类型？
         *//*

        List<? extends Fruit> fl3 = new ArrayList<>();
        fl3 = new ArrayList<RedApple>();
        fl3 = new ArrayList<Apple>();
        fl3 = new ArrayList<Fruit>();
        fl3 = new ArrayList<Food>();// Compile Error: incompatible types
        fl3.add(new RedApple());// Compile Error: incompatible types
        fl3.add(new Apple());// Compile Error: incompatible types
        fl3.add(new Fruit());// Compile Error: incompatible types
        fl3.add(new Food());// Compile Error: incompatible types
        Fruit f3 = fl3.get(0);

        List<? super Fruit> fl4 = new ArrayList<>();
        fl4 = new ArrayList<RedApple>();// Compile Error: incompatible types
        fl4 = new ArrayList<Apple>();// Compile Error: incompatible types
        fl4 = new ArrayList<Fruit>();
        fl4 = new ArrayList<Food>();
        fl4.add(new RedApple());
        fl4.add(new Apple());
        fl4.add(new Fruit());
        fl4.add(new Food());// Compile Error: incompatible types
        Object f4 = fl4.get(0);

        List<?> fl5 = new ArrayList<>();
        fl5 = new ArrayList<RedApple>();
        fl5 = new ArrayList<Apple>();
        fl5 = new ArrayList<Fruit>();
        fl5 = new ArrayList<Food>();
        fl5.add(new RedApple());// Compile Error: incompatible types
        fl5.add(new Apple());// Compile Error: incompatible types
        fl5.add(new Fruit());// Compile Error: incompatible types
        fl5.add(new Food());// Compile Error: incompatible types
        Object f5 = fl5.get(0);*/
    }

}
