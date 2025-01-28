package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

sealed interface CalculatorAction {
    data class Number(val number: Int) : CalculatorAction
    data class Op(val operation: Operation) : CalculatorAction
    data object Clear : CalculatorAction
    data object Delete : CalculatorAction
    data object Paraentheses : CalculatorAction
    data object Calculate : CalculatorAction
    data object Decimal : CalculatorAction

}