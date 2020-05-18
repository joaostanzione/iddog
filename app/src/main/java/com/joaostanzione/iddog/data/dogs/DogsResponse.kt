package com.joaostanzione.iddog.data.dogs

import com.google.gson.annotations.SerializedName

data class DogsResponse(
    @SerializedName("category") val category: String?,
    @SerializedName("list") val dogsPhotos: List<String>?
)
