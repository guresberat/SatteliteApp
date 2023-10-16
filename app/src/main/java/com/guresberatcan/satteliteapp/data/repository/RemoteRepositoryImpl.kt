package com.guresberatcan.satteliteapp.data.repository

import com.google.gson.Gson
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor() :
    RemoteRepository {
    override suspend fun getSatellite(): SatelliteList {
        return Gson().fromJson("satellite-list.json", SatelliteList::class.java)
    }
}