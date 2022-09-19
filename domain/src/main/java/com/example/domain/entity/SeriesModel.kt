package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class SeriesModel(

    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail
)
