package com.example.domain.repository

import com.example.domain.entity.*
import retrofit2.Response

interface MarvelListRepository {
    suspend fun getCharacters(
        page: Int,
        query: String? = null,
    ): Response<MarvelApiResponse<CharacterRestModel>>

    suspend fun getComics(
        page: Int,
        query: String? = null,
    ): Response<MarvelApiResponse<ComicsModel>>

    suspend fun getCreators(
        page: Int,
        query: String? = null,
    ): Response<MarvelApiResponse<CreatorModel>>

    suspend fun getSeries(
        page: Int,
        query: String? = null,
    ): Response<MarvelApiResponse<SeriesModel>>
}
