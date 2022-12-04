package com.aresudev.loompaap.commons.data.repository

import com.aresudev.loompaap.commons.data.model.LoompaModel
import com.aresudev.loompaap.commons.data.provider.AllLoompaProvider
import com.aresudev.loompaap.commons.network.LoompaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoompaRepository @Inject constructor(
    private val loompaService: LoompaService,
    private val allLoompaProvider: AllLoompaProvider
) {

    suspend fun getAllLoompas(): List<LoompaModel> =
        if (allLoompaProvider.hasData()) {
            allLoompaProvider.getData()
        } else {
            val response = loompaService.getLoompas()
            allLoompaProvider.saveData(response)
            response
        }

    suspend fun getAllLoompas(page: Int): List<LoompaModel> = loompaService.getLoompas(page)

    suspend fun getLoompaById(loompaId: Int): LoompaModel = loompaService.getLoompaById(loompaId)

}