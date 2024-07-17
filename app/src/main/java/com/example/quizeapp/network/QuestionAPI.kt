package com.example.quizeapp.network

import com.example.quizeapp.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {

    @GET("qustion.json")
    suspend fun getAllQuestion(): Question

}