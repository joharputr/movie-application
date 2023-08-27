package com.example.movieapplication.ui.movie.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentDetailMovieBinding
import com.example.movieapplication.ui.movie.adapter.MoviePagingAdapter
import com.example.movieapplication.ui.movie.adapter.UserReviewAdapter
import com.example.movieapplication.ui.movie.viewModel.MovieViewModel
import com.example.movieapplication.utility.CustomDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DetailMovieFragment : Fragment() {
    private var adapter: UserReviewAdapter? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<RelativeLayout>
    val movieViewModel: MovieViewModel by viewModels()

    lateinit var youTubePlayerView: YouTubePlayerView

    @Inject
    lateinit var newDialog: CustomDialog

    lateinit var binding: FragmentDetailMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserReviewAdapter()

        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbarGenre)

        (activity as AppCompatActivity?)?.title = "Detail Movie"
        binding.bottomNav.rvUserReview.run {
            adapter = this@DetailMovieFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
        movieViewModel.isShowDialog.observe(requireActivity()) {
            if (it) {
                newDialog.showDialog()
            } else {
                newDialog.dismisDialog()
            }
        }


        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomNav.bottomNav)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        viewLifecycleOwner.lifecycleScope.launch {
            arguments?.getInt("id")?.let {
                movieViewModel.getDetailMovie(it)
                movieViewModel.getDataYoutube(it)
                movieViewModel.getUserReview(it).collectLatest { userReview ->
                    Log.d("userreviewtest = ",userReview.toString())
                    adapter?.submitData(userReview)
                }
            }
        }

        movieViewModel.dataYoutubeModel.observe(requireActivity()) {

            if (it.results.isNotEmpty())
                binding.bottomNav.youtubePlayerView.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId = it.results[0].key
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
        }


        movieViewModel.detailMovieModel.observe(requireActivity()) {
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original" + it.backdrop_path)
                .into(binding.imgPoster)
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original" + it.poster_path)
                .into(binding.bottomNav.imgMovie)

            binding.bottomNav.texttitle.setText(it.original_title)
            binding.bottomNav.textDate.setText(it.release_date)
            binding.bottomNav.textOverview.setText(it.overview)
        }


    }
}