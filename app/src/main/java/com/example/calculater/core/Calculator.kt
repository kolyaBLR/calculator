package com.example.calculater.core

import java.lang.Exception
import java.util.*

class Calculator {

    //1+2*3
    //123
    //+*

    fun format(str: String): Double {
        val stack = Stack<Double>()
        val symbolStack = Stack<Char>()
        var temp = ""
        for (i in str.indices) {
            val symbol = str[i]
            if (isSymbol(symbol) || isBracket(symbol)) {
                stack.push(temp.toDouble())
                temp = ""
                if (symbolStack.isNotEmpty() && !isPrioritySymbol(
                        symbol,
                        symbolStack.peek()
                    ) && stack.size > 1
                ) {
                    calculate(symbol, stack)
                    symbolStack.pop()
                }
                symbolStack.push(symbol)
            } else {
                temp += str[i]
            }
        }
        if (temp.isNotEmpty()) {
            stack.push(temp.toDouble())
        }
        return 0.0
    }

    private fun calculate(symbol: Char, stack: Stack<Double>) {
        val second = stack.pop()
        val first = stack.pop()
        val value = when (symbol) {
            '/' -> first.div(second)
            '*' -> first * second
            '+' -> first + second
            '-' -> first - second
            else -> throw Exception()
        }
        stack.push(value)
    }

    private fun isSymbol(c: Char): Boolean {
        return "+-*/".any { it == c }
    }

    private fun isBracket(c: Char): Boolean {
        return "()".any { it == c }
    }

    private fun isPrioritySymbol(index: Char, peek: Char): Boolean {
        return (index == '*' || index == '/') && (peek == '+' || peek == '-')
    }
}