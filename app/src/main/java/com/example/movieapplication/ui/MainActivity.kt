package com.example.movieapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapplication.R
import com.example.movieapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setSupportActionBar(binding.toolbarGenre)
        title = "test"
        getSupportActionBar()?.hide();
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_controller
        ) as NavHostFragment
        val navController = navHostFragment.navController

    }
}