package com.example.movieapplication.ui.genre.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplication.R
import com.example.movieapplication.databinding.FragmentGenreBinding
import com.example.movieapplication.ui.genre.adapter.GenreAdapter
import com.example.movieapplication.ui.genre.viewModel.GenreViewModel
import com.example.movieapplication.utility.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GenreFragment : Fragment() {

    lateinit var genreAdapter: GenreAdapter
    private lateinit var binding: FragmentGenreBinding
    private val genreViewModel: GenreViewModel by viewModels()

    @Inject
    lateinit var newDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genreAdapter = GenreAdapter(listenerClick = {
            val bundle = bundleOf("id" to it.id)
            findNavController().navigate(R.id.action_genreFragment_to_movieFragment, bundle)

        })
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbarGenre)

        (activity as AppCompatActivity?)?.title = "Genre"

        genreViewModel.isShowDialog.observe(viewLifecycleOwner) {
            if (it) {
                newDialog.showDialog()
            } else {
                newDialog.dismisDialog()
            }
        }

        binding.rvGenre.run {
            adapter = this@GenreFragment.genreAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        genreViewModel.getGenreList()
        genreViewModel.genreList.observe(viewLifecycleOwner) {
            genreAdapter.setItemList(it)
        }
    }
}