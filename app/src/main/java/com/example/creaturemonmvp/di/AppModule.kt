package com.example.creaturemonmvp.di

import android.app.Application
import androidx.room.Room
import com.example.creaturemonmvp.model.CreatureRepository
import com.example.creaturemonmvp.model.room.CreatureDao
import com.example.creaturemonmvp.model.room.CreatureDatabase
import com.example.creaturemonmvp.model.room.RoomRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    // CreatureDatabase instance
    single { provideCreatureDatabase(androidApplication()) }
    // CreatureDao instance
    single { provideCreatureDao(get()) }
    // CreatureRepository instance
    single<CreatureRepository> { provideRoomRepository(get()) }
}

private fun provideCreatureDatabase(application: Application): CreatureDatabase {
    return Room.databaseBuilder(application, CreatureDatabase::class.java, "creature_database")
        .build()
}

private fun provideCreatureDao(creatureDatabase: CreatureDatabase): CreatureDao {
    return creatureDatabase.creatureDao()
}

private fun provideRoomRepository(creatureDao: CreatureDao): RoomRepository {
    return RoomRepository(creatureDao)
}