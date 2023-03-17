package com.example.pixelsapp.wallpapers.ui.fragment.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.model.PhotoDomain
import com.example.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
) : ViewModel() {

    fun popularWallpappers(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(GetPopularUseCase.GetPopularParams(pageConfig()))
            .cachedIn(viewModelScope)
    }

    fun pageConfig() = PagingConfig(pageSize = 40)

}