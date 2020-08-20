package com.varmin.project.java.reflectAnnotation

/**
 * author：yang
 * created on：2020/8/12 14:38
 * description:
 */
class BeanKotlin {
    companion object{
        const val aa = 1
        fun start(y:String){
            println("start: x = [${aa}], y = [${y}]")
        }
    }

    val _aa = 1
    fun _fun1(x:Int, y: String){
        println("fun1: x = [${x}], y = [${y}]")
    }
}