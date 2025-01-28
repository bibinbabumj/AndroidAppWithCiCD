package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

class ExpressionParser(private val calculation: String) {
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()
        var index = 0
        while (index < calculation.length) {
            val currentChar = calculation[index]
            when {
                currentChar in operationSymbols -> {
                    result.add(ExpressionPart.Op(operationFromSymbol(currentChar)))
                }

                currentChar.isDigit() -> {
                    index = parseNumber(index, result)
                    continue
                }

                currentChar in "()" -> {
                    parseParentheses(currentChar, result)
                }

            }
            index += 1
        }


        return result
    }

    private fun parseNumber(startIndex: Int, result: MutableList<ExpressionPart>): Int {
        var index = startIndex
        var numberAsString = buildString {
            while (index < calculation.length && calculation[index] in "0123456789.") {
                append(calculation[index])
                index += 1

            }
        }
        result.add(ExpressionPart.Number(numberAsString.toDouble()))
        return index

    }

    private fun parseParentheses(currentChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parenthesis(
                type = when (currentChar) {
                    '(' -> ParenthesisType.Opening
                    ')' -> ParenthesisType.Closing
                    else -> throw IllegalArgumentException("Invalid character")
                }
            )
        )

    }

}