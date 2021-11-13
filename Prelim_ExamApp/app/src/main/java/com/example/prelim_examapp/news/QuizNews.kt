package com.example.prelim_examapp.news

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuizNews {
    private val URL= "https://newsapi.org/"

    val quizNewsService: QuizNewsService
        get()= ClientNews.getClient(URL).create(QuizNewsService::class.java)
}
object ClientNews{
    private var retrofit:Retrofit?=null

    fun getClient(baseUrl: String):Retrofit{
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
    }
