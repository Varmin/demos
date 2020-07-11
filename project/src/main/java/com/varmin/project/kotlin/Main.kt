package com.varmin.project.kotlin

import com.varmin.project.base.IBase


class Main : IBase {
    override fun run() {

    }


/*
    /*var (nameKotlin, priceKotlin) = KotlinBook("Kotlin实战", 22f)
    var (nameJava, priceJava) = JavaBook("Kotlin实战", 22f)*/

    data class KotlinBook(var name: String, var price: Float)
    class JavaBook(var name: String, var price: Float){
        operator fun component1() = name
        operator fun component2() = price
    }*/
}

