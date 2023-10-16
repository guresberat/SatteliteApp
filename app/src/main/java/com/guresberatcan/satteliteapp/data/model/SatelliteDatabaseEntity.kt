package com.guresberatcan.satteliteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SatelliteDatabase")
data class SatelliteDatabaseEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val costPerLaunch: Int? = null,
    val firstFlight: String? = null,
    val height: Int? = null,
    val mass: Int? = null
)