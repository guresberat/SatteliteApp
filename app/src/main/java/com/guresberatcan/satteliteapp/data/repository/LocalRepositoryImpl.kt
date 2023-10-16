package com.guresberatcan.satteliteapp.data.repository

import com.guresberatcan.satteliteapp.data.dao.SatelliteDao
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.utils.toSatelliteBOList
import com.guresberatcan.satteliteapp.utils.toSatelliteDatabaseEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val localDataSource: SatelliteDao) :
    LocalRepository {
    override suspend fun insert(satellite: SatelliteBO) {
        localDataSource.insertSatellite(satellite.toSatelliteDatabaseEntity())
    }

    override suspend fun getSatellite(): List<SatelliteBO> {
        return localDataSource.getSatellites().toSatelliteBOList()
    }
}