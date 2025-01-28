package com.mj.software.solutions.pvt.india.androidappwithcicd.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mj.software.solutions.pvt.india.androidappwithcicd.domain.CalculatorAction
import com.mj.software.solutions.pvt.india.androidappwithcicd.domain.ExperessionWriter

class CalculatorViewModel(
    private val writer: ExperessionWriter = ExperessionWriter()
): ViewModel() {

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        this.expression = writer.experession
    }
}