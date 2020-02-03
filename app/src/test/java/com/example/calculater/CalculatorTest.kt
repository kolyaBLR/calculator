package com.example.calculater

import com.example.calculater.core.Calculator
import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    /*@Test
    fun format_1() {
        val converter = Calculator()
        assertEquals("%s-%s", converter.format("123-2222").formatStr)
    }
    @Test
    fun format_2() {
        val converter = Calculator()
        assertEquals("%s-%s*%s", converter.format("323-22*312").formatStr)
    }

    @Test
    fun format_3() {
        val converter = Calculator()
        assertEquals("%s-%s*%s-(%s*%s)", converter.format("3-222*4-(11*2)").formatStr)
    }

    @Test
    fun format_4() {
        val converter = Calculator()
        assertEquals("(%s+%s)*(%s-%s)", converter.format("(11+13)*(444-4123222)").formatStr)
    }*/

    @Test fun calculatePolishNotation_1() {
        val converter = Calculator()
        val data = converter.format("1-2*3")
    }

    /*@Test
    fun convertFromInfixToPolishNotations_1() {
        val converter = Calculator()
        val actual = converter.convertFromInfixToPolishNotations("3-2")//todo multi
        assertEquals("32-", actual)
        assertEquals(1.0, converter.calculatePolishNotation(actual), 0.0)
    }

    @Test
    fun convertFromInfixToPolishNotations_2() {
        val converter = Calculator()
        val actual = converter.convertFromInfixToPolishNotations("1+2*3")
        assertEquals("123*+", actual)
        assertEquals(7, converter.calculatePolishNotation(actual))
    }

    @Test
    fun convertFromInfixToPolishNotations_3() {
        val converter = Calculator()
        val actual = converter.convertFromInfixToPolishNotations("1*2+3")
        assertEquals("12*3+", actual)
        assertEquals(5, converter.calculatePolishNotation(actual))
    }

    @Test
    fun convertFromInfixToPolishNotations_4() {
        val converter = Calculator()
        val actual = converter.convertFromInfixToPolishNotations("(2+3)*4")
        assertEquals("23+4*", actual)
        assertEquals(20, converter.calculatePolishNotation(actual))
    }*/
}