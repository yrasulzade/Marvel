package com.example.data.api

import com.example.data.util.ApiConstants.MARVEL_API_HASH
import com.example.data.util.ApiConstants.MARVEL_API_PUBLIC_KEY
import com.example.data.util.ApiConstants.MARVEL_API_TS
import com.example.data.util.Endpoints.API_KEY
import com.example.data.util.Endpoints.CHARACTERS
import com.example.data.util.Endpoints.CHARACTER_DETAILS
import com.example.data.util.Endpoints.COMICS
import com.example.data.util.Endpoints.COMIC_DETAILS
import com.example.data.util.Endpoints.CREATORS
import com.example.data.util.Endpoints.CREATOR_DETAILS
import com.example.data.util.Endpoints.SERIES
import com.example.data.util.Endpoints.SERIES_DETAILS
import com.example.domain.entity.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("$CHARACTERS$API_KEY")
    suspend fun getCharacters(
        @Query("offset") page: Int,
        @Query("nameStartsWith") query: String? = null
    ): Response<MarvelApiResponse<CharacterRestModel>>

    @GET("$COMICS$API_KEY")
    suspend fun getComics(
        @Query("offset") page: Int,
        @Query("titleStartsWith") query: String? = null
    ): Response<MarvelApiResponse<ComicsModel>>

    @GET("$CREATORS$API_KEY")
    suspend fun getCreators(
        @Query("offset") page: Int,
        @Query("nameStartsWith") query: String? = null
    ): Response<MarvelApiResponse<CreatorModel>>

    @GET("$SERIES$API_KEY")
    suspend fun getSeries(
        @Query("offset") page: Int,
        @Query("titleStartsWith") query: String? = null
    ): Response<MarvelApiResponse<SeriesModel>>

    @GET("$CHARACTER_DETAILS$API_KEY")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Response<MarvelApiResponse<CharacterRestModel>>

    @GET("$COMIC_DETAILS$API_KEY")
    suspend fun getComicsDetails(
        @Path("id") id: Int
    ): Response<MarvelApiResponse<ComicsModel>>

    @GET("$CREATOR_DETAILS$API_KEY")
    suspend fun getCreatorDetails(
        @Path("id") id: Int
    ): Response<MarvelApiResponse<CreatorModel>>

    @GET("$SERIES_DETAILS$API_KEY")
    suspend fun getSeriesDetails(
        @Path("id") id: Int
    ): Response<MarvelApiResponse<SeriesModel>>
}
