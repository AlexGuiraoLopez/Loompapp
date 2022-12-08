package com.aresudev.loompapp.commons.data.repository

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.commons.data.provider.LoompaPageProvider
import com.aresudev.loompapp.commons.network.LoompaService
import com.aresudev.loompapp.core.error.ExceptionHandler
import com.aresudev.loompapp.core.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoompaRepository @Inject constructor(
    private val loompaService: LoompaService,
    private val loompaPageProvider: LoompaPageProvider
) {

    suspend fun getAllLoompas(): Resource<LoompaPageModel> =
        try {
            loompaService.getLoompas()
        } catch (ex: Exception) {
            Resource.Error(ExceptionHandler.sendExceptionFeedbackMessage(ex))
        }

    suspend fun getAllLoompas(page: Int): Resource<LoompaPageModel> {
        val localData = loompaPageProvider.getData().find { it.pageNumber == page }

        return if (localData != null) {
            Resource.Success(localData)
        } else {
            try {
                val result = loompaService.getLoompas(page)
                result.data?.let { loompaPage -> loompaPageProvider.saveData(loompaPage) }
                result
            } catch (ex: Exception) {
                Resource.Error(ExceptionHandler.sendExceptionFeedbackMessage(ex))
            }
        }
    }

    suspend fun getLoompaById(loompaId: Int): Resource<LoompaModel> =
        try {
            val result = loompaService.getLoompaById(loompaId)
            result
        } catch (ex: Exception) {
            Resource.Error(ExceptionHandler.sendExceptionFeedbackMessage(ex))
        }

}