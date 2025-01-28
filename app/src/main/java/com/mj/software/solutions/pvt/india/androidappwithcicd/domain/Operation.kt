package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

enum class Operation(val symbol: Char) {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), PERCENT('%'),
}

// result "+-*/%"
val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char): Operation {
    return Operation.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid Symbol")
}