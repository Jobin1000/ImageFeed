package com.android.imagefeed.di

import com.android.imagefeed.data.repo.ImageListRepository
import com.android.imagefeed.ui.main.ImageFeedViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(imageFeedViewModel: ImageFeedViewModel)
    fun inject(imageListRepository: ImageListRepository)
}