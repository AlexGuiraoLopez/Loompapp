package com.aresudev.loompaap.commons.business.usecase

import com.aresudev.loompaap.commons.data.repository.LoompaRepository
import javax.inject.Inject

class GetLoompaByIdUseCase @Inject constructor(private val loompaRepository: LoompaRepository) {

    suspend operator fun invoke(loompaId: Int) = loompaRepository.getLoompaById(loompaId)
}