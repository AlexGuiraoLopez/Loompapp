package com.aresudev.loompapp.commons.data.model

import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("color") val color: String,
    @SerializedName("food") val food: String,
    @SerializedName("song") val song: String,
    //ToDo: Ask if "random_string" is mandatory or its a trap!
)