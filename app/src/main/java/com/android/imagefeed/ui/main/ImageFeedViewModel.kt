package com.android.imagefeed.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.imagefeed.MainApplication
import com.android.imagefeed.data.model.Row
import com.android.imagefeed.data.repo.ImageListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageFeedViewModel : ViewModel() {
    @Inject
    lateinit var imageListRepository : ImageListRepository
    val imageListLiveData = MutableLiveData<List<Row>>()
    val titleLivaData = MutableLiveData<String>()

    init {
        MainApplication.getApiComponent().inject(this)
    }

    fun initData() {
        // Initiate network request only if viewmodel doesn't have data
        if (imageListLiveData.value == null) {
            buildNetworkRequest()
        }
    }

    fun buildNetworkRequest() {
        viewModelScope.launch {
            val images = imageListRepository.getImageList()
            titleLivaData.value = images?.title
            // Show the items having valid title
            imageListLiveData.value = images?.rows?.filter {
                !it.title.isNullOrEmpty()
            }
        }
    }
}