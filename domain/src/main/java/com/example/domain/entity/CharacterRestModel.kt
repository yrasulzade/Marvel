package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class CharacterRestModel(

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail,
)
