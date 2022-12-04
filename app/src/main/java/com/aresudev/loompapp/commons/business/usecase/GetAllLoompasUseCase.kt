package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import javax.inject.Inject

class GetAllLoompasUseCase @Inject constructor(private val loompaRepository: LoompaRepository){

    suspend operator fun invoke() = loompaRepository.getAllLoompas()
}