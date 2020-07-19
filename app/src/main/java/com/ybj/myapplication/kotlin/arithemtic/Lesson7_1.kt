package com.ybj.myapplication.kotlin.arithemtic

/**
 * Created by 杨阳洋 on 2020/7/16.
 */
class Lesson7_1 {
    //假设要从主串 s = "goodgoogle" 中找到 t = "google" 子串。
    fun findChildString(parent:String,child:String){
        var isFind = 0
        for (parentIndex in parent.indices){
            var indexLength = 0
            if (parent[parentIndex] == child[0]){
                for (childIndex in child.indices){
                    if (parent[parentIndex + childIndex] != child[childIndex]){
                        break
                    }
                    indexLength = childIndex
                }
                if (indexLength == child.length - 1){
                    isFind = 1
                }
            }
        }
        println(isFind)
    }

    fun findMaxChildString(parent:String,child:String){
        var maxSubString = ""
        var maxLength = 0
        for (i in parent.indices) {
            for (j in child.indices) {
                if (parent[i] == child[j]) {
                    var m = i
                    var n = j
                    while (m < parent.length && n < child.length) {
                        if (parent[m] != child[n]) {
                            break
                        }
                        if (maxLength < m - i + 1) {
                            maxLength = m - i + 1
                            maxSubString = parent.substring(i, m + 1)
                        }
                        m++
                        n++
                    }
                }
            }
        }
        println(maxSubString)
        println(maxLength)
    }
    /*翻转单词*/
    fun reserveWord(data: String) {
        var data = data
        data = data.trim { it <= ' ' }
        val wordList =
            mutableListOf(*data.split("\\s+").toTypedArray())
        wordList.reverse()
        val stringBuilder = StringBuilder()
        for (i in wordList.indices) {
            if (i == wordList.size - 1) {
                stringBuilder.append(wordList[i])
            } else {
                stringBuilder.append(wordList[i].toString() + " ")
            }
        }
        println(stringBuilder.toString())
    }
}