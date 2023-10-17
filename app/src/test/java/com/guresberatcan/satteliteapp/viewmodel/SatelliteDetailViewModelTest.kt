package com.guresberatcan.satteliteapp.viewmodel

import com.guresberatcan.satteliteapp.MainDispatcherRule
import com.guresberatcan.satteliteapp.data.model.PositionsBO
import com.guresberatcan.satteliteapp.data.usecase.GetPositionsFromRemoteUseCase
import com.guresberatcan.satteliteapp.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SatelliteDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var getPositionsFromRemoteUseCase: GetPositionsFromRemoteUseCase = mockk()

    private lateinit var viewModel: SatelliteDetailViewModel

    @Before
    fun setup() {
        viewModel = SatelliteDetailViewModel(getPositionsFromRemoteUseCase)
    }

    @Test
    fun `getPositions returns a list of positions`() = runBlockingTest {
        val id = 1
        val file = "sampleFilePath"
        val positionsList = listOf(
            PositionsBO.PositionList.Position(1.0, 10.0),
            PositionsBO.PositionList.Position(2.0, 30.0)
        )

        coEvery { getPositionsFromRemoteUseCase(id, file) }.returns(positionsList)

        viewModel.getPositions(id, file)

        kotlinx.coroutines.delay(5000)

        val resource = viewModel.positionSharedFlow.value
        assertTrue(resource is Resource.Success)
        val positions = (resource as Resource.Success).value

        assertEquals(positionsList[0].posX, positions?.posX)
        assertEquals(positionsList[0].posY, positions?.posY)
    }
}
