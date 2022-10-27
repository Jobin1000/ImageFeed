package com.android.imagefeed.service

import com.android.imagefeed.data.model.ImageList
import retrofit2.http.GET

/**
 * Define all the API endpoints
 */
interface ImageService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getImagesList(): ImageList
}