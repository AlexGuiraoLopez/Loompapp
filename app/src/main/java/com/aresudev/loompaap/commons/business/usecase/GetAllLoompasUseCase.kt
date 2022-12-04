package com.aresudev.loompaap.commons.business.usecase

import com.aresudev.loompaap.commons.data.repository.LoompaRepository
import javax.inject.Inject

class GetAllLoompasUseCase @Inject constructor(private val loompaRepository: LoompaRepository){

    suspend operator fun invoke() = loompaRepository.getAllLoompas()
}