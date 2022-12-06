package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.core.utils.Resource
import javax.inject.Inject

class FilterLoompaUseCase @Inject constructor(private val getAllLoompasUseCase: GetAllLoompasUseCase) {

    suspend operator fun invoke(page: Int, profession: String?, gender: String?): Resource<List<LoompaModel>> {
        val loompaPage = getAllLoompasUseCase(page).data
        val filteredResponse = if (loompaPage != null) {
            Resource.Success(loompaPage.loompaList.filterGender(gender).filterProfession(profession))
        } else {
            Resource.Error("No filters") //ToDo: Handle message constant.
        }
        return filteredResponse
    }
    private fun List<LoompaModel>.filterGender(gender: String?) =
        gender?.let { filter { it.gender == gender } } ?: this

    private fun List<LoompaModel>.filterProfession(profession: String?) =
        profession?.let { filter { it.profession == profession } } ?: this
}