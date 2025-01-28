package com.mj.software.solutions.pvt.india.androidappwithcicd.domain

import org.junit.Assert.*
import org.junit.Test
import org.xmlpull.v1.XmlPullParserException

class ExpressionParserTest {
    private lateinit var parserException: ExpressionParser

    @Test
    fun `Simple expression is parsed correctly`() {
        ///1.Given

        parserException = ExpressionParser("3+2-3*4/3")

        //2. do sothing with given
        val result = parserException.parse()


        //3. assert expected== Actual
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )

        assertEquals(expected, result)

    }


    @Test
    fun ` expression is parentheses correctly`() {
        parserException = ExpressionParser("(2+2)-3")
        val result = parserException.parse()

        val actual = listOf(
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(2.0),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0)

        )
        assertEquals(actual, result)
    }

}