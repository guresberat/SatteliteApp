package com.guresberatcan.satteliteapp.data.usecase

import com.guresberatcan.satteliteapp.data.repository.RemoteRepository
import javax.inject.Inject

class GetPositionsFromRemoteUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(id: Int, file: String) =
        remoteRepository.getPositions(id, file)
}