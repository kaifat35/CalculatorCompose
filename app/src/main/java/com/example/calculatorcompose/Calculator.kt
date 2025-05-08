package com.example.calculatorcompose

 import android.util.Log
 import androidx.compose.foundation.background
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.aspectRatio
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.collectAsState
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import androidx.lifecycle.viewmodel.compose.viewModel
 import com.example.calculatorcompose.ui.theme.CalculatorComposeTheme



@Composable
fun Calculator(modifier: Modifier = Modifier,
               viewModel: CalculatorViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 8.dp,  end = 8.dp,bottom = 8.dp ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        Column( modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd =0.dp,
                    bottomEnd = 40.dp,
                    bottomStart = 40.dp
                )
            )
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(start = 40.dp,  end = 40.dp,bottom = 16.dp )
            .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when(val currentState: CalculatorState = state.value){
                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 36.sp)
                    Text(
                        text = "",
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp)
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 36.sp)
                    Text(
                        text = currentState.result,
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp)
                }
                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 36.sp)
                    Text(
                        text = "",
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp)
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
            .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(modifier= Modifier
                .weight(1f)
                .clickable {
                    viewModel.processCommand(CalculatorCommand.Input(Symbol.SQRT))
                },
                text = "√",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 30.sp)

            Text(modifier= Modifier
                .weight(1f)
                .clickable {
                    viewModel.processCommand(CalculatorCommand.Input(Symbol.PI))
                },
                text = "π",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 30.sp)

            Text(modifier= Modifier
                .weight(1f)
                .clickable {
                    viewModel.processCommand(CalculatorCommand.Input(Symbol.POWER))
                },
                text = "^",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 30.sp)

            Text(modifier= Modifier
                .weight(1f)
                .clickable {
                    viewModel.processCommand(CalculatorCommand.Input(Symbol.FACTORIAL))
                },
                text = "!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 30.sp)

        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = CircleShape)
                    .clickable { 
                        Log.d("Calculator", "The 'AC' button is clicked ")
                       viewModel.processCommand(CalculatorCommand.Clear)
                    }
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "AC",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "()",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)

            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PERCENT))
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "%",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE))
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "÷",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "7",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "8",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "9",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .clickable {
                            viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY))
                        }
                        .background(color = MaterialTheme.colorScheme.tertiary)
                        .weight(1f)
                        .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "X",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "4",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "5",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "6",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SUBTRACT))
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "-",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "1",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "2",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "3",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "+",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(2f)
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "0",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT))
                    }
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = ",",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
            Box(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Evaluate)
                    }
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .weight(1f)
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ){
                Text(text = "=",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 40.sp)
            }
        }
    }
}
@Preview
@Composable
fun CalculatorPreview(){
    CalculatorComposeTheme{
        Calculator()
    }
}
