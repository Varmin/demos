package com.varmin.project

import java.util.*

/**
 * author：yang
 * created on：2020/9/23 17:54
 * description:
 */
object GameHelper {
    val mLatestList = arrayListOf<String>()
    init {
        val sp = "1,2,3,4,5"
        if (!sp.isNullOrEmpty()) mLatestList.addAll(sp.split(","))
    }
    fun test(){
        println("GameHelper.test: ${put("0")}")
        println("GameHelper.test: ${put("1")}")
        println("GameHelper.test: ${put("2")}")
        println("GameHelper.test: ${put("3")}")
        println("GameHelper.test: ${put("4")}")
        println("GameHelper.test: ${put("5")}")
        println("GameHelper.test: ${put("6")}")
    }
    fun put(gameId: String): List<String>{
        if (mLatestList.contains(gameId) && mLatestList.remove(gameId)) {
            mLatestList.add(0, gameId)
        }else{
            mLatestList.add(gameId)
        }
        save()
        return mLatestList
    }

    private fun save() {
        var sb = StringBuilder().apply {
            for (s in mLatestList) {
                append("$s,")
            }
            if (length > 0) deleteCharAt(length-1)
        }
        println("GameHelper.save: $sb")
    }
}