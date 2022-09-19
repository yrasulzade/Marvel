package com.example.domain.repository

import com.example.domain.entity.*
import retrofit2.Response

interface MarvelDetailRepository {

    suspend fun getCharacterDetails(
        id: Int,
    ): Response<MarvelApiResponse<CharacterRestModel>>

    suspend fun getComicsDetails(
        id: Int,
    ): Response<MarvelApiResponse<ComicsModel>>

    suspend fun getCreatorDetails(
        id: Int,
    ): Response<MarvelApiResponse<CreatorModel>>

    suspend fun getSeriesDetails(
        id: Int,
    ): Response<MarvelApiResponse<SeriesModel>>
}
