package com.android.imagefeed.ui.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.imagefeed.R
import com.android.imagefeed.data.model.Row
import com.bumptech.glide.Glide

/**
 * Adapter for the recyclerview in the ImageFeed Screen
 */
class ImageListAdapter(private val context: Context?, private var imageList: List<Row>?) :
  RecyclerView.Adapter<ImageListAdapter.RecyclerViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val row = imageList?.get(position)
        holder.apply {
            title.text = row?.title
            description.text = row?.description
            // Show the images with valid url
            if (!row?.imageHref.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
                Glide.with(imageView.context)
                    .load(row?.imageHref)
                    .into(imageView)
            } else {
                imageView.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = imageList?.size ?: 0

    fun updateImageList(imageList: List<Row>?) {
        this.imageList = imageList
    }

    class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var description: TextView = view.findViewById(R.id.description)
        var imageView: ImageView = view.findViewById(R.id.imageView)
    }
}