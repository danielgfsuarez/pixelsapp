package com.example.pixelsapp.wallpapers.framework.di

import com.example.core.data.PopularRepository
import com.example.core.data.RemoteDataSource
import com.example.pixelsapp.wallpapers.framework.network.response.DataWrapperResponse
import com.example.pixelsapp.wallpapers.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Binds
    fun bindPopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindRemnoteDataSource(): RemoteDataSource<DataWrapperResponse>

}