package com.guresberatcan.satteliteapp.viewmodel

import com.guresberatcan.satteliteapp.MainDispatcherRule
import com.guresberatcan.satteliteapp.data.model.SatelliteBO
import com.guresberatcan.satteliteapp.data.model.SatelliteList
import com.guresberatcan.satteliteapp.data.usecase.GetSatelliteDetailFromLocalUseCase
import com.guresberatcan.satteliteapp.data.usecase.GetSatelliteDetailFromRemoteUseCase
import com.guresberatcan.satteliteapp.data.usecase.GetSatellitesListUseCase
import com.guresberatcan.satteliteapp.data.usecase.InsertSatelliteToLocalUseCase
import com.guresberatcan.satteliteapp.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var getSatellitesListUseCase: GetSatellitesListUseCase =
        mockk()
    private var getSatelliteDetailFromRemoteUseCase: GetSatelliteDetailFromRemoteUseCase =
        mockk()
    private var getSatelliteDetailFromLocalUseCase: GetSatelliteDetailFromLocalUseCase =
        mockk()
    private var insertSatelliteToLocalUseCase: InsertSatelliteToLocalUseCase =
        mockk()

    private lateinit var viewModel: ListScreenViewModel

    @Before
    fun setup() {
        viewModel = ListScreenViewModel(
            getSatellitesListUseCase,
            getSatelliteDetailFromRemoteUseCase,
            getSatelliteDetailFromLocalUseCase,
            insertSatelliteToLocalUseCase
        )


    }

    @Test
    fun `getSatelliteList returns a list of satellites`() = runTest {
        val satelliteList = SatelliteList()

        coEvery { getSatellitesListUseCase(any()) }.returns(satelliteList)

        viewModel.getSatelliteList("file")

        val resource = viewModel.satelliteListSharedFlow.value
        assertTrue(resource is Resource.Success)
        val satellites = (resource as Resource.Success).value
        assertEquals(satelliteList, satellites)
    }

    @Test
    fun `getSatelliteDetail returns a satellite detail`() = runBlockingTest {
        val id = 1
        val name = "Satellite 1"
        val satelliteDetailFilePath = "sampleFilePath"
        val satelliteData = SatelliteBO(id, name)

        coEvery { getSatelliteDetailFromLocalUseCase(any()) }.returns(null)

        coEvery { getSatelliteDetailFromRemoteUseCase(any(), any()) }.returns(satelliteData)

        coEvery { insertSatelliteToLocalUseCase(any()) } just runs

        viewModel.getSatelliteDetail(id, name, satelliteDetailFilePath)

        val resource = viewModel.satelliteDetailSharedFlow.value
        assertTrue(resource is Resource.Success)
        val detail = (resource as Resource.Success).value
        assertEquals(satelliteData, detail)

        coVerify { insertSatelliteToLocalUseCase(satelliteData) }
    }
}
