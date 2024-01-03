package com.example.composeapp.data.source.remote

import com.example.composeapp.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character/")
    suspend fun getCharacters(@Query("page") page:Int):Response<Characters>
}