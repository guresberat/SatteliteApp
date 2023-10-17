package com.guresberatcan.satteliteapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class SatelliteDetailBO : ArrayList<SatelliteBO>()

@Parcelize
data class SatelliteBO(
    val id: Int? = null,
    var name: String? = null,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int? = null,
    @SerializedName("first_flight")
    val firstFlight: String? = null,
    val height: Int? = null,
    val mass: Int? = null
) : Parcelable