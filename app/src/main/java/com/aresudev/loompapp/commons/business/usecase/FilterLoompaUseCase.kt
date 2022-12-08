package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.core.utils.Resource
import javax.inject.Inject

class FilterLoompaUseCase @Inject constructor(private val getAllLoompasUseCase: GetAllLoompasUseCase) {

    companion object {
        const val GENERIC_FILTER_ERROR = "An error ocurred while filter loompa list."
    }

    suspend operator fun invoke(page: Int, profession: String?, gender: String?): Resource<List<LoompaModel>> {

        val loompaPage = getAllLoompasUseCase(page)
        val filteredResponse = if (loompaPage.data != null) {
            Resource.Success(loompaPage.data.loompaList.filterGender(gender).filterProfession(profession))
        } else {
            loompaPage.errorMessage?.let { Resource.Error(it) } ?: Resource.Error(GENERIC_FILTER_ERROR)
        }

        return filteredResponse
    }

    private fun List<LoompaModel>.filterGender(gender: String?) =
        gender?.let { filter { it.gender == gender } } ?: this

    private fun List<LoompaModel>.filterProfession(profession: String?) =
        profession?.let { filter { it.profession == profession } } ?: this
}