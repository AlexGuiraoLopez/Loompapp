package com.aresudev.loompapp.commons.business.usecase

import com.aresudev.loompapp.commons.data.model.LoompaModel
import javax.inject.Inject

class FilterLoompaUseCase @Inject constructor(private val getAllLoompasUseCase: GetAllLoompasUseCase) {

    suspend operator fun invoke(page: Int, profession: String?, gender: String?): List<LoompaModel> =
        getAllLoompasUseCase(page).loompaList
            .filterGender(gender)
            .filterProfession(profession)

    private fun List<LoompaModel>.filterGender(gender: String?) =
        gender?.let { filter { it.gender == gender } } ?: this

    private fun List<LoompaModel>.filterProfession(profession: String?) =
        profession?.let { filter { it.profession == profession } } ?: this
}