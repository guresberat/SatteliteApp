package com.guresberatcan.satteliteapp.utils

import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteDatabaseEntity

fun SatelliteDatabaseEntity.toSatelliteBO(): SatelliteBO {
    return SatelliteBO(id, name, costPerLaunch, firstFlight, height, mass)
}

fun SatelliteBO.toSatelliteDatabaseEntity(): SatelliteDatabaseEntity {
    return SatelliteDatabaseEntity(id, name, costPerLaunch, firstFlight, height, mass)
}

fun List<SatelliteDatabaseEntity>.toSatelliteBOList(): List<SatelliteBO> {
    return arrayListOf<SatelliteBO>().apply {
        this@toSatelliteBOList.forEach { add(it.toSatelliteBO()) }
    }
}

fun List<SatelliteBO>.toSatelliteDatabaseEntityList(): List<SatelliteDatabaseEntity> {
    return arrayListOf<SatelliteDatabaseEntity>().apply {
        this@toSatelliteDatabaseEntityList.forEach { add(it.toSatelliteDatabaseEntity()) }
    }
}