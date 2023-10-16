package com.guresberatcan.satteliteapp.data.repository

import com.google.gson.Gson
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList

class RemoteRepositoryImpl:
    RemoteRepository {
    override suspend fun getSatellitesList(file: String): SatelliteList {
        return Gson().fromJson(file,SatelliteList::class.java)
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteBO? {
        return SatelliteBO()
        //Gson().fromJson(
            //"satellite-detail.json",
          //  SatelliteDetailBO::class.java
       // ).list.find { it.id == id }
    }
}