package com.example.calculater.view.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculater.core.Calculator
import java.lang.Exception

class CoreViewModel : ViewModel() {

    private val calculator = Calculator()
    private var text = ""

    val condition = MutableLiveData<String>("")
    val preview = MutableLiveData<String>("")

    fun onEqualsClick() {
        calculate(true)
    }

    fun onSymbolClick(symbol: Char) {
        val last = text.lastOrNull()
        if ((last?.isSymbol() == true && symbol.isSymbol()) || !text.isDotValid(symbol)) {
            return
        }
        text += symbol
        calculate()
    }

    fun onDeleteLongClick() {
        text = ""
        calculate()
    }

    fun onDeleteClick() {
        if (text.isNotEmpty()) {
            text = text.removeRange(text.length - 1, text.length)
            calculate()
        }
    }

    private fun calculate(isEquals: Boolean = false) {
        try {
            val data = calculator.format(text)
            if (isEquals) {
                text = data.toString()
            }
            condition.value = text
            preview.value = data.toString()
        } catch (ex: Exception) {
            condition.value = text
            preview.value = ""
        }
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
}