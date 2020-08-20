package com.varmin.project.java.generic;

import com.varmin.project.base.BaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * author：yang
 * created on：2020/8/7 10:54
 * description:
 */
public class GenericMain extends BaseImpl {
    @Override
    public void run() {
        ListMaker<String> maker = new ListMaker<>();
        List<String> m1 = maker.create();
        List<Integer> m2 = maker.<Integer>create2();

    }

    class ListMaker<T>{
        public List<T> create(){return new ArrayList<T>(); }
        public <V> List<V> create2(){return new ArrayList();}
    }
}
