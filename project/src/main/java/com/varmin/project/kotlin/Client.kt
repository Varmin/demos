package com.varmin.project.kotlin


/**
 * created by HY on 2020/6/22
 * description:
 */
data class Client(var name:String, var age:Int) {
    init {
        System.out.println("Client: name=$name, age=$age")
    }

}