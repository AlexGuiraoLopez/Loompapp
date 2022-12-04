package com.aresudev.loompapp.commons.data.repository

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.commons.data.provider.AllLoompaProvider
import com.aresudev.loompapp.commons.network.LoompaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoompaRepository @Inject constructor(
    private val loompaService: LoompaService,
    private val allLoompaProvider: AllLoompaProvider
) {

    suspend fun getAllLoompas(): LoompaPageModel =
        if (allLoompaProvider.hasData()) {
            allLoompaProvider.getData()
        } else {
            val response = loompaService.getLoompas()
            allLoompaProvider.saveData(response)
            response
        }

    suspend fun getAllLoompas(page: Int): LoompaPageModel = loompaService.getLoompas(page)

    suspend fun getLoompaById(loompaId: Int): LoompaModel = loompaService.getLoompaById(loompaId)

}