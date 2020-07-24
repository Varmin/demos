package com.varmin.project.kotlin

import com.varmin.project.base.IBase


class MainKotlin : IBase {

    override fun run() {
        var (nameKotlin, priceKotlin) = BookKotlinData("Kotlin实战", 22.1f)
        var (nameJava, priceJava) = BookJava("Java实战", 22.2f)

        println("${javaClass.simpleName}.run: $nameKotlin, $priceKotlin, $nameJava, $priceJava ")
    }


    data class BookKotlinData(var name: String, val price: Float)
    class BookJava(var name: String, val price: Float){
        operator fun component1() = name
        operator fun component2() = price
    }
}

