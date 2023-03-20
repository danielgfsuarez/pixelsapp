package com.example.core.usecase.popularusecase

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.core.data.PopularRepository
import com.example.core.model.PhotoDomain
import com.example.core.usecase.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: PopularRepository
) : PagingUseCase<GetPopularUseCase.GetPopularParams, PhotoDomain>(), GetPopularUseCase {

    override fun createFlowObservable(params: GetPopularUseCase.GetPopularParams): Flow<PagingData<PhotoDomain>> {
        val pagingSource = repository.fetchPopular(pages = params.pagingConfig.pageSize)
        return Pager(config = params.pagingConfig) { pagingSource }.flow
    }
}