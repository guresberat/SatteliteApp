package com.guresberatcan.satteliteapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatelliteBO(
    val id: Int? = null,
    val costPerLaunch: Int? = null,
    val firstFlight: String? = null,
    val height: Int? = null,
    val mass: Int? = null
) : Parcelable