package com.example.pixelsapp.wallpapers.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import com.example.core.usecase.popularusecase.GetPopularUseCase
import com.example.testing.MainCoroutinesRule
import com.example.testing.model.WallpapersFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
internal class PopularViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()

    @Mock
    lateinit var popularUseCase: GetPopularUseCase
    private lateinit var popularViewModel: PopularViewModel

    @Before
    fun setup() {
        popularViewModel = PopularViewModel(popularUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {

        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock))

        val result = popularViewModel.popularWallpappers()

        Assert.assertNotNull(result.first())
    }

    @Test(expected = java.lang.RuntimeException::class)
    fun `Should return an empity PaggingData when an error ossurred`() = runTest {

        whenever(popularUseCase(any())).thenThrow(RuntimeException())

        popularViewModel.popularWallpappers()
    }



    private val wallpappersFactory = WallpapersFactory()
    private val getPagingDataMock = PagingData.from(
        listOf(
            wallpappersFactory.create(WallpapersFactory.Photo.PhotoDomainSuccess),
            wallpappersFactory.create(WallpapersFactory.Photo.PhotoDomainSuccess)
        )
    )
}
