package com.mj.software.solutions.pvt.india.androidappwithcicd.domain


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExperessionWriterTest{
    private lateinit var experessionWriter: ExperessionWriter

    @Before
    fun setUp(){
        experessionWriter = ExperessionWriter()
    }

    @Test
    fun `Initial parentheses are passed`(){
        experessionWriter.processAction(CalculatorAction.Paraentheses)
        experessionWriter.processAction(CalculatorAction.Number(5))
        experessionWriter.processAction(CalculatorAction.Op(Operation.ADD))
        experessionWriter.processAction(CalculatorAction.Number(5))
        experessionWriter.processAction(CalculatorAction.Paraentheses)
        assertThat(experessionWriter.experession).isEqualTo("(5+5)")
    }

@Test
fun `Closing parentheses at start passed`(){
    experessionWriter.processAction(CalculatorAction.Paraentheses)
    experessionWriter.processAction(CalculatorAction.Paraentheses)
    assertThat(experessionWriter.experession).isEqualTo("((")
}

    @Test
    fun ` parentheses around number passed`(){
        experessionWriter.processAction(CalculatorAction.Paraentheses)
        experessionWriter.processAction(CalculatorAction.Number(5))
        experessionWriter.processAction(CalculatorAction.Paraentheses)
        assertThat(experessionWriter.experession).isEqualTo("(5)")
    }

}