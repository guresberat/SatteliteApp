package com.guresberatcan.satteliteapp.data.repository

import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList

interface RemoteRepository {

    suspend fun getSatellitesList(): SatelliteList

    suspend fun getSatelliteDetail(id: Int): SatelliteBO?
}