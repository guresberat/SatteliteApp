package com.guresberatcan.satteliteapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guresberatcan.satteliteapp.data.model.SatelliteDatabaseEntity

@Dao
interface SatelliteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatellite(satellite: SatelliteDatabaseEntity)

    @Query("SELECT * FROM SatelliteDatabase")
    suspend fun getSatellites(): List<SatelliteDatabaseEntity>
}