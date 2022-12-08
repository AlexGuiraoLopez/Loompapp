package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import com.aresudev.loompapp.constants.ExampleTestData
import com.aresudev.loompapp.core.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetLoompaByIdUseCaseTest{
    @RelaxedMockK
    private lateinit var loompaRepository: LoompaRepository

    lateinit var getLoompaByIdUseCase: GetLoompaByIdUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getLoompaByIdUseCase = GetLoompaByIdUseCase(loompaRepository)
    }

    @Test
    fun `GIVEN data from repository WHEN we want to get data THEN get the same data`() = runBlocking {
        //Given
        val id = 1
        val testInitialData = ExampleTestData.generateExampleData().loompaList.find { it.id == id }
        coEvery { loompaRepository.getLoompaById(id) } returns Resource.Success(testInitialData)

        //When
        val result = getLoompaByIdUseCase(id)

        //Then
        assert(result.data == testInitialData)
    }

    @Test
    fun `GIVEN an error from repository WHEN we want to get data THEN get that error `() = runBlocking {
        //Given
        val id = 1
        val resourceError = Resource.Error<LoompaModel>("Error")
        coEvery { loompaRepository.getLoompaById(id) } returns resourceError

        //When
        val result = getLoompaByIdUseCase(id)

        //Then
        assert(result == resourceError)
    }
}