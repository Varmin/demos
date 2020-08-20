package com.varmin.project.kotlin

import com.varmin.project.base.IBase
import com.varmin.project.java.reflectAnnotation.MainReflectKotlin
import java.text.SimpleDateFormat
import java.util.*


class MainKotlin : IBase {

    override fun run() {
        println("MainKotlin.run: ${foreachFun()}")

        val url = "https://www.baidu.com/"
        println("MainKotlin.dateFormat: ${url.startsWith("ht")}")
    }

    private fun dateFormat() {
        var date = Date()
        println("MainKotlin.dateFormat: $date")
        println("MainKotlin.dateFormat: ${SimpleDateFormat.getDateInstance().format(date)}")
    }

    /**
     * 中断forEach
     */
    private fun foreachFun(): String {
        //过滤列表
        (0..9).filter { it == 1}.forEach{
            println("MainKotlin.foreachFun: filter $it")
        }
        //返回到自定义的方法
        run varmin@{
            (0..9).forEach {
                println("MainKotlin.foreachFun @varmin: $it")
                if(it == 0) return@varmin
            }
        }

        // 返回lambda表达式所在的函数
        arrayOf(1,2,3,4,5,6).forEach {
            println("MainKotlin.foreach: $it")
            if (it == 1) return@forEach //lambda表达式的返回
            if (it == 2) return "this is return" // lambda表达式所在的函数（foreachFun）
        }
        println("MainKotlin.foreach: over")
        return "-"
    }

}

