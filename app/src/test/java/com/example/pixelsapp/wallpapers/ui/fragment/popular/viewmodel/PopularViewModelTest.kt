package com.example.pixelsapp.wallpapers.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.model.PhotoDomain
import com.example.core.model.SrcDomain
import com.example.core.usecase.popularusecase.GetPopularUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
internal class PopularViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Mock
    lateinit var popularUseCase: GetPopularUseCase
    //    private val popularUseCase = mock<GetPopularUseCase>()

    private lateinit var popularViewModel: PopularViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        popularViewModel = PopularViewModel(popularUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {
        val expected = getPagingDataMock()
        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock()))
        val result = popularViewModel.popularWallpappers()

        Assert.assertNotNull(result.first())
    }

    private fun getPagingDataMock() = PagingData.from(
        listOf(domain, domain, domain, domain, domain, domain)
    )

    private val domain = PhotoDomain(
        description = "Free stock photo of busy, cbd, city",
        avgColor = "#464239",
        height = 6000,
        id = 15811217,
        liked = false,
        photographer = "Mateusz Walendzik",
        photographerId = 178202044,
        photographerUrl = "https://www.pexels.com/@mateusz",
        srcDomain = SrcDomain(
            landscape = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            large = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
            large2x = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
            medium = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=350",
            original = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg",
            portrait = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
            small = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&h=130",
            tiny = "https://images.pexels.com/photos/15811217/pexels-photo-15811217.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
        ),
        url = "https://www.pexels.com/photo/melting-light-city-road-15811217/",
        width = 4000
    )

}
