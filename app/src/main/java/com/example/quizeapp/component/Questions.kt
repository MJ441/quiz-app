package com.example.quizeapp.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button

import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text


import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizeapp.model.QuestionItem
import com.example.quizeapp.screen.QuestionsViewModel
import androidx.compose.runtime.Composable

import androidx.compose.runtime.MutableState


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


/*@Composable
fun Question(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember { mutableStateOf(0) }

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator() // Show loading indicator while data is fetched
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (ex: Exception) { null }

        if (questions != null && questionIndex.value < questions.size) {
            QuestionDisplay(
                question = question!!, questionIndex = questionIndex, viewModel = viewModel
            ) {
                if (questionIndex.value < questions.size - 1) {
                    questionIndex.value = questionIndex.value + 1
                } else {
                    // Do nothing when the last question is reached
                  QuestionM()

                }
            }
        } else {
            // Handle the end of the questions list (e.g., show a summary or a message)

            Text(
                text = "Quiz Completed!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }}
    }

@Composable
fun QuestionM() {
    Text(
        text = "Quiz Completed!",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}*/


/*@Composable
fun Question(viewModel: QuestionsViewModel) {


    val questions = viewModel.data.value.data?.toMutableList()
    val  questionIndex = remember { mutableStateOf(0) }


    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator() // Show loading indicator while data is fetched
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        }catch (ex: Exception){null}


        if (questions != null){
            QuestionDisplay(
                question = question!!, questionIndex = questionIndex, viewModel = viewModel,



            ) {



                questionIndex.value = questionIndex.value +1



            }


    }else
        if (viewModel.getTotalQuestion() == viewModel.getTotalQuestion())
            Text(text = "h")



    }
        
        

    
    
    }*/

@Composable
fun Question(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    val questionIndex = remember { mutableStateOf(0) }

    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator() // Show loading indicator while data is fetched
    } else {
        val question = try {
            questions?.get(questionIndex.value)
        } catch (ex: Exception) { null }

        if (questions != null && questionIndex.value < questions.size) {
            QuestionDisplay(
                question = question!!, questionIndex = questionIndex, viewModel = viewModel
            ) {
                questionIndex.value = questionIndex.value + 1
            }
        } else {
            // Display "Quiz Completed!" message
            Text(
                text = "Quiz Completed!",
              modifier = Modifier.padding(top = 400.dp),
                textAlign = TextAlign.Center, fontSize = 24.sp,
                fontWeight = FontWeight.Light,

            )
        }
    }
}



       // if (questions != null){

        //    QuestionDisplay(question = questions.first()  )






       // questions?.forEach { questionItem ->



        //    Log.d("Result", "Question: ${questionItem.question}")






@Composable
fun QuestionDisplay(

    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionsViewModel,
    onNextClicked: (Int) -> Unit =  {}



) {

    val choicesState = remember(question) {    question.choices.toMutableList()}
    val  answerState = remember(question) { mutableStateOf<Int?>(null) }
    val correctAnswerState = remember(question) { mutableStateOf<Boolean?>(null) }


    val updateAnswer: (Int) -> Unit =
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == question.answer
        }





    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f,10f), 0f)




    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.DarkGray    ) {


        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        )


        {

            if (questionIndex.value >= 3) ShowProgress(score = questionIndex.value)



            QuestionTracker(counter = questionIndex.value, viewModel.getTotalQuestion())
            DrawDottedLine(pathEffect = pathEffect )

            Column {
                Text(text =  question.question,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(alignment = Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp , fontWeight = FontWeight.Bold
                , lineHeight = 22.sp, color = Color.White
                )

                //choices

                choicesState.forEachIndexed{ index, answerText ->

                    Row (modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .background(Color.White)


                        .border(
                            width = 4.dp, brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.White
                                )
                            ), shape = RoundedCornerShape(15.dp)
                        )
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 50,
                                topEndPercent = 50,
                                bottomEndPercent = 50,
                                bottomStartPercent = 50
                            )
                        )

                        ,verticalAlignment = Alignment.CenterVertically
                    ){

                        RadioButton(selected =(answerState.value == index) ,
                            onClick = { updateAnswer(index) },
                            modifier = Modifier.padding(16.dp),
                            colors  = RadioButtonDefaults.colors(
                                selectedColor =
                                if (correctAnswerState.value == true && index == answerState.value){
                                    Color.Green//.copy(alpha = 0.2f)

                                }else {

                                    Color.Red//.copy(alpha = 0.2f)
                                }))
                        //end
                        
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,

                                    color = if (correctAnswerState.value == true &&
                                        index == answerState.value) {
                                        Color.Green

                                    } else if (correctAnswerState.value == false
                                        && index == answerState.value
                                    ) {

                                        Color.Red
                                    } else {
                                        Color.Black
                                    }, fontSize = 17.sp
                                )
                            ) {


                                append( answerText)

                            }


                            
                            
                        }

                        Text(text = annotatedString, modifier = Modifier.padding(6.dp))


                    }

                }

                Button(onClick = { onNextClicked(questionIndex.value)

                    answerState.value = null
                    correctAnswerState.value = null

                                 }, modifier = Modifier
                    .padding(3.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.White))
                {

                    Text(text = "Next", modifier = Modifier.padding(4.dp),

                        color = Color.Black, fontSize = 17.sp
                        )

                }

            }

        }
    }
}

@Composable

fun DrawDottedLine(pathEffect: PathEffect){

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp) ){

        drawLine(color = Color.White, start = Offset(0f,0f),
            end = Offset(size.width,0f),
            pathEffect = pathEffect
            )

    }





}



@Composable
fun ShowProgress(score: Int = 12){

    val gradient = Brush.linearGradient(listOf(Color.Cyan, Color.Cyan))

    val progressFactor = remember(score) {

        mutableStateOf(score*0.005f)

    }
    Row (modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth()
        .height(45.dp)
        .border(
            width = 4.dp, brush = Brush.linearGradient(
                colors = listOf(

                    Color.White, Color.White


                )
            ),

            shape = RoundedCornerShape(34.dp)
        )
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50,
                bottomStartPercent = 50,
                bottomEndPercent = 50
            )
        )
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ){
       Button(
           contentPadding = PaddingValues(1.dp),
           onClick = {  },

       modifier = Modifier
           .fillMaxWidth(progressFactor.value)
           .background(brush = gradient),

           colors = ButtonDefaults.buttonColors(

              Color.White

           )

       ) {


           Text(text = (score*10).toString(),
               modifier = Modifier
                   .clip(shape = RoundedCornerShape(23.dp))
                   .fillMaxHeight(0.07f)
                   .fillMaxWidth()
                   .padding(6.dp),
               color = Color.Black,
               textAlign = TextAlign.Center
               )

       }
    }



}




@Composable

fun QuestionTracker(
    counter: Int = 10,
    outOff: Int = 100
) {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 27.sp
                )
            ) {

                append("question $counter/")


                withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Light,
                    fontSize = 14.sp)){

                    append("$outOff")

                }

            }

        }
    }, modifier = Modifier.padding(20.dp))


}





















