package com.aresudev.loompapp.commons.network

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoompaApi {

    @GET("oompa-loompas")
    suspend fun getLoompas(@Query("page") page: Int? = 1): Response<LoompaPageModel>

    @GET("oompa-loompas/{id}")
    suspend fun getLoompaById(@Path("id") id: Int?): Response<LoompaModel>

}