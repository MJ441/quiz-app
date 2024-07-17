package com.example.quizeapp.repository

import android.util.Log
import com.example.quizeapp.data.DataOrException
import com.example.quizeapp.model.QuestionItem
import com.example.quizeapp.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionAPI) {


    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {

            dataOrException.loading = true

            dataOrException.data = api.getAllQuestion()

            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false


        } catch (exception: Exception) {

            dataOrException.e = exception
            Log.d("ex", "getAllQuestions:${dataOrException.e!!.localizedMessage}")

        }
        return dataOrException
       }

}