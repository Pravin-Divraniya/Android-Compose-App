package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.Resource
import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.source.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):RemoteRepository {
    override fun getCharacterData(): Flow<List<Characters.Result>> {
        return object: NetworkRepository<Characters>(){
            override suspend fun fetchFromNetwork() = apiService.getCharacters(1)
        }.asFlow()
            .flowOn(Dispatchers.IO)
            .map { result ->
                when(result){
                    is Resource.Success -> {
                        result.data.results
                    }
                    else -> {
                        emptyList()
                    }
                }
            }
    }
}