package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class CreatorModel(

    @SerializedName("id")
    var id: Int,

    @SerializedName("fullName")
    var fullName: String,

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail,
)
