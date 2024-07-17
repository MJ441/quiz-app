package com.example.quizeapp.di

import com.example.quizeapp.network.QuestionAPI
import com.example.quizeapp.repository.QuestionRepository
import com.example.quizeapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides

    fun providerQuestionsRepository(api: QuestionAPI) = QuestionRepository(api)


    @Singleton
    @Provides
    fun providerQuestion(): QuestionAPI {


        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionAPI::class.java)

    }

}