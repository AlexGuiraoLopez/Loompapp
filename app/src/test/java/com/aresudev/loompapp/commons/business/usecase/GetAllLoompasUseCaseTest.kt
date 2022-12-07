package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import com.aresudev.loompapp.constants.ExampleTestData
import com.aresudev.loompapp.core.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllLoompasUseCaseTest{

    @RelaxedMockK
    private lateinit var loompaRepository: LoompaRepository

    lateinit var getAllLoompasUseCase: GetAllLoompasUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getAllLoompasUseCase = GetAllLoompasUseCase(loompaRepository)
    }

    @Test
    fun `GIVEN data from repository WHEN we want to get data THEN get the same data`() = runBlocking {
        //Given
        val page = 1
        val testInitialData = ExampleTestData.generateExampleData()
        coEvery { loompaRepository.getAllLoompas(page) } returns Resource.Success(testInitialData)

        //When
        val result = getAllLoompasUseCase(page)

        //Then
        assert(result.data == testInitialData)
    }

    @Test
    fun `GIVEN an error from repository WHEN we want to get data THEN get that error `() = runBlocking {
        //Given
        val page = 1
        val resourceError = Resource.Error<LoompaPageModel>("Error")
        coEvery { loompaRepository.getAllLoompas(page) } returns resourceError

        //When
        val result = getAllLoompasUseCase(page)

        //Then
        assert(result == resourceError)
    }
}