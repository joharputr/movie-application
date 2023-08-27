package com.example.movieapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapplication.databinding.FragmentYoutubeViewBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeViewFragment : Fragment() {
    lateinit var youTubePlayerView: YouTubePlayerView
    lateinit var binding: FragmentYoutubeViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentYoutubeViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        youTubePlayerView = YouTubePlayerView(
            requireContext())

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "sFM2QBrtV0Y"
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}