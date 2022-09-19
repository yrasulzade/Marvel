package com.example.domain.usecase

import com.example.core.fragmentTypes.FragmentTypes
import com.example.domain.entity.MarvelListModel
import com.example.domain.repository.MarvelDetailRepository
import javax.inject.Inject

class MarvelDetailsUseCase @Inject constructor(private val repository: MarvelDetailRepository) {
    suspend fun execute(
        id: Int,
        type: String
    ): MarvelListModel? {
        var recyclerViewModelList: MarvelListModel? = null

        when (type) {

            FragmentTypes.Characters.name -> {
                val response = repository.getCharacterDetails(id)

                response.body()?.data?.results?.forEach { character ->
                    recyclerViewModelList = MarvelListModel(
                        character.id,
                        character.name,
                        character.thumbnail.getUrl()
                    )
                }
            }

            FragmentTypes.Comics.name -> {
                val response = repository.getComicsDetails(id)

                response.body()?.data?.results?.forEach { character ->
                    recyclerViewModelList = MarvelListModel(
                        character.id,
                        character.title,
                        character.thumbnail.getUrl()
                    )
                }
            }
            FragmentTypes.Creators.name -> {
                val response = repository.getCreatorDetails(id)

                response.body()?.data?.results?.forEach { character ->
                    recyclerViewModelList = MarvelListModel(
                        character.id,
                        character.fullName,
                        character.thumbnail.getUrl()
                    )
                }
            }
            FragmentTypes.Series.name -> {
                val response = repository.getSeriesDetails(id)

                response.body()?.data?.results?.forEach { character ->
                    recyclerViewModelList = MarvelListModel(
                        character.id,
                        character.title,
                        character.thumbnail.getUrl()
                    )
                }
            }
        }
        return recyclerViewModelList
    }
}
