package com.example.kotlinmvvm_app.api

import com.example.kotlinmvvm_app.model.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApiInterface {

    @GET("/get_memes")
   suspend fun getJokes():Response<Jokes>
}