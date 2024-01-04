package com.example.composeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.data.DataState
import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.repo.remote.NetworkRepository
import com.example.composeapp.data.repo.remote.RemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: RemoteRepositoryImpl): ViewModel() {
    private val _dataState = MutableStateFlow<DataState<Characters>>(DataState.Loading)
    val dataState: StateFlow<DataState<Characters>> = _dataState

    init {
        fetchData()
    }

    private fun fetchData(){
        _dataState.value = DataState.Loading
        viewModelScope.launch {
            val data = repository.getCharacterData()
            NetworkRepository<Characters>().asFlow(data).collectLatest { mappedData ->
                _dataState.value = mappedData
            }
        }
    }
}