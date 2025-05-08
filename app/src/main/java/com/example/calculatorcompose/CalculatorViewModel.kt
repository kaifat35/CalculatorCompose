package com.example.calculatorcompose

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression


class CalculatorViewModel: ViewModel()  {

    private val _state :MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )
    val state = _state.asStateFlow()

    private var expression= ""

    fun processCommand(command: CalculatorCommand){
        Log.d("CalculatorViewModel","Command:$command")
        when(command) {
            CalculatorCommand.Clear -> {
                expression = ""
                _state.value = CalculatorState.Initial
            }

            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                _state.value = if (result != null){
                    CalculatorState.Success(result = result)
                } else {
                    CalculatorState.Error(expression = expression)
                }
            }


            is CalculatorCommand.Input -> {
                val symbol = if (command.symbol != Symbol.PARENTHESIS){
                    command.symbol.value
                }else{
                    getCorrectParenthesis()
                }
                expression += symbol

                _state.value = CalculatorState.Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
        }
    }

    private fun evaluate():String? {
        expression.replace('X', '*')
            .replace(',', '.')
            .let { Expression(it) }
            .calculate()
            .takeIf { it.isFinite() } ?.toString()
        return  Expression(expression).calculate().toString()
    }


    fun getCorrectParenthesis():String{
        val openCount:Int= expression.count{it == '('}
        val closeCount:Int = expression.count{it == ')'}

        return when{
            expression.isEmpty()->"("
            expression.last().let { !it.isDigit() && it !='π' && it != ')' } ->"("
            openCount > closeCount -> ")"
            else -> "("
        }
    }
}

sealed interface CalculatorState{
    data object Initial:CalculatorState
    data class Input(
        val expression: String,
        val result: String
    ):CalculatorState

    data class Success (val result: String):CalculatorState

    data class Error ( val expression: String):CalculatorState
}

sealed interface CalculatorCommand {

    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand

}
enum class Symbol(val value: String) {
    DIGIT_0("0"),
    DIGIT_1("1"),
    DIGIT_2("2"),
    DIGIT_3("3"),
    DIGIT_4("4"),
    DIGIT_5("5"),
    DIGIT_6("6"),
    DIGIT_7("7"),
    DIGIT_8("8"),
    DIGIT_9("9"),
    ADD("+"),        //СЛОЖИТЬ
    SUBTRACT("-"),   //ВЫЧИТАТЬ
    MULTIPLY("x"),   //УМНОЖАТЬ
    DIVIDE("/"),     //ДЕЛИТЬ
    PERCENT("%"),    //ПРОЦЕНТ
    POWER("^"),      //СТЕПЕНЬ
    FACTORIAL("!"),
    SQRT("√"),        //КОРЕНЬ
    PI("π"),         //ПИ
    DOT(","),        //ТОЧКА
    PARENTHESIS("()")//СКОБКИ
}

data class Display(
    val expression: String,
    val result: String
)