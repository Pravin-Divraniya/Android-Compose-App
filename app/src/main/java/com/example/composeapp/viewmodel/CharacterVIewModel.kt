package com.example.composeapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeapp.data.repo.remote.RemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: RemoteRepositoryImpl): ViewModel() {
    val characters = repository.getCharacterData()
}