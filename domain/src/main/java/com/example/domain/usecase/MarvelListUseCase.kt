package com.example.domain.usecase

import com.example.core.fragmentTypes.FragmentTypes
import com.example.domain.entity.MarvelListModel
import com.example.domain.repository.MarvelListRepository

class MarvelListUseCase constructor(private val repository: MarvelListRepository) {
    suspend fun execute(
        type: FragmentTypes,
        page: Int,
        query: String? = null
    ): Pair<Int?, ArrayList<MarvelListModel>> {
        val recyclerViewModelList = ArrayList<MarvelListModel>()

        when (type) {

            FragmentTypes.Characters -> {
                val response = repository.getCharacters(page, query)

                response.body()?.data?.results?.forEach { character ->
                    recyclerViewModelList.add(
                        MarvelListModel(
                            character.id,
                            character.name,
                            character.thumbnail.getUrl()
                        )
                    )
                }
                return Pair(response.body()?.data?.limit, recyclerViewModelList)
            }

            FragmentTypes.Comics -> {
                val response = repository.getComics(page, query)

                response.body()?.data?.results?.forEach { comic ->
                    recyclerViewModelList.add(
                        MarvelListModel(
                            comic.id,
                            comic.title,
                            comic.thumbnail.getUrl()
                        )
                    )
                }
                return Pair(response.body()?.data?.limit, recyclerViewModelList)
            }
            FragmentTypes.Creators -> {
                val response = repository.getCreators(page, query)

                response.body()?.data?.results?.forEach { comic ->
                    recyclerViewModelList.add(
                        MarvelListModel(
                            comic.id,
                            comic.fullName,
                            comic.thumbnail.getUrl()
                        )
                    )
                }
                return Pair(response.body()?.data?.limit, recyclerViewModelList)
            }

            FragmentTypes.Series -> {
                val response = repository.getSeries(page, query)

                response.body()?.data?.results?.forEach { series ->
                    recyclerViewModelList.add(
                        MarvelListModel(
                            series.id,
                            series.title,
                            series.thumbnail.getUrl()
                        )
                    )
                }
                return Pair(response.body()?.data?.limit, recyclerViewModelList)
            }
        }
    }
}
