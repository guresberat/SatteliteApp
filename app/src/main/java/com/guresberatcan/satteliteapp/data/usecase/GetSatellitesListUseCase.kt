package com.guresberatcan.satteliteapp.data.usecase

import com.guresberatcan.satteliteapp.data.repository.RemoteRepository
import javax.inject.Inject

class GetSatellitesListUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(file: String) = remoteRepository.getSatellitesList(file)
}