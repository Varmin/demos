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

        var b1 = c1.equals(c2)
        var b2 = c1 == c2
        var b3 = c1 === c2

        System.out.println("$b1, $b2, $b3")
    }
}