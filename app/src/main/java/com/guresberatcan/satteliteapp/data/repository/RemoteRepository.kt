package com.guresberatcan.satteliteapp.data.repository

import com.guresberatcan.satteliteapp.data.model.SatelliteList

interface RemoteRepository {

    suspend fun getSatellite(): SatelliteList
}