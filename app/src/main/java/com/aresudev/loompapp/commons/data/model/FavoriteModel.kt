package com.aresudev.loompapp.commons.data.model

import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("song") val song: String,
    @SerializedName("random_string") val randomString: String,
)