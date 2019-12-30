package com.example.creaturemonmvp.di

import android.app.Application
import androidx.room.Room
import com.example.creaturemonmvp.model.room.CreatureDao
import com.example.creaturemonmvp.model.room.CreatureDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCreatureDatabase(application: Application): CreatureDatabase {
        return Room.databaseBuilder(application, CreatureDatabase::class.java, "creature_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideCreatureDao(creatureDatabase: CreatureDatabase): CreatureDao {
        return creatureDatabase.creatureDao()
    }
}