package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.R
import com.example.weatherapp.data.local.AppDatabase
import com.example.weatherapp.data.local.WeatherDao
import org.koin.dsl.module

object PersistenceModule {

    val persistenceModule = module {
        single { provideAppDatabase(get()) }

        single { provideLocalitiesDao(get()) }

    }

    private fun provideLocalitiesDao(appDatabase: AppDatabase):WeatherDao {
        return appDatabase.weatherDao()
    }

    private fun provideAppDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,
            context.applicationContext.getString(R.string.app_database_name)).build()
    }
}