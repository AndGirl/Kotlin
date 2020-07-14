package com.ybj.myapplication.kotlin.arithemtic

import java.util.*

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
fun main(args: Array<String>): Unit {
}
class Lesson5_1 {
    private fun isLeft(c: Char): Int {
        return if (c == '{' || c == '(' || c == '[') {
            1
        } else {
            2
        }
    }

    private fun isPair(p: Char, curr: Char): Int {
        return if (p == '{' && curr == '}' || p == '[' && curr == ']' || p == '(' && curr == ')') {
            1
        } else {
            0
        }
    }

    private fun isLegal(s: String): String? {
        val stack = Stack<Char>()
        for (element in s) {
            if (isLeft(element) == 1) {
                stack.push(element)
            } else {
                if (stack.empty()) {
                    return "非法"
                }
                val p = stack.pop()
                if (isPair(p, element) == 0) {
                    return "非法"
                }
            }
        }
        return if (stack.empty()) {
            "合法"
        } else {
            "非法"
        }
    }
}