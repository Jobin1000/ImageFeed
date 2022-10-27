package com.android.imagefeed.data.model

/**
 * Model class corresponding to the Json response
 */
data class ImageList(
    val rows: List<Row>,
    val title: String
)
