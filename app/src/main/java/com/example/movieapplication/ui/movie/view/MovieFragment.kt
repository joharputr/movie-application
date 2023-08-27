package com.example.movieapplication.ui.movie.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentMovieBinding
import com.example.movieapplication.ui.movie.adapter.MoviePagingAdapter
import com.example.movieapplication.ui.movie.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var adapter: MoviePagingAdapter? = null
    val movieViewModel: MovieViewModel by viewModels()
    lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbarGenre)

        (activity as AppCompatActivity?)?.title = "List Movie"
        adapter = MoviePagingAdapter(ctx = requireContext(), listenerCard = {
            val bundle = bundleOf("id" to it.id)
            findNavController().navigate(R.id.action_movieFragment_to_detailMovieFragment, bundle)
        })

        binding.rvMovie.run {
            adapter = this@MovieFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            arguments?.getInt("id")?.let {
                movieViewModel.getDataMovie(with_genres = it).collectLatest {
                    adapter?.submitData(it)
                }
            }
        }

//        adapter?.addLoadStateListener { loadState ->
//            // show empty list
//            Log.d("loadstateee", loadState.toString())
//            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
//                binding.progressDialogPaging.visibility = View.VISIBLE
//            } else {
//                Log.d("loadstateee2", loadState.toString())
//
//                binding.shimerView.stopShimmer()
//                binding.shimerView.visibility = View.GONE
//                binding.progressDialogPaging.visibility = View.GONE
//
//                // If we have an error, show a toast
//                val errorState = when {
//                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                    else -> null
//                }
//                errorState?.let {
//                    Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }
}