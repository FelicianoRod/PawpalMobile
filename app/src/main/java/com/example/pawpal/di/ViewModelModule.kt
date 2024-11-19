package com.example.pawpal.di

import com.example.core.ui.viewmodel.SelectedDogViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideSharedDogViewModel(): SelectedDogViewModel {
        return SelectedDogViewModel()
    }
}