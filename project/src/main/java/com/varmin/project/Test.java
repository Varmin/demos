package com.varmin.project;

import com.varmin.project.base.IBase;

import java.util.ArrayList;
import java.util.List;

/**
 * created by HYY on 2020/6/24
 * description:
 */
class Test implements IBase {

    @Override
    public void run() {

    }

    /**
     * 通配符在“泛型声明”处
     */
    class B<T extends Test>{
        private  T t;

        private List<?> list3;
        private List<? super String> list2;
        private List<? extends String> list1;


        public List<T> create(){
            t.run();
            return new ArrayList<>();
        }

        public <V extends Test> List<V> create2(V v){
            t.run();

            return new ArrayList<>();
        }
    }
}
