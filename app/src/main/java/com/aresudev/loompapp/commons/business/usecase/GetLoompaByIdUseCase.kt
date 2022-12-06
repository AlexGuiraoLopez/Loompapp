package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import com.aresudev.loompapp.core.utils.Resource
import javax.inject.Inject

class GetLoompaByIdUseCase @Inject constructor(private val loompaRepository: LoompaRepository) {

    suspend operator fun invoke(loompaId: Int): Resource<LoompaModel> = loompaRepository.getLoompaById(loompaId)
}