package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

class ExperssionEvaluator(private val expression: List<ExpressionPart>) {

    //-3+(5*3)
    /**
     * Uses the following grammar to evaluate the expression:
     * experession : term | term + term | expression - term
     * term: factor | factor * factor | factor / factor| factor % factor
     * factor: number | (expression) | +factor | -factor
     * */

    fun evaluate(): Double {
        return evaluateExperssion(expression).value
    }


    private fun evaluateExperssion(expression: List<ExpressionPart>): ExperessionResult {
        val result = evaluateTerm(expression)
        var remainingExp = result.remainingExpression
        var sum = result.value

        while (true) {
            when (remainingExp.firstOrNull()) {
                ExpressionPart.Op(Operation.ADD) -> {
                    val term = evaluateTerm(remainingExp.drop(1))
                    sum += term.value
                    remainingExp = term.remainingExpression
                }

                ExpressionPart.Op(Operation.SUBTRACT) -> {
                    val term = evaluateTerm(remainingExp.drop(1))
                    sum -= term.value
                    remainingExp = term.remainingExpression
                }

                else -> {
                    return ExperessionResult(remainingExp, sum)
                }

            }
        }

    }

    //5+ (3*4)
    private fun evaluateTerm(expression: List<ExpressionPart>): ExperessionResult {
        val result = evaluateFactor(expression)
        var remainingExp = result.remainingExpression
        var sum = result.value

        while (true) {
            when (remainingExp.firstOrNull()) {
                ExpressionPart.Op(Operation.MULTIPLY) -> {
                    val factor = evaluateFactor(remainingExp.drop(1))
                    sum *= factor.value
                    remainingExp = factor.remainingExpression
                }

                ExpressionPart.Op(Operation.DIVIDE) -> {
                    val factor = evaluateFactor(remainingExp.drop(1))
                    sum /= factor.value
                    remainingExp = factor.remainingExpression
                }

                ExpressionPart.Op(Operation.PERCENT) -> {
                    val factor = evaluateFactor(remainingExp.drop(1))
                    sum *= (factor.value / 100.0)
                    remainingExp = factor.remainingExpression
                }

                else -> {
                    return ExperessionResult(remainingExp, sum)
                }

            }
        }

    }


    // A factor is either a number or an experssion in para
    // eg 5.0, -7.5, -(3+4*5)
    // But Not something like 3+4, 5*5
    private fun evaluateFactor(expression: List<ExpressionPart>): ExperessionResult {
        return when (val part = expression.firstOrNull()) {
            ExpressionPart.Op(Operation.ADD) -> {
                evaluateFactor(expression.drop(1))
            }

            ExpressionPart.Op(Operation.SUBTRACT) -> {
                evaluateFactor(expression.drop(1)).run {
                    ExperessionResult(remainingExpression, -value)
                }
            }

            ExpressionPart.Parenthesis(ParenthesisType.Opening) -> {
                evaluateExperssion(expression.drop(1)).run {
                    ExperessionResult(remainingExpression.drop(1), value)
                }
            }

            ExpressionPart.Op(Operation.PERCENT) -> {
                evaluateTerm(expression.drop(1))
            }

            is ExpressionPart.Number -> ExperessionResult(expression.drop(1), part.number)
            else -> throw IllegalArgumentException("Invalid expression")
        }

    }


    data class ExperessionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double,

        )
}