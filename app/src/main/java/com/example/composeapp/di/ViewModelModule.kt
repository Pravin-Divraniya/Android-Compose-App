package com.example.composeapp.di

import com.example.composeapp.data.paging.CharacterPageSource
import com.example.composeapp.data.repo.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    fun provideCharacterPageSource(remoteRepository: RemoteRepository) =
        CharacterPageSource(remoteRepository = remoteRepository)
}