package com.varmin.project.java.reflectAnnotation

import com.varmin.project.base.BaseImpl
import java.text.DateFormat
import java.util.*

/**
 * author：yang
 * created on：2020/8/12 14:38
 * description: Kotlin反射
 */
class MainReflectKotlin : BaseImpl(){
    override fun run() {
        val clazz = Class.forName("com.varmin.project.java.reflectAnnotation.BeanKotlin")
        val fields = clazz.declaredFields
        for (field in fields) {
            println("MainReflectKotlin.run, field: ${field.name}, ${field.type}")
        }

        val compClazz = Class.forName("com.varmin.project.java.reflectAnnotation.BeanKotlin\$Companion")
        val methods = compClazz.declaredMethods
        for (method in methods) {
            println("MainReflectKotlin.run, method: $method")
        }

        val constructors = compClazz.declaredConstructors
        for (constructor in constructors) {
            println("MainReflectKotlin.run, constructor: $constructor")
        }
        val consComp = compClazz.getDeclaredConstructor()
        consComp.isAccessible = true
        println("MainReflectKotlin.run, getconst：$consComp, ${consComp.newInstance()}")


        val methodStart = compClazz.getDeclaredMethod("start", String::class.java)
        methodStart.isAccessible = true
        var bean = compClazz.getDeclaredConstructor().run {
            isAccessible = true
            newInstance()
        }
        methodStart.invoke(bean,"Kotlin")
    }
}