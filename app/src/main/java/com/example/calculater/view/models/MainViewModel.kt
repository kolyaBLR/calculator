package com.example.calculater.view.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculater.core.Calculator
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val calculator = Calculator()
    private var text = ""

    val condition = MutableLiveData<String>("")
    val preview = MutableLiveData<String>("")

    fun onClickSymbol(symbol: Char) {
        try {
            /*text += symbol
            val formatData = calculator.format(text)
            val polishNotation = calculator.convertFromInfixToPolishNotations(formatData.formatStr)
            val result = calculator.calculatePolishNotation(polishNotation)

            condition.value = text
            preview.value = result.toString()*/
        } catch (ex: Exception) {

        }
    }
}