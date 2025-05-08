package com.example.calculatorcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CalculatorViewModel: ViewModel()  {

    private val _state = MutableStateFlow(
        Display(
            expression = "45x18",
            result = "360"
        )
    )
    val state = _state.asStateFlow()

    fun processCommand(command: CalculatorCommand){
        Log.d("CalculatorViewModel","Command:$command")
        when(command){
            CalculatorCommand.Clear ->{ _state.value = Display("","")}
            CalculatorCommand.Evaluate -> {_state.value = Display("${CalculatorCommand.Evaluate}",
                "${CalculatorCommand.Evaluate}")}
            is CalculatorCommand.Input -> {}/*state.value = Display(
                "${CalculatorCommand.Input(Symbol)}","${CalculatorCommand.Evaluate}")*/
        }
    }
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand

}
enum class Symbol {
    DIGIT_0,
    DIGIT_1,
    DIGIT_2,
    DIGIT_3,
    DIGIT_4,
    DIGIT_5,
    DIGIT_6,
    DIGIT_7,
    DIGIT_8,
    DIGIT_9,
    ADD,        //СЛОЖИТЬ
    SUBTRACT,   //ВЫЧИТАТЬ
    MULTIPLY,   //УМНОЖАТЬ
    DIVIDE,     //ДЕЛИТЬ
    PERCENT,    //ПРОЦЕНТ
    POWER,      //СТЕПЕНЬ
    FACTORIAL,
    SQRT,        //КОРЕНЬ
    PI,         //ПИ
    DOT,        //ТОЧКА
    PARENTHESIS//СКОБКИ
}

data class Display(
    val expression: String,
    val result: String
)