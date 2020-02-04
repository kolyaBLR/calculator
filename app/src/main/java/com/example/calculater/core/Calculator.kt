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
                    calculate(symbolStack, stack)
                }
                symbolStack.push(symbol)
            } else {
                temp += str[i]
            }
        }
        if (temp.isNotEmpty()) {
            stack.push(temp.toDouble())
        }
        while (stack.size != 1) {
            calculate(symbolStack, stack)
        }
        return stack.pop()
    }

    private fun calculate(symbolStack: Stack<Char>, stack: Stack<Double>) {
        val second = stack.pop()
        var first = stack.pop()
        val symbol = symbolStack.pop()
        if (symbolStack.isNotEmpty() && symbolStack.peek() == '-') {
            symbolStack.pop()
            symbolStack.push('+')
            first *= -1
        }
        val value = when (symbol) {
            '/' -> first.div(second)
            '*' -> first * second
            '+' -> first + second
            '-' -> first - second
            else -> throw Exception()
        }
        print(String.format("%s%s%s = %s\n", first, symbol, second, value))
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