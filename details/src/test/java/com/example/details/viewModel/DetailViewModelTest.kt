package com.example.details.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.fragmentTypes.FragmentTypes
import com.example.details.DetailsViewModel
import com.example.details.MainCoroutineRule
import com.example.details.getOrAwaitValueTest
import com.example.domain.entity.CharacterRestModel
import com.example.domain.entity.MarvelApiResponse
import com.example.domain.entity.MarvelListModel
import com.example.domain.repository.MarvelDetailRepository
import com.example.domain.usecase.MarvelDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class DetailViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val marvelDetailsUseCase = mockk<MarvelDetailsUseCase>()
    private val repository = mockk<MarvelDetailRepository>()
    private lateinit var detailsViewModel: DetailsViewModel
    private val characterResponse = mockk<Response<MarvelApiResponse<CharacterRestModel>>>()

    @Before
    fun initiateViewModel() {
        detailsViewModel = DetailsViewModel(marvelDetailsUseCase)
    }

    @Test
    fun `test details use case`() {
        val response = MarvelListModel(1, "some title", "some thumbnail")

        coEvery {
            marvelDetailsUseCase.execute(
                99,
                FragmentTypes.Characters.name
            )
        } returns response

        runTest {
            marvelDetailsUseCase.execute(
                99,
                FragmentTypes.Characters.name
            )
        }
        verify(exactly = 1) {
            runTest {
                marvelDetailsUseCase.execute(
                    99,
                    FragmentTypes.Characters.name
                )
            }
        }
    }

    @Test
    fun `character details view model`() = runBlocking {
        val response = MarvelListModel(1, "title", "thumbnail")

        coEvery { repository.getCharacterDetails(any()) } returns characterResponse.also {
            detailsViewModel.details.value = response
        }

        detailsViewModel.fetchCharacterDetails(1, FragmentTypes.Characters.name)
        val value = detailsViewModel.details.getOrAwaitValueTest()

        MatcherAssert.assertThat(
            value,
            CoreMatchers.`is`(response)
        )
    }
}
