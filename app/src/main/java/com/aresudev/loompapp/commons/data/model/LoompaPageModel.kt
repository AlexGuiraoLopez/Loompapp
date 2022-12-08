package com.aresudev.loompapp.commons.data.model

import com.google.gson.annotations.SerializedName

data class LoompaPageModel(
    @SerializedName("current") var pageNumber: Int,
    @SerializedName("total") var totalLoompas: Int,
    @SerializedName("results") var loompaList: List<LoompaModel>,
)