package com.example.calculatorcompose

import androidx.compose.runtime.mutableStateOf

class CalculatorViewModel {
    val state = mutableStateOf(
        Display(expression = "45x18", result = "360")
    )
    fun processUserInput(input: String){
        when (input) {
            "AC" -> {
                state.value = Display("","")
            }
            "1" -> {
                state.value = Display("1","")
            }
            "2" -> {
                state.value = Display("","2")
            }
        }
    }
}

data class Display(
    val expression: String,
    val result: String
)