package com.guresberatcan.satteliteapp.data.usecase

import com.guresberatcan.satteliteapp.data.repository.RemoteRepository
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(id: Int) = remoteRepository.getSatelliteDetail(id)
}