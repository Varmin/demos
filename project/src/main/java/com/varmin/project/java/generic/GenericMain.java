package com.varmin.project.java.generic;

import com.varmin.project.base.BaseImpl;

import java.lang.reflect.TypeVariable;
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
    public interface IWater{}
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


    /**
     * class Plate<T extends Fruit & IWater>{}
     * 类型声明处的extends为“边界”
     * &：为实现接口
     */
    public static class Plate<T extends Fruit>{
        /**
         * 类型实参处的 extends、super为“通配符”
         */
        private List<?> l1;
        private List<? extends Fruit> l2;
        private List<? super Apple> l3;
        private T mT;
        public void add(T t){
            this.mT = t;
        }

        public <K extends Fruit> void add2(K k){
            k.taste();
        }

        public static  <P extends Food> void add3(P p){
            p.taste();
        }

        //public static void add4(T t){ t.taste(); }

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
    }

}
