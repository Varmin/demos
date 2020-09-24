package com.varmin.project


/**
 * author：yang
 * created on：2020/9/23 17:54
 * description:
 */
object GameHelper {
    val mLatestList = arrayListOf<String>()
    init {
        val sp = ""
        if (!sp.isNullOrEmpty()) mLatestList.addAll(sp.split(","))
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
    }
}