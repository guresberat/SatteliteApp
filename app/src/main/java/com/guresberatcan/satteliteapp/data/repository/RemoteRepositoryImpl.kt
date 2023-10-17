package com.guresberatcan.satteliteapp.data.repository

import com.google.gson.Gson
import com.guresberatcan.satteliteapp.data.model.PositionsBO
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteDetailBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList

class RemoteRepositoryImpl:
    RemoteRepository {
    override suspend fun getSatellitesList(file: String): SatelliteList {
        return Gson().fromJson(file,SatelliteList::class.java)
    }

    override suspend fun getSatelliteDetail(id: Int, file: String): SatelliteBO? {
        return Gson().fromJson(file, SatelliteDetailBO::class.java).find { it.id == id }
    }

    override suspend fun getPositions(id: Int, file: String): List<PositionsBO.PositionList.Position>? {
        return Gson().fromJson(file, PositionsBO::class.java).list.find { it.id.toInt() == id }?.positions
    }
}