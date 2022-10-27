package com.android.imagefeed.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.imagefeed.databinding.ImageFeedFragmentBinding
import com.android.imagefeed.ui.widget.ImageListAdapter

/**
 * Fragment to show the list of the data plus images
 */
class ImageFeedFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance() = ImageFeedFragment()
    }

    lateinit var fragmentBinding: ImageFeedFragmentBinding

    private lateinit var viewModel: ImageFeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = ImageFeedFragmentBinding.inflate(layoutInflater)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ImageFeedViewModel::class.java]
        val swipeRefreshLayout = fragmentBinding.refreshContainer
        val recyclerView = fragmentBinding.imageRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)

        swipeRefreshLayout.setOnRefreshListener(this)
        val imageListAdapter = ImageListAdapter(context, null)
        recyclerView.adapter = imageListAdapter
        viewModel.titleLivaData.observe(viewLifecycleOwner) { title ->
            // Update the activity title
            activity?.title = title
        }
        viewModel.imageListLiveData.observe(viewLifecycleOwner) { rows ->
            swipeRefreshLayout.isRefreshing = false
            // Update the new data to the adapter and notify it
            imageListAdapter.updateImageList(rows)
            imageListAdapter.notifyDataSetChanged()
        }
        viewModel.initData()
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        // Make a fresh network request again
        viewModel.buildNetworkRequest()
    }
}