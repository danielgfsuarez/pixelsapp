package com.example.pixelsapp.wallpapers.framework.network.api

import com.example.pixelsapp.wallpapers.framework.network.response.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpapersApi {
    @GET("v1/curated")
    suspend fun getPopularWallpapers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): DataWrapperResponse
}