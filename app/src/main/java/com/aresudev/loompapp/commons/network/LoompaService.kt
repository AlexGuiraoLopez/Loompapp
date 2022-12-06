package com.aresudev.loompapp.commons.network

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.error.LoompaNotFoundException
import com.aresudev.loompapp.core.utils.Resource
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject
import kotlin.jvm.Throws

class LoompaService @Inject constructor(private val api: LoompaApi) {

    suspend fun getLoompas(): Resource<LoompaPageModel> {
        return withContext(Dispatchers.IO) {
            val apiResponseData = api.getLoompas().body()
            if (apiResponseData != null) {
                Resource.Success(apiResponseData)
            } else {
                throw LoompaNotFoundException()
            }
        }
    }

    suspend fun getLoompas(page: Int): Resource<LoompaPageModel> {
        return withContext(Dispatchers.IO) {
            val apiResponseData = api.getLoompas(page).body()
            if (apiResponseData != null) {
                Resource.Success(apiResponseData)
            } else {
                throw LoompaNotFoundException()
            }
        }
    }

    suspend fun getLoompaById(id: Int): Resource<LoompaModel> {
        return withContext(Dispatchers.IO) {
            val apiResponseData = api.getLoompaById(id).body()
            if (apiResponseData != null) {
                Resource.Success(apiResponseData)
            } else {
                throw LoompaNotFoundException()
            }
        }
    }
}