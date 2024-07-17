package com.example.quizeapp.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizeapp.component.Question


@Composable
fun TriviaHome(viewModel: QuestionsViewModel = hiltViewModel()){

    Question(viewModel)


}
