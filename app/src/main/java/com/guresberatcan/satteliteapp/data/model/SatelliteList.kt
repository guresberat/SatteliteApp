package com.guresberatcan.satteliteapp.data.model


data class SatelliteList(
    val list: List<SatelliteListBO>
)
data class SatelliteListBO(
    val id: Int? = null,
    val active: Boolean? = null,
    val name: Boolean? = null
)