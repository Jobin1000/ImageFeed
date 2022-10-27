package com.android.imagefeed.data.repo

import com.android.imagefeed.MainApplication
import com.android.imagefeed.data.model.ImageList
import com.android.imagefeed.service.ImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Repository for accessing the data layer
 */
class ImageListRepository {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        MainApplication.getApiComponent().inject(this)
    }

    suspend fun getImageList(): ImageList? {
        // Move the actual network operation out of main thread
        return withContext(Dispatchers.IO) {
            val imageService = retrofit.create(ImageService::class.java)
            imageService.getImagesList()
        }
    }
}