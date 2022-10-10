package com.example.data.reposiory

import com.example.data.api.ApiService
import com.example.domain.entity.*
import com.example.domain.repository.MarvelDetailRepository
import retrofit2.Response

class MarvelDetailRepositoryImpl constructor(private val apiService: ApiService) :
    MarvelDetailRepository {

    override suspend fun getCharacterDetails(id: Int): Response<MarvelApiResponse<CharacterRestModel>> {
        return apiService.getCharacterDetails(id)
    }

    override suspend fun getComicsDetails(id: Int): Response<MarvelApiResponse<ComicsModel>> {
        return apiService.getComicsDetails(id)
    }

    override suspend fun getCreatorDetails(id: Int): Response<MarvelApiResponse<CreatorModel>> {
        return apiService.getCreatorDetails(id)
    }

    override suspend fun getSeriesDetails(id: Int): Response<MarvelApiResponse<SeriesModel>> {
        return apiService.getSeriesDetails(id)
    }
}
