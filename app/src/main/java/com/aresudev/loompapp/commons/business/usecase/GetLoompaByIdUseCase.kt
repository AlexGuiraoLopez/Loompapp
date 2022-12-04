package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import javax.inject.Inject

class GetLoompaByIdUseCase @Inject constructor(private val loompaRepository: LoompaRepository) {

    suspend operator fun invoke(loompaId: Int) = loompaRepository.getLoompaById(loompaId)
}