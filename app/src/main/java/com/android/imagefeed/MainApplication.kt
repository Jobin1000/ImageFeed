package com.android.imagefeed

import android.app.Application
import com.android.imagefeed.di.ApiComponent
import com.android.imagefeed.di.ApiModule
import com.android.imagefeed.di.DaggerApiComponent


class MainApplication : Application() {

    companion object {
        fun getApiComponent(): ApiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build();
    }
    override fun onCreate() {
        super.onCreate()
    }
}