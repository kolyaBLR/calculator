package com.example.calculater.core

import java.lang.Exception
import java.util.*
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin

class Calculator {

    fun calculate(str: String): Double {
        val valuesStack = Stack<Double>()
        val symbolStack = Stack<Char>()
        var temp = ""
        for (i in str.indices) {
            val symbol = str[i]
            if (symbolStack.isPeekSinusOrCosinus() && isBracket(symbol)) {
                if (temp.isNotEmpty()) {
                    valuesStack.push(temp.toDouble())
                }
                temp = ""
                if (symbol == ')') {
                    calculateSinusOrCosinus(symbolStack.pop(), valuesStack)
                }
            } else if (isSymbol(symbol) || symbol == ')') {
                if (temp.isNotEmpty()) {
                    valuesStack.push(temp.toDouble())
                }
                temp = ""
                val isPriority = symbolStack.isNotEmpty() && !isPrioritySymbol(
                    symbol, symbolStack.peek()
                ) && symbolStack.peek() != '('
                if ((isPriority || symbol == ')') && valuesStack.size > 1) {
                    calculate(symbolStack, valuesStack)
                }
                if (symbol != ')') {
                    symbolStack.push(symbol)
                }
            } else if (symbol == 'S' || symbol == 'C') {
                symbolStack.push(symbol)
            } else if (symbol == '(') {
                symbolStack.push(symbol)
            } else {
                temp += str[i]
            }
        }
        if (temp.isNotEmpty()) {
            valuesStack.push(temp.toDouble())
        }
        while (valuesStack.size != 1) {
            calculate(symbolStack, valuesStack)
        }
        return valuesStack.pop()
    }

    private fun calculateSinusOrCosinus(symbol: Char, valuesStack: Stack<Double>) {
        val value = valuesStack.pop()
        val radiansValue = (value * Math.PI).div(180.0)
        val result = when (symbol) {
            'C' -> cos(radiansValue)
            'S' -> sin(radiansValue)
            else -> throw Exception("not valid type: $symbol")
        }
        //cos 90 и cos 270 возвращает отрицательное число бесконечно близкое к нулю
        // это хак с округлением
        val roundResult = round(result * 1000000).div(1000000)
        valuesStack.push(roundResult)
    }

    private fun calculate(symbolStack: Stack<Char>, stack: Stack<Double>) {
        val second = stack.pop()
        var first = stack.pop()
        val symbol = symbolStack.pop()
        if (symbolStack.isNotEmpty() && symbolStack.peek() == '(') {
            symbolStack.pop()
        }
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

    private fun Stack<Char>.isPeekSinusOrCosinus(): Boolean {
        return this.isNotEmpty() && "SC".any { it == this.peek() }
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