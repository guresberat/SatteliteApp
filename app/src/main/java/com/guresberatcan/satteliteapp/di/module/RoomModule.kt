package com.guresberatcan.satteliteapp.di.module

import android.content.Context
import androidx.room.Room
import com.guresberatcan.satteliteapp.data.dao.SatelliteDao
import com.guresberatcan.satteliteapp.data.database.SatelliteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideSatelliteDatabase(@ApplicationContext context: Context): SatelliteDatabase {
        return Room.databaseBuilder(
            context,
            SatelliteDatabase::class.java,
            SatelliteDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSatelliteDao(satelliteDatabase: SatelliteDatabase): SatelliteDao {
        return satelliteDatabase.satelliteDao()
    }
}