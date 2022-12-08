package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.constants.ExampleTestData
import com.aresudev.loompapp.core.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilterLoompaUseCaseTest {

    @RelaxedMockK
    lateinit var getAllLoompasUseCase: GetAllLoompasUseCase

    lateinit var filterLoompaUseCase: FilterLoompaUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        filterLoompaUseCase = FilterLoompaUseCase(getAllLoompasUseCase)
    }

    @Test
    fun `GIVEN gender filter but not profession filter WHEN filtering list THEN final result doesn't include any  gender items different than filter`() =
        runBlocking {
            //Given
            val page = 1
            val genderFilter = "m"
            val professionFilter = null

            coEvery { getAllLoompasUseCase(page) } returns Resource.Success(ExampleTestData.generateExampleData())

            //When
            val result = filterLoompaUseCase(page, gender = genderFilter, profession = professionFilter)

            //Then
            val opositeGenderItems = result.data?.filterNot { it.gender == genderFilter }
            assert(opositeGenderItems?.size == 0)
        }

    @Test
    fun `GIVEN profession filter but not gender one WHEN filtering list THEN final result doesn't include any profession items different than filter`() =
        runBlocking {
            //Given
            val page = 1
            val genderFilter = null
            val professionFilter = "developer"

            coEvery { getAllLoompasUseCase(page) } returns Resource.Success(ExampleTestData.generateExampleData())

            //When
            val result = filterLoompaUseCase(page, gender = genderFilter, profession = professionFilter)

            //Then
            val opositeGenderItems = result.data?.filterNot { it.profession == professionFilter }
            assert(opositeGenderItems?.size == 0)
        }

    @Test
    fun `GIVEN no active filters WHEN filtering list THEN final result doesn't change from initial data`() =
        runBlocking {
            //Given
            val page = 1
            val genderFilter = null
            val professionFilter = null
            val testInitialData = ExampleTestData.generateExampleData()

            coEvery { getAllLoompasUseCase(page) } returns Resource.Success(testInitialData)

            //When
            val result = filterLoompaUseCase(page, gender = genderFilter, profession = professionFilter)

            //Then
            assert(result.data == testInitialData.loompaList)
        }

    @Test
    fun `GIVEN all active filters WHEN filtering list THEN final result doesn't include any profession or gender items different than filter `() =
        runBlocking {
            //Given
            val page = 1
            val genderFilter = "m"
            val professionFilter = "developer"
            val testInitialData = ExampleTestData.generateExampleData()

            coEvery { getAllLoompasUseCase(page) } returns Resource.Success(testInitialData)

            //When
            val result = filterLoompaUseCase(page, gender = genderFilter, profession = professionFilter)

            //Then
            val opositeGenderItems = result.data?.filterNot { it.profession == professionFilter && it.gender == genderFilter }
            assert(opositeGenderItems?.size == 0)
        }

    @Test
    fun `GIVEN an error WHEN receiving loompa list to filter THEN return resource error `() =
        runBlocking {
            //Given
            val page = 1
            val genderFilter = "m"
            val professionFilter = "developer"
            val testErrorMessage = "Test error"
            coEvery { getAllLoompasUseCase(page) } returns Resource.Error(testErrorMessage)

            //When
            val result = filterLoompaUseCase(page, gender = genderFilter, profession = professionFilter)

            //Then
            assert(result.errorMessage == testErrorMessage)
        }
}