package com.example.composeapp.data.repo.remote

import com.example.composeapp.data.DataState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class NetworkRepository<T> {
    fun asFlow(response: Response<T>) = flow<DataState<T>> {
//        val response = fetchFromNetwork()
        val data = response.body()
        if(response.isSuccessful && data != null){
            emit(DataState.Success(data))
        }else{
            emit(DataState.Error(Exception(response.errorBody()?.string())))
        }
    }.catch {
        emit(DataState.Error(Exception(it)))
    }
//    protected abstract suspend fun fetchFromNetwork():Response<T>
}