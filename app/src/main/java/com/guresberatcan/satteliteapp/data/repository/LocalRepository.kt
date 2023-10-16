package com.guresberatcan.satteliteapp.data.repository

import com.guresberatcan.satteliteapp.data.model.SatelliteBO

interface LocalRepository {

    suspend fun insert(satellite: SatelliteBO)

    suspend fun getSatellite(): List<SatelliteBO>
}