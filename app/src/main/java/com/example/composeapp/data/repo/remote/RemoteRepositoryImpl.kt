package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.source.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):RemoteRepository {
    override suspend fun getCharacterData(page: Int): Response<Characters> =
        apiService.getCharacters(page)
}