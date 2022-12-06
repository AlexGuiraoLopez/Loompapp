package com.aresudev.loompapp.commons.data.repository

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.commons.data.provider.LoompaPageProvider
import com.aresudev.loompapp.commons.network.LoompaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoompaRepository @Inject constructor(
    private val loompaService: LoompaService,
    private val loompaPageProvider: LoompaPageProvider
) {

    suspend fun getAllLoompas(): LoompaPageModel = loompaService.getLoompas()

    suspend fun getAllLoompas(page: Int): LoompaPageModel {
        val localData = loompaPageProvider.getData().find { it.pageNumber == page }

        return if (localData == null) {
            val result = loompaService.getLoompas(page)
            loompaPageProvider.saveData(result)
            result
        } else {
            localData
        }
    }

    suspend fun getLoompaById(loompaId: Int): LoompaModel = loompaService.getLoompaById(loompaId)

}