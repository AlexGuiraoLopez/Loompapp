package com.aresudev.loompapp.commons.network

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.error.LoompaNotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoompaService @Inject constructor(private val api: LoompaApi) {

    suspend fun getLoompas(): LoompaPageModel {
        return withContext(Dispatchers.IO) {
            api.getLoompas().body() ?: throw LoompaNotFoundException(this::class.java.simpleName + "- getLoompas()")
        }
    }

    suspend fun getLoompas(page: Int): LoompaPageModel{
        return withContext(Dispatchers.IO) {
            api.getLoompas(page).body() ?: throw LoompaNotFoundException(this::class.java.simpleName + "- getLoompas()")
        }
    }

    suspend fun getLoompaById(id: Int): LoompaModel {
        return withContext(Dispatchers.IO) {
            api.getLoompaById(id).body() ?: throw LoompaNotFoundException(this::class.java.simpleName + "- getLoompaById()")
        }
    }
}