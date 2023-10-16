package com.guresberatcan.satteliteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guresberatcan.satteliteapp.data.dao.SatelliteDao


@Database(entities = [SatelliteDatabase::class], version = 1)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao

    companion object {
        const val DATABASE_NAME = "SatelliteDatabase"
    }
}