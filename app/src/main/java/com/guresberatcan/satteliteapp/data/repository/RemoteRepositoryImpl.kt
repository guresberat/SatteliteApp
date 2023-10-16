package com.guresberatcan.satteliteapp.data.repository

import com.google.gson.Gson
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteDetailBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor() :
    RemoteRepository {
    override suspend fun getSatellitesList(): SatelliteList {
        return Gson().fromJson("satellite-list.json", SatelliteList::class.java)
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteBO? {
        return Gson().fromJson(
            "satellite-detail.json",
            SatelliteDetailBO::class.java
        ).list.find { it.id == id }
    }
}