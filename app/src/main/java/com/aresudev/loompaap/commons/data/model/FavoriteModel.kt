package com.aresudev.loompaap.commons.data.model

import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("song") val song: String,
    //ToDo: No cojo el random string porque es información innecesaria.
)