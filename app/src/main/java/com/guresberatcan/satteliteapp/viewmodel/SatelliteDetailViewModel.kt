package com.guresberatcan.satteliteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guresberatcan.satteliteapp.data.model.PositionsBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import com.guresberatcan.satteliteapp.data.usecase.GetPositionsFromRemoteUseCase
import com.guresberatcan.satteliteapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getPositionsFromRemoteUseCase: GetPositionsFromRemoteUseCase
) : ViewModel() {

    val positionSharedFlow: StateFlow<Resource<PositionsBO.PositionList.Position?>>
        get() = _positionSharedFlow
    private val _positionSharedFlow =
        MutableStateFlow<Resource<PositionsBO.PositionList.Position?>>(Resource.Loading)

    private var positionList: List<PositionsBO.PositionList.Position>? = null

    fun getPositions(id: Int, file: String) = viewModelScope.launch {
        positionList = getPositionsFromRemoteUseCase(id, file)
        emitPositions()
    }

    private suspend fun emitPositions() {
        positionList?.forEach { position ->
            _positionSharedFlow.emit(Resource.Success(position))
            delay(3000)
            if (positionList?.last() == position) {
                emitPositions()
            }
        }
    }
}