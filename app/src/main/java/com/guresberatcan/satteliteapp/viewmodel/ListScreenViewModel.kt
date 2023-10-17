package com.guresberatcan.satteliteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import com.guresberatcan.satteliteapp.data.usecase.GetSatelliteDetailFromLocalUseCase
import com.guresberatcan.satteliteapp.data.usecase.GetSatelliteDetailFromRemoteUseCase
import com.guresberatcan.satteliteapp.data.usecase.GetSatellitesListUseCase
import com.guresberatcan.satteliteapp.data.usecase.InsertSatelliteToLocalUseCase
import com.guresberatcan.satteliteapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val getSatellitesListUseCase: GetSatellitesListUseCase,
    private val getSatelliteDetailFromRemoteUseCase: GetSatelliteDetailFromRemoteUseCase,
    private val getSatelliteDetailFromLocalUseCase: GetSatelliteDetailFromLocalUseCase,
    private val insertSatelliteToLocalUseCase: InsertSatelliteToLocalUseCase,
) : ViewModel() {

    val satelliteListSharedFlow: StateFlow<Resource<SatelliteList>>
        get() = _satelliteListSharedFlow
    private val _satelliteListSharedFlow =
        MutableStateFlow<Resource<SatelliteList>>(Resource.Loading)

    val satelliteDetailSharedFlow: StateFlow<Resource<SatelliteBO?>>
        get() = _satelliteDetailSharedFlow
    private val _satelliteDetailSharedFlow =
        MutableStateFlow<Resource<SatelliteBO?>>(Resource.Loading)

    fun getSatelliteList(file: String) = viewModelScope.launch {
        _satelliteListSharedFlow.emit(Resource.Success(getSatellitesListUseCase(file)))
    }

    fun getSatelliteDetail(id: Int, name: String, satelliteDetailFilePath: String) =
        viewModelScope.launch {
            val satelliteData =
                getSatelliteDetailFromLocalUseCase(id) ?: getSatelliteDetailFromRemoteUseCase(
                    id,
                    satelliteDetailFilePath
                )
            satelliteData?.name = name
            insertSatellite(satelliteData)
            _satelliteDetailSharedFlow.emit(
                Resource.Success(
                    satelliteData
                )
            )
        }

    private suspend fun insertSatellite(satelliteData: SatelliteBO?) {
        if (satelliteData != null) {
            insertSatelliteToLocalUseCase(satelliteData)
        }
    }
}