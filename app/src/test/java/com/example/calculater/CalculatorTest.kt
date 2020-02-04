package com.example.calculater

import com.example.calculater.core.Calculator
import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    @Test
    fun calculatePolishNotation_1() {
        val converter = Calculator()
        val data = converter.format("10-2*3")
        assertEquals(4.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_2() {
        val converter = Calculator()
        val data = converter.format("2-2*3+1/2")
        assertEquals(-3.5, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_3() {
        val converter = Calculator()
        val data = converter.format("12-9*11+11/22")
        assertEquals(-86.5, data, 0.0)
    }
}