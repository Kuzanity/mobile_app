package com.example.prelim_examapp.news

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface QuizNewsService {
    @GET("v2/everything")
    fun getCurrentNews(@Query("q") query:String,
                       @Query("apiKey") apiKey:String):
            Call<QuizNewsResponse>
}