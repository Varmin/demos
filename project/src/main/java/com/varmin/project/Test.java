package com.varmin.project;

import com.varmin.project.base.IBase;
import com.varmin.project.kotlin.Main;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function2;

/**
 * created by HYY on 2020/6/24
 * description:
 */
class Test implements IBase {

    @Override
    public void run() {
    }

    class B{
        public void b(){
            run();
        }
    }
}
