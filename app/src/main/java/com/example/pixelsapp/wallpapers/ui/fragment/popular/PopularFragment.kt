package com.example.pixelsapp.wallpapers.ui.fragment.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import animationCancel
import com.example.core.model.PhotoDomain
import com.example.pixelsapp.R
import com.example.pixelsapp.databinding.FragmentPopularBinding
import com.example.pixelsapp.wallpapers.ui.fragment.adapter.photoadapter.PhotoAdapter
import com.example.pixelsapp.wallpapers.ui.fragment.main.MainFragmentDirections
import com.example.pixelsapp.wallpapers.ui.fragment.popular.viewmodel.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pulseAnimation

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel: PopularViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observerLoafState()
        fetchWallpappers()
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter(::detail)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        with(binding.recyclerView) {
            scrollToPosition(INIT_POSITION)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun fetchWallpappers() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularWallpappers().collectLatest { pagingData ->
                    photoAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun observerLoafState() {
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadState ->
                binding.imagePulseAnimation.isVisible =
                    loadState.source.refresh is LoadState.Loading
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.imagePulseAnimation.pulseAnimation()
                    }
                    is LoadState.NotLoading -> {
//                        binding.imagePulseAnimation.visibility = View.GONE
                        binding.imagePulseAnimation.animationCancel()

                    }
                    is LoadState.Error -> {
                        Toast.makeText(context, "Try again later", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

    private fun detail(photo: PhotoDomain) {
        val data = arrayOf(photo.srcDomain.original, photo.description)
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDownloadFragment(
                data
            )
        )
    }

    companion object {
        const val INIT_POSITION = 0
    }

}