package com.mj.software.solutions.pvt.india.androidappwithcicd.presentation

import androidx.compose.runtime.Composable
import com.mj.software.solutions.pvt.india.androidappwithcicd.domain.CalculatorAction

data class CalculatorUiAction (
    val text:String?,
    val highlightLevel:HighlightLevel,
    val action: CalculatorAction,
    val content:@Composable ()-> Unit = {}
)

sealed interface HighlightLevel{
    object Neutral:HighlightLevel
    object SemiHighlighted:HighlightLevel
    object Highlighted:HighlightLevel
    object StronglyHighlighted:HighlightLevel

}