package com.example.characters.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.characters.MainCoroutineRule
import com.example.characters.getOrAwaitValueTest
import com.example.characters.ui.MarvelListViewModel
import com.example.core.fragmentTypes.FragmentTypes
import com.example.domain.entity.CharacterRestModel
import com.example.domain.entity.MarvelApiResponse
import com.example.domain.entity.MarvelListModel
import com.example.domain.repository.MarvelListRepository
import com.example.domain.usecase.MarvelListUseCase
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
class MarvelListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val marvelListUseCase = mockk<MarvelListUseCase>()
    private val repository = mockk<MarvelListRepository>()
    private lateinit var viewModel: MarvelListViewModel
    private val characterResponse = mockk<Response<MarvelApiResponse<CharacterRestModel>>>()

    @Before
    fun initiateViewModel() {
        viewModel = MarvelListViewModel(marvelListUseCase)
    }

    @Test
    fun `test marvel list use case`() {
        val recyclerViewModelList = ArrayList<MarvelListModel>()
        val response = Pair(80, recyclerViewModelList)

        coEvery {
            marvelListUseCase.execute(
                FragmentTypes.Characters,
                1
            )
        } returns response

        runTest {
            marvelListUseCase.execute(
                FragmentTypes.Characters,
                1
            )
        }
        verify(exactly = 1) {
            runTest {
                marvelListUseCase.execute(
                    FragmentTypes.Characters,
                    1
                )
            }
        }
    }

    @Test
    fun `test marvel list view model`() = runBlocking {
        val response = ArrayList<MarvelListModel>()

        coEvery { repository.getCharacters(any()) } returns characterResponse.also {
            viewModel.marvelList.value = response
        }

        viewModel.fetchCharacters(FragmentTypes.Characters, 1, false)
        val value = viewModel.marvelList.getOrAwaitValueTest()

        MatcherAssert.assertThat(
            value,
            CoreMatchers.`is`(response)
        )
    }
}
