package com.android.imagefeed.di

import com.android.imagefeed.data.repo.ImageListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "https://dl.dropboxusercontent.com/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideImageListRepository(): ImageListRepository {
        return ImageListRepository()
    }
}