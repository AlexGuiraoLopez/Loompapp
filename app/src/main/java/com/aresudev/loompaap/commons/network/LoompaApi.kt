package com.aresudev.loompaap.commons.network

import com.aresudev.loompaap.commons.data.model.LoompaModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LoompaApi {
    @GET("/oompa-loompas")
    fun getLoompas(@Query("page") page: Int? = 1): Response<List<LoompaModel>>

    @GET("/oompa-loompas/{id}")
    fun getLoompaById(@Path("id") id: Int?): Response<LoompaModel>

}