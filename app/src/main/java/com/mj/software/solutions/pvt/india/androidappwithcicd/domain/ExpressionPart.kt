package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    data class Parenthesis(val type: ParenthesisType) : ExpressionPart

}

sealed interface ParenthesisType {
    data object Opening : ParenthesisType
    data object Closing : ParenthesisType

}

