package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkRepository<T> {

    fun asFlow() = flow<Resource<T>> {
        val response = fetchFromNetwork()
        val data = response.body()
        if(response.isSuccessful && data != null){
            emit(Resource.Success(data))
        }else{
            emit(Resource.Error(response.message()))
        }
    }.catch {
        emit(Resource.Error(""))
    }
    protected abstract suspend fun fetchFromNetwork():Response<T>
}