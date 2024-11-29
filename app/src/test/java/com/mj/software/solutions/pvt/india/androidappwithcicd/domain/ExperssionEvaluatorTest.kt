package com.mj.software.solutions.pvt.india.androidappwithcicd.domain


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExperssionEvaluatorTest {
    private lateinit var evaluator: ExperssionEvaluator

    @Test
    fun `simple experession should be evaluated`() {
        evaluator = ExperssionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(4)
    }


    @Test
    fun `expression with parentheses should be evaluated`() {
        evaluator = ExperssionEvaluator(
            listOf(
                ExpressionPart.Parenthesis(ParenthesisType.Opening),
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Parenthesis(ParenthesisType.Closing),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0)
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(4)
    }

    @Test
    fun `expression with negative numbers should be evaluated`() {
        evaluator = ExperssionEvaluator(
            listOf(
                ExpressionPart.Parenthesis(ParenthesisType.Opening),
                ExpressionPart.Number(-8.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Parenthesis(ParenthesisType.Closing),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0)
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(-8)
    }

    @Test
    fun `expression with decimal numbers should be evaluated`() {
        evaluator = ExperssionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(4.5)
    }


}

