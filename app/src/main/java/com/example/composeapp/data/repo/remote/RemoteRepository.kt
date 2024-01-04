package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.repo.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteRepository:Repository {
    suspend fun getCharacterData(page:Int): Response<Characters>
}