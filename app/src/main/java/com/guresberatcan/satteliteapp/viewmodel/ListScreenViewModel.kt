package com.guresberatcan.satteliteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import com.guresberatcan.satteliteapp.data.usecase.GetSatellitesListUseCase
import com.guresberatcan.satteliteapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val getSatellitesListUseCase: GetSatellitesListUseCase
) : ViewModel() {

    val satelliteListSharedFlow: StateFlow<Resource<SatelliteList>>
        get() = _satelliteListSharedFlow

    private val _satelliteListSharedFlow = MutableStateFlow<Resource<SatelliteList>>(Resource.Loading)

    fun getSatelliteList(file: String) = viewModelScope.launch {
        _satelliteListSharedFlow.emit(Resource.Success(getSatellitesListUseCase(file)))
    }
}