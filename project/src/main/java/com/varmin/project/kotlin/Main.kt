package com.varmin.project.kotlin

import com.varmin.project.base.IBase

/**
 * created by HY on 2020/6/22
 * description:
 */
class Main :IBase{
    override fun run() {
        var c1 = Client("Varmin", 23)
        var c2 = Client("Varmin", 23)
        System.out.println("c1,$c1,  ${c1.toString()}, ${c1.hashCode()}")
        System.out.println("c2,$c2,  ${c2.toString()}, ${c2.hashCode()}")
    }
}