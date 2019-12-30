package com.example.creaturemonmvp.di

import com.example.creaturemonmvp.model.CreatureRepository
import com.example.creaturemonmvp.model.room.RoomRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideCreatureRepository(repository: RoomRepository): CreatureRepository
}