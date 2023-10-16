package com.guresberatcan.satteliteapp.data.model

class SatelliteList : ArrayList<SatelliteListItem>()

data class SatelliteListItem(
    val active: Boolean,
    val id: Int,
    val name: String
)