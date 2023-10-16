package com.guresberatcan.satteliteapp.di.module

import com.guresberatcan.satteliteapp.data.dao.SatelliteDao
import com.guresberatcan.satteliteapp.data.repository.LocalRepository
import com.guresberatcan.satteliteapp.data.repository.LocalRepositoryImpl
import com.guresberatcan.satteliteapp.data.repository.RemoteRepository
import com.guresberatcan.satteliteapp.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiDataStore: SatelliteDao
    ): RemoteRepository = RemoteRepositoryImpl()

    @Singleton
    @Provides
    fun provideLocalRepository(
        localRepository: SatelliteDao
    ): LocalRepository = LocalRepositoryImpl(localRepository)
}