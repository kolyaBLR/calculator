package com.example.calculater

import com.example.calculater.core.Calculator
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.sin

class CalculatorTest {

    @Test
    fun calculatePolishNotation_1() {
        val converter = Calculator()
        val data = converter.calculate("10-2*3")
        assertEquals(4.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_2() {
        val converter = Calculator()
        val data = converter.calculate("2-2*3+1/2")
        assertEquals(-3.5, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_3() {
        val converter = Calculator()
        val data = converter.calculate("12-9*11+11/22")
        assertEquals(-86.5, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_4() {
        val converter = Calculator()
        val data = converter.calculate("(1+2)*(3+4)")
        assertEquals(21.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_5() {
        val converter = Calculator()
        val data = converter.calculate("5+4*(2+11)")
        assertEquals(57.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_6() {
        val converter = Calculator()
        val data = converter.calculate("(12-33)/4+2-30*(4+14)")
        assertEquals(-543.25, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_sin_1() {
        val converter = Calculator()
        val data = converter.calculate("S(0)")
        assertEquals(0.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_sin_2() {
        val converter = Calculator()
        val data = converter.calculate("S(90)")
        assertEquals(1.0, data, 0.0)
    }


    @Test
    fun calculatePolishNotation_sin_3() {
        val converter = Calculator()
        val data = converter.calculate("S(270)")
        assertEquals(-1.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_sin_4() {
        val converter = Calculator()
        val data = converter.calculate("S(45)")
        assertEquals(0.707, data, 0.001)
    }

    @Test
    fun calculatePolishNotation_cos_1() {
        val converter = Calculator()
        val data = converter.calculate("C(90)")
        assertEquals(0.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_cos_2() {
        val converter = Calculator()
        val data = converter.calculate("C(180)")
        assertEquals(-1.0, data, 0.0)
    }


    @Test
    fun calculatePolishNotation_cos_3() {
        val converter = Calculator()
        val data = converter.calculate("C(0)")
        assertEquals(1.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_cos_4() {
        val converter = Calculator()
        val data = converter.calculate("C(270)")
        assertEquals(0.0, data, 0.0)
    }

    @Test
    fun calculatePolishNotation_cos_5() {
        val converter = Calculator()
        val data = converter.calculate("C(45)")
        assertEquals(0.707, data, 0.001)
    }

    @Test
    fun calculatePolishNotation_cos_6() {
        val converter = Calculator()
        val data = converter.calculate("C(66)")
        assertEquals(0.406, data, 0.001)
    }
}