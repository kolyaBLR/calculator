package com.example.calculater.view.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculater.core.Calculator
import java.lang.Exception

class CoreViewModel : ViewModel() {

    private val calculator = Calculator()
    private var text = ""
    private var memoryData = 0.0

    val condition = MutableLiveData("")
    val preview = MutableLiveData("")
    var errorText = ""

    /*
    MS (Memory Save) - кнопка означает сохранить число, отображенное в данный момент на дисплее калькулятора в память.
    MR (Memory Read) - кнопка означает считать число из ячейки памяти и вывести его на дисплей.
    MC (Memory Clear) - кнопка означает стереть данные из ячейки памяти.
    M+ - прибавить к числу из памяти число, отображенное на дисплее и результат записать в память вместо предыдущего.
    M- - вычесть из числа в памяти число, отображенное на дисплее калькулятора и результат записать в память.
    */

    fun onMemoryClick(symbol: Char) {
        val value = preview.value?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
        when (symbol) {
            'S' -> memoryData = value
            'R' -> {
                text = if (memoryData != 0.0) memoryData.toString() else ""
                condition.value = memoryData.toString()
                preview.value = memoryData.toString()
            }
            'C' -> memoryData = 0.0
            '+' -> memoryData += value
            '-' -> memoryData -= value
        }
    }

    fun onEqualsClick() {
        calculate(true)
    }

    fun onSymbolClick(symbol: Char) {
        if (isNotValid(symbol)) {
            return
        }
        when (symbol) {
            'S' -> text += "Sin("
            'C' -> text += "Cos("
            '0' -> if (isValidZero()) text += symbol
            else -> text += symbol
        }
        calculate()
    }

    private fun isValidZero(): Boolean {
        if (text.length < 2) {
            return !(text.length == 1 && text.first() == '0')
        }

        val preLastSymbol = text[text.length - 2]
        if (text.last() == '0' && (preLastSymbol.isSymbol())) {
            return false
        }
        return true
    }

    fun onDeleteLongClick() {
        text = ""
        calculate()
    }

    fun onDeleteClick() {
        if (text.isNotEmpty()) {
            text = if (text.isOperator()) {
                text.removeOperator()
            } else {
                text.removeRange(text.length - 1, text.length)
            }
            calculate()
        }
    }

    private fun String.isOperator(): Boolean {
        val sin = "Sin("
        val cos = "Cos("

        if (this.length < sin.length) {
            return false
        }

        return this.isOperator(sin, sin.length) || this.isOperator(cos, cos.length)
    }

    private fun String.isOperator(toFind: String, size: Int): Boolean {
        return this.indexOf(toFind, this.length - size) != -1
    }

    private fun String.removeOperator(): String {
        val sin = "Sin("
        val cos = "Cos("

        if (this.length < sin.length) {
            return this
        }

        return this.removeRange(this.length - sin.length, this.length)
    }

    private fun isNotValid(symbol: Char): Boolean {
        val last = text.lastOrNull()
        return (last?.isSymbol() == true && symbol.isSymbol()) || !text.isDotValid(symbol)
    }

    private fun calculate(isEqualsClick: Boolean = false) {
        try {
            val formattedText = text
                .replace("Sin", "S")
                .replace("Cos", "C")
                .autocompleteBracket()

            val data = calculator.calculate(formattedText)

            if (isEqualsClick && data != Double.POSITIVE_INFINITY && data != Double.NEGATIVE_INFINITY) {
                text = data.toString()
            }

            if (data == Double.POSITIVE_INFINITY || data == Double.NEGATIVE_INFINITY) {
                condition.value = text
                preview.value = errorText
            } else {
                condition.value = text
                preview.value = data.toString()
            }
        } catch (ex: Exception) {
            condition.value = text
            preview.value = ""
        }
    }

    private fun String.autocompleteBracket(): String {
        return if (this.sumBy { if (it == '(') 1 else 0 } > this.sumBy { if (it == ')') 1 else 0 }) "$this)" else this
    }

    private fun Char.isDot() = this == '.'
    private fun String.isDotValid(char: Char): Boolean {
        val reversed = this.reversed()
        for (i in reversed.indices) {
            val c = reversed[i]
            if (c.isSymbol() || c.isDot()) {
                if (char.isDot()) {
                    return !c.isDot()
                }
            }
        }
        return true
    }

    private fun Char.isSymbol() = "/*+-".any { this == it }
    private fun Char.isBracket() = "()".any { this == it }
}