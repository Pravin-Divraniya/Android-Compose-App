package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.repo.Repository
import kotlinx.coroutines.flow.Flow

interface RemoteRepository:Repository {
    fun getCharacterData(): Flow<List<Characters.Result>>
}