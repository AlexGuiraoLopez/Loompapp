package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.commons.data.repository.LoompaRepository
import com.aresudev.loompapp.core.error.LoompaNotFoundException
import com.aresudev.loompapp.core.utils.Resource
import javax.inject.Inject
import kotlin.jvm.Throws

class GetAllLoompasUseCase @Inject constructor(private val loompaRepository: LoompaRepository){

    suspend operator fun invoke(): Resource<LoompaPageModel>  = loompaRepository.getAllLoompas()

    suspend operator fun invoke(page: Int): Resource<LoompaPageModel> = loompaRepository.getAllLoompas(page)

}