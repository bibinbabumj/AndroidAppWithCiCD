package com.mj.software.solutions.pvt.india.androidappwithcicd.domain


class ExperessionWriter {

    var experession = ""
    fun processAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExperssionEvaluator(parser.parse())
                experession = evaluator.evaluate().toString()
            }

            CalculatorAction.Clear -> {
                experession = ""
            }

            CalculatorAction.Decimal ->
                if (canEnterDecimal()) {
                    experession += "."
                }

            CalculatorAction.Delete -> {
                experession = experession.dropLast(1)
            }

            is CalculatorAction.Number -> {
                experession += action.number
            }

            is CalculatorAction.Op -> {
                if (canEnterOperation(action.operation)) {
                    experession += action.operation.symbol
                }

            }

            CalculatorAction.Paraentheses -> {
                processParaentheses()

            }
        }


    }

    private fun prepareForCalculation(): String {

        val newExpression = experession.dropLastWhile {
            it in "$operationSymbols (."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }


    private fun processParaentheses() {
        val openingCount = experession.count { it == '(' }
        val closingCount = experession.count { it == ')' }
        experession += when {
            experession.isEmpty() || experession.last() in "$operationSymbols (" -> "("
            experession.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        if (experession.isEmpty() || experession.last() in "$operationSymbols .()") {
            return false
        }
        // 4+5.66 here . is not allowed
        return !experession.takeLastWhile {
            it in "0123456789"
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return experession.isEmpty() || experession.last() in "$operationSymbols ()0123456789"
        }
        return experession.isNotBlank() || experession.last() in "0123456789"

    }
}