package com.aresudev.loompaap.commons.network

import com.aresudev.loompaap.commons.data.model.LoompaModel
import com.aresudev.loompaap.core.error.LoompaNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoompaService @Inject constructor(private val api: LoompaApi) {

    suspend fun getLoompas(): List<LoompaModel> {
        return withContext(Dispatchers.IO) {
            api.getLoompas().body() ?: emptyList()
        }
    }

    suspend fun getLoompas(page: Int): List<LoompaModel> {
        return withContext(Dispatchers.IO) {
            api.getLoompas(page).body() ?: emptyList()
        }
    }

    suspend fun getLoompaById(id: Int): LoompaModel {
        return withContext(Dispatchers.IO) {
            api.getLoompaById(id).body() ?: throw LoompaNotFoundException(this::class.java.simpleName + "- getLoompaById()")
        }
    }
}