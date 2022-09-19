package com.example.data.reposiory

import com.example.data.api.ApiService
import com.example.domain.entity.*
import com.example.domain.repository.MarvelListRepository
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MarvelListRepository {

    override suspend fun getCharacters(
        page: Int,
        query: String?
    ): Response<MarvelApiResponse<CharacterRestModel>> {
        return apiService.getCharacters(page, query)
    }

    override suspend fun getComics(
        page: Int,
        query: String?
    ): Response<MarvelApiResponse<ComicsModel>> {
        return apiService.getComics(page, query)
    }

    override suspend fun getCreators(
        page: Int,
        query: String?
    ): Response<MarvelApiResponse<CreatorModel>> {
        return apiService.getCreators(page, query)
    }

    override suspend fun getSeries(
        page: Int,
        query: String?
    ): Response<MarvelApiResponse<SeriesModel>> {
        return apiService.getSeries(page, query)
    }
}
