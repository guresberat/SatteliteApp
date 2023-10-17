package com.guresberatcan.satteliteapp.data.usecase

import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.repository.LocalRepository
import javax.inject.Inject

class InsertSatelliteToLocalUseCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke(satellite: SatelliteBO) {
        localRepository.insert(satellite)
    }
}