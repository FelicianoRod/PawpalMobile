package com.example.pawpal.di

import com.example.dogprofile.data.supabase.DogRepositoryImpl
import com.example.dogprofile.domain.repository.DogRepository
import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDogRepository() : DogRepository {
        return DogRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideHomeRepository() : HomeRepository {
        return HomeRepositoryImpl()
    }
}