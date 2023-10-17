package com.guresberatcan.satteliteapp.data.repository

import com.guresberatcan.satteliteapp.data.model.PositionsBO
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList

interface RemoteRepository {

    suspend fun getSatellitesList(file: String): SatelliteList

    suspend fun getSatelliteDetail(id: Int, file: String): SatelliteBO?

    suspend fun getPositions(id: Int, file: String) : List<PositionsBO.PositionList.Position>?
}