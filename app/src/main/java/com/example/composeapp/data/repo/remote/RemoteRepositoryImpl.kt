package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.source.remote.ApiService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):RemoteRepository {
    override suspend fun getCharacterData() = apiService.getCharacters(1)
}