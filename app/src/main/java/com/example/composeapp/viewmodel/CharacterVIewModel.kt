package com.example.composeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.composeapp.data.paging.CharacterPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterPageSource: CharacterPageSource): ViewModel() {
    val dataState = Pager(
        config = PagingConfig(pageSize = 20),
        initialKey = 1,
        pagingSourceFactory = {
            characterPageSource
        }
    ).flow.cachedIn(viewModelScope)
}