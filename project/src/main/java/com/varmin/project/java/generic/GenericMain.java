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


        Fruit[] fruits = new Apple[10];
        //class [Lcom.varmin.project.java.generic.GenericMain$Fruit;, class [Lcom.varmin.project.java.generic.GenericMain$Apple;
        System.out.println("GenericMain.run: "+ new Fruit[10].getClass() + ", " + new Apple[10].getClass());
        fruits[0] = new Apple();
        fruits[0] = new RedApple();
        // 运行时异常：java.lang.ArrayStoreException
        //fruits[0] = new Fruit();

        // Compile Error: incompatible types(编译错误：不兼容类型)
        //List<Fruit> fList = new ArrayList<Apple>();

        //数组是赋值，List是泛型
        //class java.util.ArrayList, class java.util.ArrayList
        System.out.println("GenericMain.run: "+ new ArrayList<Fruit>().getClass() + ", "+ new ArrayList<Apple>().getClass());
    }

}
