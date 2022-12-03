package com.aresudev.loompaap.commons.data.model

import com.google.gson.annotations.SerializedName

data class LoompaModel(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("favorite") val favorite: FavoriteModel,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val image: String,
    @SerializedName("profession") val profession: String,
    @SerializedName("email") val email: String,
    @SerializedName("age") val age: String,
    @SerializedName("country") val country: String,
    @SerializedName("height") val height: String,
)