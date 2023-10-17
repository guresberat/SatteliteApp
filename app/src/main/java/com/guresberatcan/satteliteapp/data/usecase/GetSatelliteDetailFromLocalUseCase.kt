package com.guresberatcan.satteliteapp.data.usecase

import com.guresberatcan.satteliteapp.data.repository.LocalRepository
import javax.inject.Inject

class GetSatelliteDetailFromLocalUseCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke(id: Int) =
        localRepository.getSatellite().find { it.id == id }
}